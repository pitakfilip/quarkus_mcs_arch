package cz.muni.soa.simulation.service;

import cz.muni.soa.infrastructure.security.AuthContext;
import cz.muni.soa.kingdom.dto.DtoKingdom;
import cz.muni.soa.simulation.assembler.BattleAssembler;
import cz.muni.soa.simulation.assembler.TroopAssembler;
import cz.muni.soa.simulation.domain.*;
import cz.muni.soa.simulation.proxy.KingdomProxy;
import cz.muni.soa.simulation.proxy.WarfareBattleProxy;
import cz.muni.soa.simulation.proxy.WarfareTroopProxy;
import cz.muni.soa.simulation.repository.IBattleRepository;
import cz.muni.soa.simulation.repository.ITroopRepository;
import cz.muni.soa.warfare.dto.*;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.Integer.max;

@ApplicationScoped
public class BattleService {

    @Inject
    ITroopRepository troopRepository;

    @Inject
    IBattleRepository battleRepository;

    @RestClient
    KingdomProxy kingdomApi;

    @RestClient
    WarfareTroopProxy warfareTroopApi;
    @RestClient
    WarfareBattleProxy warfareBattleApi;

    @Inject
    AuthContext context;

    private final Random random = new Random();
    private final boolean log = true;

    public Response /*DtoBattle*/ getBattle(long id) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Battle battle = battleRepository.getById(id);
        if (battle == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Battle " + id + " not found.").build();
        }

        return Response.ok(BattleAssembler.toDto(battle)).build();
    }

    @Transactional
    public Response /*DtoBattle*/ createBattle(long target, List<cz.muni.soa.warfare.dto.DtoTroop> troops) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        DtoKingdom defender = kingdomApi.getKingdom(target);

        List<Troop> attackerTroops = TroopAssembler.fromDto(
                TroopAssembler.dtoFromWarfareDto(troops));
        List<Troop> defenderTroops = TroopAssembler.fromDto(
                TroopAssembler.dtoFromWarfareDto(warfareTroopApi.getAvailableTroops(target)));

        Battle battle = new Battle();
        battle.setStatus(BattleStatus.WAITING);
        battle.setResult(BattleResult.UNDECIDED);
        battle.setRound(0);
        battle.setAttacker(context.getKingdomId());
        battle.setDefender(target);
        battle.setAttackerTroops(attackerTroops);
        battle.setDefenderTroops(defenderTroops);  // later

        List<Long> troopIds = troops.stream().map(troop -> troop.id).collect(Collectors.toList());
        warfareBattleApi.sendToWar(troopIds);

        troopRepository.persist(attackerTroops);
        troopRepository.persist(defenderTroops);
        battleRepository.persist(battle);

        return Response.ok(BattleAssembler.toDto(battle)).build();
    }

    @Scheduled(every = "4s")
    public void advanceFirstUnfinishedBattle() {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Optional<Battle> battle = battleRepository.getFirstUnfinishedBattle();

        if (battle.isPresent()) {
            advanceBattle(battle.get().getId());
        }
    }

    // Combat logic.

    @Transactional
    void pickEligibleTarget(Troop troop, List<Troop> enemies) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        int current_target = troop.getTarget();

        // -2 means no valid targets left, skip troop
        if (current_target == -2) {
            return;
        }

        boolean enemyHasLivingMeleeTroops = combatUtilities.hasLivingMeleeTroops(enemies);

        // if the current target is still good, keep fighting him
        if (current_target >= 0 && combatUtilities.isEligibleTarget(
                troop, enemies.get(current_target), enemyHasLivingMeleeTroops)) {
            return;
        }

        if (log) {
            System.out.println(troop.getTroopClass() + " " + troop.getId() +
                    " searches for a new target"
            );
        }

        // start on a random enemy and cycle through all of them
        int start = random.nextInt(enemies.size());
        for (int offset = 0; offset < enemies.size(); offset++) {
            int new_target = (start + offset) % enemies.size();

            if (combatUtilities.isEligibleTarget(troop, enemies.get(new_target), enemyHasLivingMeleeTroops)) {
                troop.setTarget(new_target);
                troopRepository.persist(troop);
                return;
            }
        }

        // melee troop has only unreachable rangers left - keep searching
        if (troop.getTroopType() == TroopType.MELEE && combatUtilities.hasLivingRangedTroops(enemies)) {
            troop.setTarget(-1);
            troopRepository.persist(troop);
            return;
        }

        // no valid targets left in the battle
        if (log) {
            System.out.println(troop.getTroopClass() + " " + troop.getId() +
                    " has no valid targets left"
            );
        }
        troop.setTarget(-2);
        troopRepository.persist(troop);
    }

    @Transactional
    public void attackTarget(Troop troop, Troop enemy) {
        int damageDealt = max(0, troop.getDps() - enemy.getArmor());
        enemy.setHp(enemy.getHp() - damageDealt);
        troopRepository.persist(enemy);
        if (log) {
            System.out.println(troop.getTroopClass() + " " + troop.getId() + " attacks " +
                    enemy.getTroopClass() + " " + enemy.getId() + " for " +
                    troop.getDps() + "-" + enemy.getArmor() + " damage (HP: " +
                    (enemy.getHp() + troop.getDps() - enemy.getArmor()) + " -> " +
                    enemy.getHp() + ")"
            );
        }
    }

    public void attackEnemies(List<Troop> dealers, List<Troop> receivers) {
        for (Troop troop : dealers) {
            if (troop.getHp() > 0) {
                pickEligibleTarget(troop, receivers);
                if (troop.getTarget() >= 0) {
                    attackTarget(troop, receivers.get(troop.getTarget()));
                }
            }
        }
    }

    public void combatRound(Battle battle) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        if (log) {
            System.out.print("\n ===== ROUND " + battle.getRound() + " =====\n\n");
        }

        attackEnemies(battle.getAttackerTroops(), battle.getDefenderTroops());
        if (log) {
            System.out.println("----");
        }
        attackEnemies(battle.getDefenderTroops(), battle.getAttackerTroops());
    }

    @Transactional
    void performCombatRound(long id) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Battle battle = battleRepository.getById(id);
        battle.setRound(battle.getRound() + 1);
        battleRepository.persist(battle);
        combatRound(battle);
        battleRepository.persist(battle);
    }

    @Transactional
    void checkEndOfBattle(long id) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Battle battle = battleRepository.getById(id);

        if (combatUtilities.hasBeenDestroyed(battle.getAttackerTroops())) {
            battle.setResult(BattleResult.DEFENDER_WON);
            battle.setStatus(BattleStatus.FINISHING);
            battleRepository.persist(battle);
            return;
        }
        if (combatUtilities.hasBeenDestroyed(battle.getDefenderTroops())) {
            battle.setResult(BattleResult.ATTACKER_WON);
            battle.setStatus(BattleStatus.FINISHING);
            battleRepository.persist(battle);
            return;
        }
        if (combatUtilities.hasNoValidTargets(battle.getAttackerTroops()) &&
                combatUtilities.hasNoValidTargets(battle.getDefenderTroops())) {
            battle.setResult(BattleResult.STALEMATE);
            battle.setStatus(BattleStatus.FINISHING);
            battleRepository.persist(battle);
            // return;
        }
    }

    @Transactional
    void startBattle(long id) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Battle battle = battleRepository.getById(id);
        assert(battle.getStatus() == BattleStatus.WAITING);
        battle.setStatus(BattleStatus.ONGOING);
        battleRepository.persist(battle);
    }

    @Transactional
    void finishBattle(long id) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);
        TroopAssembler troopAssembler = new TroopAssembler();

        Battle battle = battleRepository.getById(id);
        assert(battle.getStatus() == BattleStatus.FINISHING);

        // call warfare with deceased/survivor troops for each of the kingdoms
        DtoWarResult attackerResult = troopAssembler.dtoToWarResultDto(battle.getAttackerTroops());
        DtoWarResult defenderResult = troopAssembler.dtoToWarResultDto(battle.getDefenderTroops());
        warfareBattleApi.warResult(attackerResult, battle.getAttacker());
        warfareBattleApi.warResult(defenderResult, battle.getDefender());

        battle.setStatus(BattleStatus.FINISHED);
        battleRepository.persist(battle);
    }

    void advanceBattle(long id) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Battle battle = battleRepository.getById(id);

        assert(battle.getStatus() != BattleStatus.FINISHED);
        switch (battle.getStatus()) {
            case WAITING:
                    startBattle(id);
                    break;
            case ONGOING:
                    performCombatRound(id);
                    checkEndOfBattle(id);
                    break;
            case FINISHING:
                    finishBattle(id);
                    break;
        }
    }
}

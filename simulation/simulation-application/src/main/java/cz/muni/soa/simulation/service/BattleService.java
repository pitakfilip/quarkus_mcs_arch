package cz.muni.soa.simulation.service;

import cz.muni.soa.infrastructure.security.AuthContext;
import cz.muni.soa.kingdom.api.KingdomApi;
import cz.muni.soa.kingdom.dto.DtoKingdom;
import cz.muni.soa.simulation.assembler.BattleAssembler;
import cz.muni.soa.simulation.assembler.TroopAssembler;
import cz.muni.soa.simulation.domain.Battle;
import cz.muni.soa.simulation.domain.BattleResult;
import cz.muni.soa.simulation.domain.BattleStatus;
import cz.muni.soa.simulation.domain.Troop;
import cz.muni.soa.simulation.dto.DtoTroop;
import cz.muni.soa.simulation.proxy.KingdomProxy;
import cz.muni.soa.simulation.repository.IBattleRepository;
import cz.muni.soa.simulation.repository.ITroopRepository;
import cz.muni.soa.simulation.resource.KingdomResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BattleService {

    @Inject
    ITroopRepository troopRepository;

    @Inject
    IBattleRepository battleRepository;

    // KingdomResource kingdomResource = new KingdomResource();  // TODO remove after next refactor

    @RestClient
    KingdomProxy kingdomApi;

    @Inject
    AuthContext context;

    @Transactional
    public Response /*DtoBattle*/ createBattle(long target, List<DtoTroop> troops) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        // verify that the target kingdom exists
        DtoKingdom defender = kingdomApi.getKingdom(target);
        // TODO maybe if null or smth

        // TODO verify that the troops exist when warfare implements that
        // TODO get defender troops
        List<Troop> attackerTroops = TroopAssembler.fromDto(troops);
        List<Troop> defenderTroops = new ArrayList<>();

        Battle battle = new Battle();
        battle.setStatus(BattleStatus.WAITING);
        battle.setResult(BattleResult.UNDECIDED);
        battle.setRound(0);
        battle.setAttacker(context.getKingdomId());
        battle.setDefender(target);
        battle.setAttackerTroops(attackerTroops);
        battle.setDefenderTroops(defenderTroops);  // later

        troopRepository.persist(attackerTroops);
        troopRepository.persist(defenderTroops);
        battleRepository.persist(battle);

        return Response.ok(BattleAssembler.toDto(battle)).build();
    }

    public Response /*DtoBattle*/ getBattle(long id) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Battle battle = battleRepository.getById(id);
        if (battle == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Battle " + id + " not found.").build();
        }

        return Response.ok(BattleAssembler.toDto(battle)).build();
    }

    @Transactional
    void performCombatRound(long id) {
        // TODO void too, this won't be exposed after CRONs work
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Battle battle = battleRepository.getById(id);
        combatUtilities.combatRound(battle);
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
        // TODO stalemate
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

        Battle battle = battleRepository.getById(id);
        assert(battle.getStatus() == BattleStatus.FINISHING);
        // TODO call warfare and shit
        battle.setStatus(BattleStatus.FINISHED);
        battleRepository.persist(battle);
    }

    public Response /*DtoBattle*/ advanceBattle(long id) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Battle battle = battleRepository.getById(id);
        if (battle == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Battle " + id + " not found.").build();
        }

        assert(battle.getStatus() != BattleStatus.FINISHED);
        switch (battle.getStatus()) {
            case WAITING:
                    startBattle(id);
            case ONGOING:
                    performCombatRound(id);
                    checkEndOfBattle(id);
            case FINISHING:
                    finishBattle(id);
        }

        return Response.ok(BattleAssembler.toDto(battle)).build();
    }
}

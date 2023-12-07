package cz.muni.soa.simulation.resource;

import cz.muni.soa.kingdom.dto.DtoKingdom;
import cz.muni.soa.simulation.api.BattleApi;
import cz.muni.soa.simulation.assembler.BattleAssembler;
import cz.muni.soa.simulation.assembler.TroopAssembler;
import cz.muni.soa.simulation.domain.Battle;
import cz.muni.soa.simulation.domain.BattleResult;
import cz.muni.soa.simulation.domain.BattleStatus;
import cz.muni.soa.simulation.domain.Troop;
import cz.muni.soa.simulation.dto.DtoTroop;
import cz.muni.soa.simulation.repository.IBattleRepository;
import cz.muni.soa.simulation.repository.ITroopRepository;
import cz.muni.soa.simulation.service.CombatUtilities;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;


@Path("/battle")
public class BattleResource implements BattleApi {

    @Inject
    ITroopRepository troopRepository;
    @Inject
    IBattleRepository battleRepository;

    CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

    KingdomResource kingdomResource = new KingdomResource();

    @Transactional
    @Override
    public Response /*DtoBattle*/ createBattle(long kingdom, long target, List<DtoTroop> troops) {

        // verify that the kingdoms exist
        Response kingdomResponse = kingdomResource.getKingdom(kingdom);
        if (kingdomResponse.getStatus() != 200) {
            return kingdomResponse;
        }

        Response targetResponse = kingdomResource.getKingdom(target);
        if (targetResponse.getStatus() != 200) {
            return targetResponse;
        }

        DtoKingdom defender = kingdomResponse.readEntity(DtoKingdom.class);
        DtoKingdom attacker = targetResponse.readEntity(DtoKingdom.class);

        // TODO verify that the troops exist when warfare implements that
        // TODO get defender troops
        List<Troop> attackerTroops = TroopAssembler.fromDto(troops);
        List<Troop> defenderTroops = new ArrayList<>();

        Battle battle = new Battle();
        battle.setStatus(BattleStatus.WAITING);
        battle.setResult(BattleResult.UNDECIDED);
        battle.setRound(0);
        battle.setAttacker(kingdom);
        battle.setDefender(target);
        battle.setAttackerTroops(attackerTroops);
        battle.setDefenderTroops(defenderTroops);  // later

        troopRepository.persist(attackerTroops);
        troopRepository.persist(defenderTroops);
        battleRepository.persist(battle);

        return Response.ok(BattleAssembler.toDto(battle)).build();
    }

    @Override
    public Response /*DtoBattle*/ getBattle(long id) {
        Battle battle = battleRepository.getById(id);
        if (battle == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Battle " + id + " not found.").build();
        }

        return Response.ok(BattleAssembler.toDto(battle)).build();
    }

    @Transactional
    @Override
    public Response /*DtoBattle*/ performCombatRound(long id) {
        Battle battle = battleRepository.getById(id);
        if (battle == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Battle " + id + " not found.").build();
        }

        combatUtilities.combatRound(battle);
        battleRepository.persist(battle);
        return Response.ok(BattleAssembler.toDto(battle)).build();
    }
}

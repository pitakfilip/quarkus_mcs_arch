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
import cz.muni.soa.simulation.service.BattleService;
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
    BattleService service;

    @Override
    public Response createBattle(long target, List<DtoTroop> troops) {
        return service.createBattle(target, troops);
    }

    @Override
    public Response getBattle(long id) {
        return service.getBattle(id);
    }

    @Override
    public Response performCombatRound(long id) {
        return service.performCombatRound(id);
    }
}

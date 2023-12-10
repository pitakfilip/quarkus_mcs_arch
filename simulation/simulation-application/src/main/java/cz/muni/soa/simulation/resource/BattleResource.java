package cz.muni.soa.simulation.resource;

import cz.muni.soa.simulation.api.BattleApi;
import cz.muni.soa.simulation.dto.DtoTroop;
import cz.muni.soa.simulation.service.BattleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

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
    public Response advanceBattle(long id) {
        return service.advanceBattle(id);
    }
}

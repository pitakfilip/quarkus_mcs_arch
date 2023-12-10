package cz.muni.soa.warfare.resource;

import cz.muni.soa.warfare.api.BattleApi;
import cz.muni.soa.warfare.dto.DtoWarResult;
import cz.muni.soa.warfare.service.WarfareService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("battle")
public class BattleResource implements BattleApi {
    @Inject
    WarfareService service;
    @Transactional
    @Override
    public Response warResult(DtoWarResult warResult, Long kingdomId) {
        service.warResult(warResult.deceasedTroops, warResult.survivorTroops,kingdomId);
        return Response.ok().build();
    }
    @Transactional
    @Override
    public Response sendToWar(List<Long> troops) {
        service.troopsToWar(troops);
        return Response.ok().build();
    }


}

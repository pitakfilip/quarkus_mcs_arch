package cz.muni.soa.warfare.resource;

import cz.muni.soa.warfare.api.TroopApi;
import cz.muni.soa.warfare.assembler.TroopAssembler;
import cz.muni.soa.warfare.domain.troop.TroopClass;
import cz.muni.soa.warfare.domain.troop.ranged.Archer;
import cz.muni.soa.warfare.dto.*;
import cz.muni.soa.warfare.service.WarfareService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@Path("/troops")
public class TroopResource implements TroopApi {
    @Inject
    WarfareService service;


    @Override
    public List<DtoTroop> getAvailableTroops() {
        return (service.getAvailableTroops());
    }

    @Override
    @Transactional
    public Response levelUpClass(DtoTroopClass troopClass) {
        service.levelUpClass(troopClass);
        return Response.ok().build();
    }

    @Override
    @Transactional
    public Response trainTroops(List<DtoTroopRequest> requests) throws Exception {
        return Response.ok(service.trainTroops(requests)).build();
    }


}

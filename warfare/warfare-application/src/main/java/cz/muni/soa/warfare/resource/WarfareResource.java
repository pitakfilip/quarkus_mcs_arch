package cz.muni.soa.warfare.resource;

import cz.muni.soa.warfare.api.WarfareApi;
import cz.muni.soa.warfare.service.WarfareService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/warfare")
public class WarfareResource implements WarfareApi {

    @Inject
    WarfareService service;

    @Override
    @Transactional
    public Response initKingdomTroops() throws Exception {
        service.initializeKingdomsDomain();

//        service.getMoney();
        return Response.ok().build();
    }
}

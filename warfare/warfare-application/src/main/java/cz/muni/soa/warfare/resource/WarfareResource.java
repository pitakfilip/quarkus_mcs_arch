package cz.muni.soa.warfare.resource;

import cz.muni.soa.warfare.api.WarfareApi;
import cz.muni.soa.warfare.service.WarfareService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/warfare")
public class WarfareResource implements WarfareApi {

    @Inject
    WarfareService service;

    @Override
    public Response initKingdomTroops() {
        service.initializeKingdomsDomain();
        return Response.ok().build();
    }
}

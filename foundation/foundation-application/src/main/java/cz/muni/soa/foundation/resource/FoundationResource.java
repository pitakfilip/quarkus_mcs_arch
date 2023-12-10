package cz.muni.soa.foundation.resource;

import cz.muni.soa.foundation.api.FoundationApi;
import cz.muni.soa.foundation.dto.DtoDefence;
import cz.muni.soa.foundation.dto.DtoResourceRequest;
import cz.muni.soa.foundation.service.FoundationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/foundation")
public class FoundationResource implements FoundationApi {
    
    @Inject
    FoundationService service;
    
    @Override
    public Response createFoundation() throws Exception {
        service.create();
        return Response.ok().build();
    }

    @Override
    public List<DtoDefence> getKingdomDefence(long kingdomId) {
        return service.getKingdomDefence(kingdomId);
    }

    @Override
    public Response addResources(List<DtoResourceRequest> resourceRequests) {
        service.addResources(resourceRequests);
        return Response.ok().build();
    }
}

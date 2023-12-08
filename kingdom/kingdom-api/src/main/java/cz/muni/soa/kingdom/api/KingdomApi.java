package cz.muni.soa.kingdom.api;

import cz.muni.soa.kingdom.dto.DtoKingdom;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

public interface KingdomApi {
    
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    DtoKingdom getKingdom(@PathParam("id") long id);
    
    @POST
    @Path("/experience/{amount}")
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    void addExperience(@PathParam("amount") int amount);
    
}

package cz.muni.soa.kingdom.api;

import cz.muni.soa.kingdom.dto.DtoKingdom;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

public interface KingdomApi {

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    DtoKingdom createKingdom(@RequestBody String name);
    
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    DtoKingdom getKingdom(@PathParam("id") long id);
    
    @GET
    @Path("/add/{id}/experience/{amount}")
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    void addExperience(@PathParam("id") long id, @PathParam("amount") int amount);
    
}

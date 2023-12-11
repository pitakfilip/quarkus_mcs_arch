package cz.muni.soa.warfare.api;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

public interface WarfareApi {
    
    @POST
    @Path("/initTroops")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Initializes all needed entities for troops")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response initKingdomTroops() throws Exception;


}

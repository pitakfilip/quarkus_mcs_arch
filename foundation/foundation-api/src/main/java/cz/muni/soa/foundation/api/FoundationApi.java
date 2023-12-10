package cz.muni.soa.foundation.api;

import cz.muni.soa.foundation.dto.DtoDefence;
import cz.muni.soa.foundation.dto.DtoResourceRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

public interface FoundationApi {
    
    @POST
    @Path("/init")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Initialize a new foundation for a kingdom.")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response createFoundation() throws Exception;

    @GET
    @Path("/defence/{kingdomId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get an accumulation of a kingdom's foundation defence with their stats.")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    List<DtoDefence> getKingdomDefence(@PathParam("kingdomId") long kingdomId); 
    
    @POST
    @Path("/collect")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Manual production of resources.")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response addResources(@RequestBody List<DtoResourceRequest> resourceRequests);
}

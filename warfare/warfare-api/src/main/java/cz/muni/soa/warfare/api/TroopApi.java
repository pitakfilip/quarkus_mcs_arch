package cz.muni.soa.warfare.api;

import cz.muni.soa.warfare.dto.DtoTroop;
import cz.muni.soa.warfare.dto.DtoTroopClass;
import cz.muni.soa.warfare.dto.DtoTroopRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

public interface TroopApi {

    @GET
    @Path("/available/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Request troops of a kingdom that are not currently at war")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    List<DtoTroop> getAvailableTroops(@PathParam("id") Long id);



    @POST
    @Path("/levelUp")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Levels up class of troop")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response levelUpClass(@QueryParam("class") DtoTroopClass troopClass);



    @POST
    @Path("/trainTroops")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Orders troops to train")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response trainTroops(@RequestBody List<DtoTroopRequest> requests) throws Exception;
}

package cz.muni.soa.warfare.api;

import cz.muni.soa.warfare.dto.DtoTroop;
import cz.muni.soa.warfare.dto.DtoWarResult;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

public interface BattleApi {


    @POST
    @Path("/warRes/{kId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Saves which troops have survived the war and which died")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response warResult(@RequestBody DtoWarResult warResult,
                       @PathParam("kId")Long kingdomId);

    @POST
    @Path("/sendToWar")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Sends troops to battle")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response sendToWar(@RequestBody List<Long> troops);

}

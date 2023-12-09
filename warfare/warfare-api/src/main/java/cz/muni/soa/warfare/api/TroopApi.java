package cz.muni.soa.warfare.api;

import cz.muni.soa.warfare.dto.DtoTroop;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

public interface TroopApi {

    @GET
    @Path("/available")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    List<DtoTroop> getAvailableTroops();

    @POST
    @Path("/addTroops")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
     Response addTroops(@RequestBody  List<DtoTroop> troops);

    @POST
    @Path("/levelUp")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    Response levelUpClass(@QueryParam("class") String troopClass);

}

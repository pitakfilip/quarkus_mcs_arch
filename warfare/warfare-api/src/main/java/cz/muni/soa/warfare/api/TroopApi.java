package cz.muni.soa.warfare.api;

import cz.muni.soa.warfare.dto.DtoTroop;
import cz.muni.soa.warfare.dto.DtoTroopClass;
import cz.muni.soa.warfare.dto.DtoTroopType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

public interface TroopApi {

    @GET
    @Path("/availTroops")
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
    @Path("/levelUpClass/{t}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )

    Response levelUpClass(@PathParam("t") DtoTroopClass tC);

}

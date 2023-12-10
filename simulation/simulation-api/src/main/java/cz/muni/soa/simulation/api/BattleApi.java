package cz.muni.soa.simulation.api;

import cz.muni.soa.warfare.dto.DtoTroop;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

public interface BattleApi {
    @POST
    @Path("/{target}")
    @Produces(MediaType.APPLICATION_JSON)  // just the battle id
    @Operation(summary = "Attack another kingdom with given troops")
    @APIResponse(responseCode = "200", description = "Battle created")
    @APIResponse(responseCode = "400", description = "Validation error")
    @APIResponse(responseCode = "404", description = "Kingdom or troops not found")
    @APIResponse(responseCode = "500", description = "Error in service dependencies")
    Response /*DtoBattle*/ createBattle(
            @PathParam("target") long target,
            @RequestBody List<cz.muni.soa.warfare.dto.DtoTroop> troops
    );

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200")
    @APIResponse(responseCode = "404")
    Response /*DtoBattle*/ getBattle(@PathParam("id") long id);

    // TODO eventually remove when CRONs work
    @POST
    @Path("/advance/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200")
    @APIResponse(responseCode = "404")
    Response /*DtoBattle*/ advanceBattle(@PathParam("id") long id);
}

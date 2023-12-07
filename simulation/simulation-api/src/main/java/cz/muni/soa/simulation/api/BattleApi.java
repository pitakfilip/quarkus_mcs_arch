package cz.muni.soa.simulation.api;

import cz.muni.soa.simulation.dto.DtoBattle;
import cz.muni.soa.simulation.dto.DtoBattleStatus;
import cz.muni.soa.simulation.dto.DtoTroop;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

public interface BattleApi {
    @POST
    @Path("/battle/{kingdom}:{target}")
    @Produces(MediaType.APPLICATION_JSON)  // just the battle id
    @Operation(summary = "Attack another kingdom with given troops")
    @APIResponse(responseCode = "200", description = "Battle created")
    @APIResponse(responseCode = "400", description = "Validation error")
    @APIResponse(responseCode = "500", description = "Internal server error")  // idk
    DtoBattle createBattle(
            @PathParam("kingdom") long kingdom,
            @PathParam("target") long target,
            @RequestBody List<DtoTroop> troops
    );

    @GET
    @Path("/battle/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get the status of a battle")
    @APIResponse(responseCode = "200")
    @APIResponse(responseCode = "404")
    Response /*DtoBattleStatus*/ getBattleStatus(@PathParam("id") long id);
}

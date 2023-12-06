package cz.muni.soa.simulation.api;

import cz.muni.soa.simulation.dto.DtoTroop;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

public interface SimulationApi {
    @POST
    @Path("/attack")
    @Produces(MediaType.APPLICATION_JSON)  // just the battle id
    @Operation(summary = "Attack another kingdom with given troops")
    @APIResponse(responseCode = "200", description = "Battle created")
    @APIResponse(responseCode = "400", description = "Not enough troops in kingdom")
    @APIResponse(responseCode = "500", description = "Internal server error")  // idk
    long createBattle(
            @PathParam("target") long kingdom,
            @PathParam("target") long target,
            @PathParam("troops") List<DtoTroop> troops
    );
}

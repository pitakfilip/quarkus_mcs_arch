package cz.muni.soa.warfare.api;

import cz.muni.soa.warfare.dto.DtoKingdomTroopLevel;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

public interface WarfareApi {
    @POST
    @Path("/initLevels/{kID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    void initKingdomTroops(@PathParam("kID") Long id);

}

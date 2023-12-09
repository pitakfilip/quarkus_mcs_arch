package cz.muni.soa.foundation.api;

import cz.muni.soa.foundation.dto.DtoDefence;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

public interface FoundationApi {
    
    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    Response createFoundation();

    @GET
    @Path("/defence/{kingdomId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    List<DtoDefence> getKingdomDefence(@PathParam("kingdomId") long kingdomId);    
}

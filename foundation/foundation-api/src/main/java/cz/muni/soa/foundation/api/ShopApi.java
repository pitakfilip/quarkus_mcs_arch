package cz.muni.soa.foundation.api;


import cz.muni.soa.foundation.dto.DtoDefenceType;
import cz.muni.soa.foundation.dto.DtoProducerType;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

public interface ShopApi {

    @GET
    @Path("/gold")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    boolean hasEnoughGold(@QueryParam("need") long requiredGold);

    @GET
    @Path("/defence")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    Response buyDefence(@QueryParam("type") DtoDefenceType type);

    @GET
    @Path("/producer")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    Response buyProducer(@QueryParam("type") DtoProducerType type);

}

package cz.muni.soa.foundation.api;


import cz.muni.soa.foundation.dto.DtoDefenceType;
import cz.muni.soa.foundation.dto.DtoProducerType;
import cz.muni.soa.foundation.dto.DtoResourceRequest;
import cz.muni.soa.foundation.dto.DtoResourceType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

public interface ShopApi {

    @GET
    @Path("/funds/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    DtoResourceRequest hasResources(@PathParam("type") DtoResourceType type, 
                                    @QueryParam("need") long amount) throws Exception;

    @GET
    @Path("/defence")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    Response buyDefence(@QueryParam("type") DtoDefenceType type) throws Exception;

    @GET
    @Path("/producer")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "")
    @APIResponse(
            responseCode = "200",
            description = ""
    )
    Response buyProducer(@QueryParam("type") DtoProducerType type) throws Exception;

}

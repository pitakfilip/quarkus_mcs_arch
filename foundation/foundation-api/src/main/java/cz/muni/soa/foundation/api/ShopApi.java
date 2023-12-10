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
    @Operation(summary = "Check foundation's storage for a specific resource type, whether a requested amount is available.")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    DtoResourceRequest hasResources(@PathParam("type") DtoResourceType type, 
                                    @QueryParam("need") long amount) throws Exception;
    
    @POST
    @Path("/spend/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Request foundation to spend a resource type.")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response spendResources(@PathParam("type") DtoResourceType type,
                            @QueryParam("need") long amount) throws Exception;

    @POST
    @Path("/defence")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Request foundation to build a defence for a kingdom.")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response buyDefence(@QueryParam("type") DtoDefenceType type) throws Exception;

    @POST
    @Path("/producer")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Request foundation to build a resource producer for a kingdom.")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response buyProducer(@QueryParam("type") DtoProducerType type) throws Exception;

}

package cz.muni.soa.kingdom.api;


import cz.muni.soa.kingdom.dto.DtoAuthRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

public interface AuthApi {

    @POST
    @Path("/signup")
    @Operation(summary = "Create new user and his kingdom.")
    @APIResponse(responseCode = "200", description = "Request successfully processed.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response signup(@RequestBody DtoAuthRequest authRequest) throws Exception;

    @POST
    @Path("/login")
    @Operation(summary = "Authenticate user credentials and generate JWT token returned in header and cookies.")
    @APIResponse(responseCode = "200", description = "User authenticated successfully, generated JWT added to headers of response.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response login(@RequestBody DtoAuthRequest authRequest);


    @GET
    @Path("/logout")
    @Operation(summary = "Logging out of the service mesh invalidating the stored JWT in cookies.")
    @APIResponse(responseCode = "200", description = "Removed JWT from cookies, leading to no further access to wild-tribes services.")
    @APIResponse(responseCode = "401", description = "Attempted unauthorized access to protected endpoint.")
    @APIResponse(responseCode = "500", description = "Server error thrown by domain validations.")
    Response logout();
}

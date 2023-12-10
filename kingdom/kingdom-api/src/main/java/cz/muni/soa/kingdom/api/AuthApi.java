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
    @Operation(summary = "Create new user")
    @APIResponse(
            responseCode = "200",
            description = "Alles gutes"
    )
    Response signup(@RequestBody DtoAuthRequest authRequest) throws Exception;

    @POST
    @Path("/login")
    @Operation(summary = "Authenticate user")
    @APIResponse(
            responseCode = "200",
            description = "User authenticated successfully, generated JWT added to headers of response."
    )
    Response login(@RequestBody DtoAuthRequest authRequest);


    @GET
    @Path("/logout")
    @Operation(summary = "Logging out of the service mesh invalidating the stored JWT in cookies.")
    @APIResponse(
            responseCode = "200",
            description = "Removing valid JWT from cookies, leading to no further access to wild-tribes services."
    )
    Response logout();
}

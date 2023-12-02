package cz.muni.soa.kingdom.resource;

import cz.muni.soa.kingdom.dto.DtoLogin;
import cz.muni.soa.kingdom.api.AuthApi;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthResource implements AuthApi {
    
    @Override
    public String login(DtoLogin login) {
        return null;
    }

    @Override
    public Response logout() {
        return null;
    }
}

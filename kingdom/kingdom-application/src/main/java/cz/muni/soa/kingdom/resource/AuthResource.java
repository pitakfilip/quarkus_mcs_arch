package cz.muni.soa.kingdom.resource;

import cz.muni.soa.kingdom.auth.DomainUser;
import cz.muni.soa.kingdom.auth.JwtGenerator;
import cz.muni.soa.kingdom.service.AuthService;
import cz.muni.soa.kingdom.dto.DtoAuthRequest;
import cz.muni.soa.kingdom.api.AuthApi;
import cz.muni.soa.kingdom.service.KingdomService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthResource implements AuthApi {

    @Inject
    AuthService authService;

    @Inject
    KingdomService kingdomService;

    @Inject
    JwtGenerator jwtGenerator;

    @Override
    @Transactional
    public Response signup(DtoAuthRequest request) {
        if (!authService.canCreate(request.username)) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        long kingdomId = kingdomService.createKingdom(request.kingdomName);
        authService.createUser(kingdomId, request.username, request.username, request.password);
        return Response.ok().build();
    }

    @Override
    public Response login(DtoAuthRequest login) {
        DomainUser logged = authService.getUserByCredentials(login.username, login.password);

        // User with provided credentials not found
        if (logged == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String jwt = jwtGenerator.generateToken(logged.kingdomId, logged.username, "BASIC");
        NewCookie cookie = new NewCookie.Builder("JWT")
                .secure(true)
                .maxAge(86400) // ONE DAY
                .value(jwt)
                .build();
        
        return Response.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .cookie(cookie)
                .build();
    }

    @Override
    public Response logout() {
        NewCookie cookie = new NewCookie.Builder("JWT")
                .secure(true)
                .maxAge(1)
                .value("")
                .build();

        return Response.ok()
                .header(HttpHeaders.AUTHORIZATION, "")
                .cookie(cookie)
                .build();
    }
}

package cz.muni.soa.infrastructure.security;

import cz.muni.soa.infrastructure.security.jwt.JwtSecurityContext;
import cz.muni.soa.infrastructure.security.jwt.JwtValidator;
import cz.muni.soa.infrastructure.security.model.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    JwtValidator validator;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        UriInfo info = requestContext.getUriInfo();

        // For all routes besides auth, which is unprotected
        if (!info.getPath().startsWith("/auth") && !info.getPath().startsWith("/public")) {
            Optional<User> user = getAuthentication(requestContext.getHeaders());
            if (user.isEmpty()) {
                Response response = Response.status(Response.Status.UNAUTHORIZED).build();
                requestContext.abortWith(response);
                return;
            }
            requestContext.setSecurityContext(new JwtSecurityContext(user.get(), true));
        }
    }

    private Optional<User> getAuthentication(MultivaluedMap<String, String> headers) {
        final List<String> authorization = headers.get(HttpHeaders.AUTHORIZATION);

        if (authorization == null || authorization.isEmpty()) {
            return Optional.empty();
        }
        final String token = authorization.get(0).replaceFirst("Bearer ", "");
        try {
            User user = validator.validateAndFetchUser(token);
            if (user == null) {
                return Optional.empty();
            }
            return Optional.of(user);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
    
    
}

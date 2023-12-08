package cz.muni.soa.infrastructure.security.jwt;

import cz.muni.soa.infrastructure.security.model.User;
import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;

public final class JwtSecurityContext implements SecurityContext {
    
    private final User user;
    private final boolean secured;

    public JwtSecurityContext(User user, boolean secured) {
        this.user = user;
        this.secured = secured;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isUserInRole(String s) {
        return Boolean.TRUE; // not checking the roles
    }

    @Override
    public boolean isSecure() {
        return this.secured;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.FORM_AUTH;
    }
}

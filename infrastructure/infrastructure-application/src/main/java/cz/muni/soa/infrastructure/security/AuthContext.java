package cz.muni.soa.infrastructure.security;

import cz.muni.soa.infrastructure.security.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.SecurityContext;

@ApplicationScoped
public class AuthContext {
    
    @Inject
    SecurityContext securityContext;
    
    public long getKingdomId() {
        return ((User) securityContext.getUserPrincipal()).getId();
    }
}

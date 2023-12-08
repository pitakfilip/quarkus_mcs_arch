package cz.muni.soa.kingdom.service;


import cz.muni.soa.kingdom.auth.DomainUser;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthService {

    /**
     * Create new user account.
     */
    public void createUser(long kingdomId, String name, String username, String password) {
        DomainUser domainUser = new DomainUser();
        domainUser.name = name;
        domainUser.username = username;
        domainUser.password = password; // ENCODE!
        domainUser.kingdomId = kingdomId;
        
        domainUser.persist();
    }

    public DomainUser getUserByCredentials(String username, String password) {
        DomainUser user = DomainUser.findByUsername(username);
        
        if (user == null || !user.password.equals(password)) {
            return null;
        }
        return user;
    }

    public boolean canCreate(String username) {
        return DomainUser.count("username", username) == 0;
    }
}

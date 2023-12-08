package cz.muni.soa.kingdom.auth;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class DomainUser extends PanacheEntity {
    
    public String name;
    
    public String username;
    
    public String password;
    
    public long kingdomId;
    
    public static DomainUser findByUsername(String username) {
        return find("username", username).firstResult();
    }

}

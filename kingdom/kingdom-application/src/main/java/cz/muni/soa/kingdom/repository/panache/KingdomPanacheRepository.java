package cz.muni.soa.kingdom.repository.panache;

import cz.muni.soa.kingdom.domain.Kingdom;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KingdomPanacheRepository implements PanacheRepository<Kingdom> {
    
    public void create(Kingdom kingdom) {
        persist(kingdom);
    }

}

package cz.muni.soa.kingdom.service;

import cz.muni.soa.kingdom.domain.Kingdom;
import cz.muni.soa.kingdom.repository.IKingdomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class KingdomService {

    @Inject
    IKingdomRepository kingdomRepository;
    
    
    public long createKingdom(String name) {
        Kingdom kingdom = new Kingdom(name);
        kingdomRepository.persist(kingdom);
        return kingdom.getId();
        
        // TODO call warfare and foundation APIs to setup users domain        
    }
    
}

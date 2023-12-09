package cz.muni.soa.kingdom.service;

import cz.muni.soa.kingdom.domain.Kingdom;
import cz.muni.soa.kingdom.proxy.WarfareProxy;
import cz.muni.soa.kingdom.repository.IKingdomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@ApplicationScoped
public class KingdomService {

    @Inject
    IKingdomRepository kingdomRepository;

    @RestClient
                
    WarfareProxy warfareApi;

    @Transactional
    public long createKingdom(String name) {
        Kingdom kingdom = new Kingdom(name);
        kingdomRepository.persist(kingdom);
        //  TODO call warfare and foundation APIs to setup users domain 
        // will rollback DB on error
        warfareApi.initKingdomTroops();

        return kingdom.getId();
    }
    
}
  
package cz.muni.soa.foundation.repository;

import cz.muni.soa.foundation.domain.Foundation;
import cz.muni.soa.foundation.domain.repository.IFoundationRepository;
import cz.muni.soa.foundation.repository.panache.FoundationPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FoundationRepository implements IFoundationRepository {
    
    @Inject
    FoundationPanacheRepository repository;
    
    @Override
    public Foundation ofKingdom(long kingdomId) {
        return repository.find("kingdomId", kingdomId).firstResult();
    }

    @Override
    public void persist(Foundation foundation) {
        repository.persist(foundation);
    }
}

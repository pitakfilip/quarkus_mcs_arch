package cz.muni.soa.kingdom.service;

import cz.muni.soa.kingdom.domain.Kingdom;
import cz.muni.soa.kingdom.domain.Progress;
import cz.muni.soa.kingdom.repository.IKingdomRepository;

public final class KingdomOperations {
    
    private final IKingdomRepository repository;

    public KingdomOperations(IKingdomRepository repository) {
        this.repository = repository;
    }
    
    public void addExperience(long kingdomId, int amount) {
        Kingdom kingdom = repository.getById(kingdomId);
        
        if (kingdom == null) {
            throw new NullPointerException("Kingdom not found");    
        }
        
        Progress progress = kingdom.getProgress();
        progress.addExperience(amount);
        
        repository.persist(kingdom);
    }
}

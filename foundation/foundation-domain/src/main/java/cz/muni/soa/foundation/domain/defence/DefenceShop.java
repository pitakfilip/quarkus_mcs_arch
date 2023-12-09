package cz.muni.soa.foundation.domain.defence;

import cz.muni.soa.foundation.domain.Foundation;
import cz.muni.soa.foundation.domain.FoundationRepository;
import cz.muni.soa.foundation.domain.resource.ResourceStorage;
import cz.muni.soa.foundation.domain.resource.ResourceType;

import java.util.Map;

public class DefenceShop {

    private final FoundationRepository repository;

    public DefenceShop(FoundationRepository repository) {
        this.repository = repository;
    }
    
    public void buyArcherTower(long kingdomId) {
        Foundation foundation = repository.ofKingdom(kingdomId);
        
        if (buyDefence(foundation, DefenceCosts.archerTower())) {
            foundation.getDefences().add(DefenceFactory.archerTower());
            repository.persist(foundation);
        }
    }
    
    public void buyCannon(long kingdomId) {
        Foundation foundation = repository.ofKingdom(kingdomId);

        if (buyDefence(foundation, DefenceCosts.cannon())) {
            foundation.getDefences().add(DefenceFactory.cannon());
            repository.persist(foundation);
        }
    }
    
    public void buyBatman(long kingdomId) {
        Foundation foundation = repository.ofKingdom(kingdomId);

        if (buyDefence(foundation, DefenceCosts.batman())) {
            foundation.getDefences().add(DefenceFactory.batman());
            repository.persist(foundation);
        }
    }
    
    public void buyPerimeterWall(long kingdomId) {}
    
    public void buyGalacticShield(long kingdomId) {
        Foundation foundation = repository.ofKingdom(kingdomId);

        if (buyDefence(foundation, DefenceCosts.galacticShield())) {
            foundation.getDefences().add(DefenceFactory.shield());
            repository.persist(foundation);
        }
    }
    
    
    private boolean buyDefence(Foundation foundation, Map<ResourceType, Long> cost) {
        ResourceStorage storage = foundation.getStorage();
        if (!sufficientFunds(storage, cost)) {
            return false;
        }

        for (ResourceType fundType : ResourceType.values()) {
            storage.subtract(fundType, cost.get(fundType));
        }
        return true;
    }
    
    private boolean sufficientFunds(ResourceStorage storage, Map<ResourceType, Long> cost) {
        for (ResourceType fundType : ResourceType.values()) {
            long have = storage.getResource(fundType);
            long need = cost.get(fundType);
            if (have < need) {
                return false;
            }
        }
        return true;
    }
}

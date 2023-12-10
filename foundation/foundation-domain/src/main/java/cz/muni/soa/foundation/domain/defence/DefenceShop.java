package cz.muni.soa.foundation.domain.defence;

import cz.muni.soa.foundation.domain.Foundation;
import cz.muni.soa.foundation.domain.defence.active.ArcherTower;
import cz.muni.soa.foundation.domain.defence.active.Batman;
import cz.muni.soa.foundation.domain.defence.active.Cannon;
import cz.muni.soa.foundation.domain.defence.passive.GalacticShield;
import cz.muni.soa.foundation.domain.repository.IDefenceRepository;
import cz.muni.soa.foundation.domain.repository.IFoundationRepository;
import cz.muni.soa.foundation.domain.resource.ResourceStorage;
import cz.muni.soa.foundation.domain.resource.ResourceType;

import java.util.Map;

public class DefenceShop {

    private final IFoundationRepository repository;
    
    private final IDefenceRepository defenceRepository;

    public DefenceShop(IFoundationRepository repository, IDefenceRepository defenceRepository) {
        this.repository = repository;
        this.defenceRepository = defenceRepository;
    }
    
    public void buyArcherTower(long kingdomId) {
        Foundation foundation = repository.ofKingdom(kingdomId);
        
        if (buyDefence(foundation, DefenceCosts.archerTower())) {
            ArcherTower archerTower = DefenceFactory.archerTower();
            defenceRepository.persist(archerTower);
            foundation.getDefences().add(archerTower);
            repository.persist(foundation);
        }
    }
    
    public void buyCannon(long kingdomId) {
        Foundation foundation = repository.ofKingdom(kingdomId);

        if (buyDefence(foundation, DefenceCosts.cannon())) {
            Cannon cannon = DefenceFactory.cannon();
            defenceRepository.persist(cannon);
            foundation.getDefences().add(cannon);
            repository.persist(foundation);
        }
    }
    
    public void buyBatman(long kingdomId) {
        Foundation foundation = repository.ofKingdom(kingdomId);

        if (buyDefence(foundation, DefenceCosts.batman())) {
            Batman batman = DefenceFactory.batman();
            defenceRepository.persist(batman);
            foundation.getDefences().add(batman);
            repository.persist(foundation);
        }
    }
    
    public void buyPerimeterWall(long kingdomId) {}
    
    public void buyGalacticShield(long kingdomId) {
        Foundation foundation = repository.ofKingdom(kingdomId);

        if (buyDefence(foundation, DefenceCosts.galacticShield())) {
            GalacticShield shield = DefenceFactory.shield();
            defenceRepository.persist(shield);
            foundation.getDefences().add(shield);
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

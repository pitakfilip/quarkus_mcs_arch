package cz.muni.soa.foundation.domain;

import cz.muni.soa.foundation.domain.defence.DefenceFactory;
import cz.muni.soa.foundation.domain.resource.*;
import cz.muni.soa.foundation.domain.resource.producer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoundationOperations {

    private final FoundationRepository repository;

    
    public FoundationOperations(FoundationRepository repository) {
        this.repository = repository;
    }

    public void createNewFoundation(long kingdomId) throws Exception {
        Foundation existing = repository.ofKingdom(kingdomId);
        if (existing != null) {
            throw new Exception("Foundation for provided Kingdom id={" + kingdomId + "} already exists.");
        }
        Foundation foundation = new Foundation(kingdomId);
        foundation.getDefences().addAll(
                List.of(
                        DefenceFactory.wall(),
                        DefenceFactory.archerTower()
                )
        );
        foundation.getProducers().addAll(
                List.of(
                        new Lumberjack(),
                        new Lumberjack(),
                        new IronMine(),
                        new GoldSmelter()
                )
        );

        repository.persist(foundation);
    }

    /** WARNING - need transaction */
    public void addNewProducer(long kingdomId, ResourceType type) throws Exception {
        Foundation foundation = repository.ofKingdom(kingdomId);
        if (foundation != null) {
            throw new Exception("Foundation for provided Kingdom id={" + kingdomId + "} not found.");
        }

        Map<ResourceType, Long> costs = ProducerCosts.of(type);
        ResourceStorage storage = foundation.getStorage();
        
        List<String> insufficientFunds = new ArrayList<>();
        for (ResourceType fundType : ResourceType.values()) {
            long have = storage.getResource(fundType); 
            long need = costs.get(fundType);
            if (have < need) {
                insufficientFunds.add(String.format("[%s] missing %d", fundType.name(), (need - have)));
            }
        }
        
        if (!insufficientFunds.isEmpty()) {
            String message = "Insufficient funds: " + String.join(",", insufficientFunds);
            throw new Exception(message);
        }

        for (ResourceType fundType : ResourceType.values()) {
            storage.subtract(fundType, costs.get(fundType));
        }
        
        ResourceProducer producer = switch (type) {
            case WOOD -> new Lumberjack();
            case IRON -> new IronMine();
            case GOLD -> new GoldSmelter();
        };
        
        foundation.getProducers().add(producer);
        repository.persist(foundation);
    }
        
}

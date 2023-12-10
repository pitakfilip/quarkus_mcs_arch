package cz.muni.soa.foundation.domain;

import cz.muni.soa.foundation.domain.defence.Defence;
import cz.muni.soa.foundation.domain.defence.DefenceFactory;
import cz.muni.soa.foundation.domain.resource.*;
import cz.muni.soa.foundation.domain.resource.producer.*;
import cz.muni.soa.foundation.domain.repository.IDefenceRepository;
import cz.muni.soa.foundation.domain.repository.IFoundationRepository;
import cz.muni.soa.foundation.domain.repository.IProducerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoundationOperations {

    private final IFoundationRepository repository;

    private final IDefenceRepository defenceRepository;
    
    private final IProducerRepository producerRepository;
    
    public FoundationOperations(IFoundationRepository repository, IDefenceRepository defenceRepository, IProducerRepository producerRepository) {
        this.repository = repository;
        this.defenceRepository = defenceRepository;
        this.producerRepository = producerRepository;
    }

    public void createNewFoundation(long kingdomId) throws Exception {
        Foundation existing = repository.ofKingdom(kingdomId);
        if (existing != null) {
            throw new Exception("Foundation for provided Kingdom id={" + kingdomId + "} already exists.");
        }
        
        List<Defence> defences = new ArrayList<>();
        defences.add(DefenceFactory.wall());
        defences.add(DefenceFactory.archerTower());
        defenceRepository.persist(defences);
        
        List<ResourceProducer> producers = new ArrayList<>();
        producers.add(new Lumberjack());
        producers.add(new Lumberjack());
        producers.add(new IronMine());
        producers.add(new GoldSmelter());
        producerRepository.persist(producers);
        
        
        Foundation foundation = new Foundation(kingdomId);
        foundation.getDefences().addAll(defences);
        foundation.getProducers().addAll(producers);
        repository.persist(foundation);
    }

    /** WARNING - need transaction */
    public void addNewProducer(long kingdomId, ResourceType type) throws Exception {
        Foundation foundation = repository.ofKingdom(kingdomId);
        if (foundation == null) {
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
        producerRepository.persist(producer);
        
        foundation.getProducers().add(producer);
        repository.persist(foundation);
    }
        
}

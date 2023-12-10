package cz.muni.soa.foundation.service;

import cz.muni.soa.foundation.assembler.ResourceTypeAssembler;
import cz.muni.soa.foundation.domain.Foundation;
import cz.muni.soa.foundation.domain.FoundationOperations;
import cz.muni.soa.foundation.domain.defence.DefenceShop;
import cz.muni.soa.foundation.domain.resource.ResourceType;
import cz.muni.soa.foundation.dto.DtoDefenceType;
import cz.muni.soa.foundation.dto.DtoProducerType;
import cz.muni.soa.foundation.dto.DtoResourceRequest;
import cz.muni.soa.foundation.dto.DtoResourceType;
import cz.muni.soa.foundation.repository.DefenceRepository;
import cz.muni.soa.foundation.repository.FoundationRepository;
import cz.muni.soa.foundation.repository.ProducerRepository;
import cz.muni.soa.infrastructure.security.AuthContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ShopService {

    @Inject
    FoundationRepository repository;

    @Inject
    DefenceRepository defenceRepository;

    @Inject
    ProducerRepository producerRepository;
    
    @Inject
    AuthContext context;
    
    public DtoResourceRequest hasResources(DtoResourceType type, long amount) throws Exception {
        Foundation foundation = repository.ofKingdom(context.getKingdomId());
        
        if (foundation == null) {
            throw new Exception("Foundation for provided kingdom not found");
        }
        
        long has = foundation.getStorage().getResource(ResourceTypeAssembler.fromDto(type));
        DtoResourceRequest result = new DtoResourceRequest();
        result.type = type;
        result.amount = amount;
        result.hasEnough = (has - amount) >= 0;
        result.missing = (result.hasEnough) ? 0 : (amount - has);       
        
        return result;
    }

    @Transactional
    public void buyDefence(DtoDefenceType type) throws Exception {
        DefenceShop shop = new DefenceShop(repository, defenceRepository);
        
        switch (type) {
            case ARCHER_TOWER -> shop.buyArcherTower(context.getKingdomId());
            case CANNON -> shop.buyCannon(context.getKingdomId());
            case BATMAN -> shop.buyBatman(context.getKingdomId());
            case GALACTIC_SHIELD -> shop.buyGalacticShield(context.getKingdomId());
            case WALL -> throw new Exception("Kingdom already has a wall!");
        }
    }

    @Transactional
    public void buyProducer(DtoProducerType type) throws Exception {
        FoundationOperations operations = new FoundationOperations(repository, defenceRepository, producerRepository);

        switch (type) {
            case LUMBERJACK -> operations.addNewProducer(context.getKingdomId(), ResourceType.WOOD);
            case IRON_MINE -> operations.addNewProducer(context.getKingdomId(), ResourceType.IRON);
            case GOLD_SMELTER -> operations.addNewProducer(context.getKingdomId(), ResourceType.GOLD);
        }
    }
}

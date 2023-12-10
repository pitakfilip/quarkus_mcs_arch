package cz.muni.soa.foundation.service;

import cz.muni.soa.foundation.assembler.ResourceTypeAssembler;
import cz.muni.soa.foundation.domain.Foundation;
import cz.muni.soa.foundation.domain.FoundationOperations;
import cz.muni.soa.foundation.domain.defence.Defence;
import cz.muni.soa.foundation.domain.defence.active.ActiveDefence;
import cz.muni.soa.foundation.domain.defence.passive.PassiveDefence;
import cz.muni.soa.foundation.dto.DtoDefence;
import cz.muni.soa.foundation.dto.DtoResourceRequest;
import cz.muni.soa.foundation.repository.DefenceRepository;
import cz.muni.soa.foundation.repository.FoundationRepository;
import cz.muni.soa.foundation.repository.ProducerRepository;
import cz.muni.soa.infrastructure.security.AuthContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FoundationService {
    
    @Inject
    FoundationRepository repository;
    
    @Inject
    DefenceRepository defenceRepository;
    
    @Inject
    ProducerRepository producerRepository;
    
    @Inject
    AuthContext context;
    
    
    @Transactional
    public void create() throws Exception {
        FoundationOperations operations = new FoundationOperations(repository, defenceRepository, producerRepository);
        operations.createNewFoundation(context.getKingdomId());
    }

    public List<DtoDefence> getKingdomDefence(long kingdomId) {
        Foundation foundation = repository.ofKingdom(kingdomId);
        
        List<DtoDefence> defences = new ArrayList<>();
        
        for (Defence defence : foundation.getDefences()) {
            DtoDefence dto = new DtoDefence();
            dto.health = defence.getHealth();
            dto.armor = defence.getArmor();
            
            if (defence instanceof PassiveDefence) {
                dto.isPassive = true;
                dto.dps = 0;
            } else {
                dto.isPassive = false;
                dto.dps = ((ActiveDefence) defence).getDps();
            }
            
            defences.add(dto);
        }
        return defences;
    }
    
    @Transactional
    public void addResources(List<DtoResourceRequest> resourceRequests) {
        Foundation foundation = repository.ofKingdom(context.getKingdomId());
        
        for (DtoResourceRequest request : resourceRequests) {
            foundation.getStorage().add(ResourceTypeAssembler.fromDto(request.type), request.amount);
        }
        
        repository.persist(foundation);
    }
}

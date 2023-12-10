package cz.muni.soa.kingdom.service;

import cz.muni.soa.foundation.dto.DtoResourceRequest;
import cz.muni.soa.foundation.dto.DtoResourceType;
import cz.muni.soa.infrastructure.security.AuthContext;
import cz.muni.soa.kingdom.domain.Kingdom;
import cz.muni.soa.kingdom.proxy.FoundationProxy;
import cz.muni.soa.kingdom.proxy.WarfareProxy;
import cz.muni.soa.kingdom.repository.IKingdomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class KingdomService {

    @Inject
    IKingdomRepository kingdomRepository;

    @RestClient
    WarfareProxy warfareApi;
    
    @RestClient
    FoundationProxy foundationApi;
    

    @Transactional
    public long createKingdom(String name) {
        Kingdom kingdom = new Kingdom(name);
        kingdomRepository.persist(kingdom);
        return kingdom.getId();
    }
    
    public void initKingdom() throws Exception {
        warfareApi.initKingdomTroops();
        foundationApi.createFoundation();
        List<DtoResourceRequest> initialResources = new ArrayList<>();

        DtoResourceRequest wood = new DtoResourceRequest();
        wood.type = DtoResourceType.WOOD;
        wood.amount = 1200;

        DtoResourceRequest iron = new DtoResourceRequest();
        iron.type = DtoResourceType.IRON;
        iron.amount = 850;

        DtoResourceRequest gold = new DtoResourceRequest();
        gold.type = DtoResourceType.GOLD;
        gold.amount = 500;

        initialResources.addAll(List.of(wood, iron, gold));
        foundationApi.addResources(initialResources);
    }
}
  
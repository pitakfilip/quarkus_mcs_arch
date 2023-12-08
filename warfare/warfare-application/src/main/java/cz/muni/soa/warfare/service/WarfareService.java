package cz.muni.soa.warfare.service;

import cz.muni.soa.infrastructure.security.AuthContext;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;
import cz.muni.soa.warfare.repository.ITroopsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class WarfareService {

    @Inject
    ITroopsRepository troopsRepository;

    @Inject
    ITroopClassLevelRepository levelRepository;

    @Inject
    IKingdomsTroopsRepository kingdomsTroopsRepository;

    @Inject
    AuthContext context;
    
    
    public void initializeKingdomsDomain() {
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        warfareOperations.setUpLevelsForNewKingdom(context.getKingdomId());
        warfareOperations.setupKingdomsTroops(context.getKingdomId());
    }
}

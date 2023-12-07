package cz.muni.soa.warfare.resource;

import cz.muni.soa.warfare.api.WarfareApi;
import cz.muni.soa.warfare.dto.DtoKingdomTroopLevel;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;
import cz.muni.soa.warfare.repository.ITroopsRepository;
import cz.muni.soa.warfare.service.WarfareOperations;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;

@Path("/warfare")
public class WarfareResource implements WarfareApi {

    @Inject
    ITroopsRepository troopsRepository;

    @Inject
    ITroopClassLevelRepository levelRepository;

    @Inject
    IKingdomsTroopsRepository kingdomsTroopsRepository;

    WarfareOperations wfr = new WarfareOperations(kingdomsTroopsRepository,levelRepository,troopsRepository);

    @Transactional
    @Override
    public void initKingdomTroops(Long kingdomId) {
        wfr.setUpLevelsForNewKingdom(kingdomId);
        wfr.setupKingdomsTroops(kingdomId);
    }

}

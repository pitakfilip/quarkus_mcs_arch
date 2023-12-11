package cz.muni.soa.warfare.service;

import cz.muni.soa.foundation.dto.DtoResourceRequest;
import cz.muni.soa.foundation.dto.DtoResourceType;
import cz.muni.soa.infrastructure.security.AuthContext;
import cz.muni.soa.warfare.assembler.RequestAssembler;
import cz.muni.soa.warfare.assembler.TroopAssembler;
import cz.muni.soa.warfare.assembler.TroopClassAssembler;
import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopRequest;
import cz.muni.soa.warfare.dto.DtoTroop;
import cz.muni.soa.warfare.dto.DtoTroopClass;
import cz.muni.soa.warfare.dto.DtoTroopRequest;
import cz.muni.soa.warfare.proxy.ShopProxy;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;
import cz.muni.soa.warfare.repository.ITroopsRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;


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

    @RestClient
    ShopProxy shopProxy;
    
    
    public void initializeKingdomsDomain() {
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        warfareOperations.setUpLevelsForNewKingdom(context.getKingdomId());
        warfareOperations.setupKingdomsTroops(context.getKingdomId());
    }

    public List<DtoTroop> getAvailableTroops(Long id){
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        return TroopAssembler.toDto(warfareOperations.getAvailableTroops(id));
    }
    public void levelUpClass(DtoTroopClass tC){
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        warfareOperations.levelUpTroopClass(TroopClassAssembler.fromDto(tC), context.getKingdomId());
    }

    public void warResult(List<Long> dead, List<Long> survived,Long kingdomId){
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        warfareOperations.warResult(dead,survived,kingdomId);
    }

    public String trainTroops(List<DtoTroopRequest> requests) throws Exception {
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        List<TroopRequest> reqTroops = RequestAssembler.fromDto(requests);
//        int costOfRequest = warfareOperations.getCostOfRequest(reqTroops);
//
//        var fundsState = getResourcesOfFoundation(costOfRequest);
//
//        if (fundsState.missing > 0){
//            return "Missing gold";
//        }




        return warfareOperations.trainTroops(reqTroops, context.getKingdomId());
    }

    public DtoResourceRequest getResourcesOfFoundation( int requestCost) throws Exception {

        return shopProxy.hasResources(DtoResourceType.GOLD,requestCost);
    }

    public void troopsToWar(List<Long> dtoTroops){
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        List<Troop> t = troopsRepository.getById(dtoTroops);
        warfareOperations.sendTroopsToWar(t);

    }
}

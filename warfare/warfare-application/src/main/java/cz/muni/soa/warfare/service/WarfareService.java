package cz.muni.soa.warfare.service;

import cz.muni.soa.infrastructure.security.AuthContext;
import cz.muni.soa.warfare.assembler.RequestAssembler;
import cz.muni.soa.warfare.assembler.TroopAssembler;
import cz.muni.soa.warfare.assembler.TroopClassAssembler;
import cz.muni.soa.warfare.assembler.TroopTypeAssembler;
import cz.muni.soa.warfare.domain.KingdomsTroops;
import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopClass;
import cz.muni.soa.warfare.domain.troop.TroopClassLevel;
import cz.muni.soa.warfare.domain.troop.melee.CalvarySword;
import cz.muni.soa.warfare.domain.troop.melee.InfantrySword;
import cz.muni.soa.warfare.domain.troop.melee.MaceMan;
import cz.muni.soa.warfare.domain.troop.ranged.Archer;
import cz.muni.soa.warfare.domain.troop.ranged.CrossBowTroop;
import cz.muni.soa.warfare.domain.troop.siege.RamVehicle;
import cz.muni.soa.warfare.domain.troop.siege.Trebuchet;
import cz.muni.soa.warfare.dto.DtoTroop;
import cz.muni.soa.warfare.dto.DtoTroopClass;
import cz.muni.soa.warfare.dto.DtoTroopRequest;
import cz.muni.soa.warfare.dto.DtoTroopType;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;
import cz.muni.soa.warfare.repository.ITroopsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    
    public void initializeKingdomsDomain() {
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        warfareOperations.setUpLevelsForNewKingdom(context.getKingdomId());
        warfareOperations.setupKingdomsTroops(context.getKingdomId());
    }

    public List<DtoTroop> getAvailableTroops(){
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        return TroopAssembler.toDto(warfareOperations.getAvailableTroops(context.getKingdomId()));
    }
    public void levelUpClass(DtoTroopClass tC){
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        warfareOperations.levelUpTroopClass(TroopClassAssembler.fromDto(tC), context.getKingdomId());
    }

    public void warResult(List<Long> dead, List<Long> survived){
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        warfareOperations.warResult(dead,survived,context.getKingdomId());
    }

    public String trainTroops(List<DtoTroopRequest> requests){
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        return warfareOperations.trainTroops(RequestAssembler.fromDto(requests), context.getKingdomId());
    }

    public void troopsToWar(List<Long> dtoTroops){
        WarfareOperations warfareOperations = new WarfareOperations(kingdomsTroopsRepository,
                levelRepository,
                troopsRepository);
        List<Troop> t = troopsRepository.getById(dtoTroops);
        warfareOperations.sendTroopsToWar(t);
    }
}

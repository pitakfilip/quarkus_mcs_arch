package cz.muni.soa.warfare.service;

import cz.muni.soa.warfare.domain.KingdomsTroops;
import cz.muni.soa.warfare.domain.troop.TroopClassLevel;
import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopClass;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;
import cz.muni.soa.warfare.repository.ITroopsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WarfareOperations {
    private final IKingdomsTroopsRepository kTRepo;

    private final ITroopClassLevelRepository levelRepo;

    private final ITroopsRepository troopRepository;

    public WarfareOperations(IKingdomsTroopsRepository kTRepo, ITroopClassLevelRepository level, ITroopsRepository troopRepository) {
        this.kTRepo = kTRepo;
        this.levelRepo = level;
        this.troopRepository = troopRepository;
    }

    public void levelUpTroopClass(TroopClass tC, Long kingdomId) {
        levelRepo.levelUpTroopClass(tC, kingdomId);
    }

    public void setUpLevelsForNewKingdom(Long kingdomId) {
        Map<TroopClass, Integer> map = Map.of(TroopClass.CALVARYSWORD, 1,
                TroopClass.INFANTRYSWORD, 1,
                TroopClass.MACEMAN, 1,
                TroopClass.ARCHER, 1,
                TroopClass.CROSSBOW_TROOP, 1,
                TroopClass.RAM_VEHICLE, 1,
                TroopClass.TREBUCHET, 1);

        TroopClassLevel lvl = new TroopClassLevel();
        lvl.setKingdomId(kingdomId);
        lvl.setTroopLevel(map);
        levelRepo.persist(lvl);
    }

    public void setupKingdomsTroops(Long kingdomId){
        KingdomsTroops kT = new KingdomsTroops();
        kT.setId(kingdomId);
        kT.setTroops(new ArrayList<>());
        kTRepo.persist(kT);
    }

    public void addTroopsToKingdom(List<Troop> newTroops, Long kingdomId){
        KingdomsTroops kt = kTRepo.getById(kingdomId);
        kt.getTroops().addAll(newTroops);
        kTRepo.persist(kt);
    }


    public List<Troop> getAvailableTroops(Long kingdomId){
        KingdomsTroops kt = kTRepo.getById(kingdomId);
        List<Troop> troopList = kt.getTroops();
        return troopList.stream()
                .filter(troop -> !troop.isAtWar())
                .toList();
    }


    public void removeFallenTroops(List<Troop> deceasedTroops,Long kingdomId){
        kTRepo.deleteKingdomsTroopsList(deceasedTroops, kingdomId);
        troopRepository.deleteTroopsList(deceasedTroops);
    }

    public void setupCostsOfTroopClasses(){

    }

//    public List<Troop>




}

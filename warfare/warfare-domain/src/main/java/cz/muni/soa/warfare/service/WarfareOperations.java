package cz.muni.soa.warfare.service;

import cz.muni.soa.warfare.domain.KingdomsTroops;
import cz.muni.soa.warfare.domain.LevelOfTroopClass;
import cz.muni.soa.warfare.domain.Troop;
import cz.muni.soa.warfare.domain.TroopClass;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;

import java.util.List;
import java.util.Map;

public class WarfareOperations {
    private final IKingdomsTroopsRepository kTRepo;

    private final ITroopClassLevelRepository levelRepo;

    public WarfareOperations(IKingdomsTroopsRepository kTRepo, ITroopClassLevelRepository repository) {
        this.kTRepo = kTRepo;
        this.levelRepo = repository;
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

        LevelOfTroopClass lvl = new LevelOfTroopClass();
        lvl.setKingdomId(kingdomId);
        lvl.setTroopLevel(map);
        levelRepo.persist(lvl);
    }

    public void addTroopsToKingdom(List<Troop> newTroops, Long kingdomId){
        KingdomsTroops kt = kTRepo.getById(kingdomId);
        List<Troop> troopList = kt.getTroops();
        troopList.addAll(newTroops);
        kTRepo.persist(kt);

    }


    public List<Troop> getAvailableTroops(Long kingdomId){
        return null;
    }


    public void evaluateBattleAftermath(Long kingdomId){

    }




}

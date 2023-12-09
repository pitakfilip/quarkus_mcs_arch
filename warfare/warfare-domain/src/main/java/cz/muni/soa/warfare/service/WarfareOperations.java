package cz.muni.soa.warfare.service;

import cz.muni.soa.warfare.domain.KingdomsTroops;
import cz.muni.soa.warfare.domain.troop.*;
import cz.muni.soa.warfare.domain.troop.melee.CalvarySword;
import cz.muni.soa.warfare.domain.troop.melee.InfantrySword;
import cz.muni.soa.warfare.domain.troop.melee.MaceMan;
import cz.muni.soa.warfare.domain.troop.ranged.Archer;
import cz.muni.soa.warfare.domain.troop.ranged.CrossBowTroop;
import cz.muni.soa.warfare.domain.troop.siege.RamVehicle;
import cz.muni.soa.warfare.domain.troop.siege.Trebuchet;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;
import cz.muni.soa.warfare.repository.ITroopsRepository;

import java.util.*;

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
        TroopClassLevel entity = levelRepo.getById(kingdomId);
        var mapa = entity.getTroopLevel();
        int troopClassLevel = mapa.get(tC);
        troopClassLevel++;
        mapa.put(tC, troopClassLevel);
        levelRepo.persist(entity);
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

    public void setupKingdomsTroops(Long kingdomId) {
        KingdomsTroops kT = new KingdomsTroops();
        kT.setId(kingdomId);
        kT.setTroops(new ArrayList<>());
        kTRepo.persist(kT);
    }

    public void addTroopsToKingdom(List<Troop> newTroops, Long kingdomId) {
        troopRepository.persist(newTroops);
        KingdomsTroops kt = kTRepo.getById(kingdomId);
        kt.getTroops().addAll(newTroops);
        kTRepo.persist(kt);
    }


    public List<Troop> getAvailableTroops(Long kingdomId) {
        KingdomsTroops kt = kTRepo.getById(kingdomId);
        List<Troop> troopList = kt.getTroops();
        return troopList.stream()
                .filter(troop -> !troop.isAtWar())
                .toList();
    }


    public List<Troop> initMockTroops() {

        Troop Calvar = new CalvarySword(1);
        Troop Infantry = new InfantrySword(1);
        Infantry.setAtWar(true);
        Troop Mace = new MaceMan(1);
        Troop Archer = new Archer(1);
        Archer.setAtWar(true);

        Troop Cross = new CrossBowTroop(1);
        Troop Ram = new RamVehicle(1);
        Troop Trebuchet = new Trebuchet(1);
        return new ArrayList<>(Arrays.asList(Calvar, Infantry, Mace, Archer, Cross, Ram, Trebuchet));

    }



    public void warResult(List<Long> deceased, List<Long> survivors, Long kingdomId) {
        List<Troop> dec = troopRepository.getById(deceased);

        KingdomsTroops kingdomsTroops = kTRepo.getById(kingdomId);
        List<Troop> kTList = kTRepo.getAllTroops(kingdomId);


        var updatedKingdomsTroops = returnSoldiers(survivors,kTList);
        kingdomsTroops.setTroops(updatedKingdomsTroops);

        removeFallenTroops(dec,kingdomId);

        System.out.println("ya");
    }

    public boolean checkIfCanPurchaseTroop(List<TroopRequest> requests, int kingdomMoney) {
        int sum = 0;

        for (var t : requests){
            var r = TroopFactory.create(t.clazz);
            sum += r.getCost();
        }
        return sum <= kingdomMoney;
    }


    public void removeFallenTroops(List<Troop> deceasedTroops, Long kingdomId) {
        kTRepo.deleteKingdomsTroopsList(deceasedTroops, kingdomId);
        troopRepository.deleteTroopsList(deceasedTroops);
    }

    public List<Troop> returnSoldiers(List<Long> returnedIds, List<Troop> allSoldierOfKingdom){

        for (int i = 0; i < returnedIds.size(); i++) {
            for (int j = 0; j < allSoldierOfKingdom.size(); j++) {
                if (Objects.equals(returnedIds.get(i), allSoldierOfKingdom.get(j).getId())){
                    allSoldierOfKingdom.get(j).setAtWar(false);
                }
            }
        }
        return new ArrayList<>(allSoldierOfKingdom);
    }

    public String trainTroops(List<TroopRequest> tR, Long kingdomId){
        if(!checkIfCanPurchaseTroop(tR,60)) return "Insufficient funds";
        List<Troop> result = new ArrayList<>();

        for (TroopRequest troopRequest : tR) {
            if(!checkIfLevelOfRequestedTroop(troopRequest,kingdomId)) return "Troop of wanted level not trainable";
            List<Troop> temp = new ArrayList<>(getInstances(troopRequest));
            result.addAll(temp);
        }
        troopRepository.persist(result);
        KingdomsTroops kt = kTRepo.getById(kingdomId);
        kt.getTroops().addAll(result);
        kTRepo.persist(kt);

        return "Troops successfully trained";

    }

    private List<Troop> getInstances(TroopRequest tR){
        List<Troop> res = new ArrayList<>();
        for (int i = 0; i < tR.amount; i++) {
            Troop t = TroopFactory.create(tR.clazz);
            t.setLevel(tR.level);
            res.add(t);
        }
        return res;
    }

    private boolean checkIfLevelOfRequestedTroop(TroopRequest tR, Long kingdomId){
        TroopClassLevel entity = levelRepo.getById(kingdomId);
        var mapa = entity.getTroopLevel();
        int troopClassLevel = mapa.get(tR.clazz);
        return troopClassLevel >= tR.level;
    }

    public void sendTroopsToWar(List<Troop> troops){

        for (var t : troops){
            t.setAtWar(true);
        }
        troopRepository.persist(troops);
    }

}

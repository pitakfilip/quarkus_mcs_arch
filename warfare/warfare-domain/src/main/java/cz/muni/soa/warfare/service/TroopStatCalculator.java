package cz.muni.soa.warfare.service;

import cz.muni.soa.warfare.domain.troop.Troop;

import java.util.List;

public class TroopStatCalculator {
//    private final IKingdomsTroopsRepository repository;
//
//    public TroopStatCalculator(IKingdomsTroopsRepository repository) {
//        this.repository = repository;
//    }

    /**
    Kedze mame tabulky zvlast na level a zvlast na troops kingdomu, ked chceme specific
     instances troopov jedneho kingdomu pre simulaciu, tak trebalo takto prepocitat podla
     levelu stats current instancie
     **/
    public List<Troop> getAllCalcTroops(List<Troop> troops){ //prerobit na male staticke metody ktore sa bude volat v assembleri


        for (var troop : troops) {
            int currHP = troop.getHp();
            int currDPS = troop.getDps();
            int currArmor = troop.getArmor();
            int currLevel = troop.getLevel();
            troop.setHp(currHP + (currLevel - 1) * (int)(currHP * 0.5));
            troop.setDps(currDPS + (currLevel -1) * (int)((currDPS) * 0.4));
            if (currArmor == 0) currArmor = 5;
            troop.setArmor(currArmor + (currLevel -1) * (int)((currArmor) * 0.3));
        }

        return troops;
    }

    public static int getHpByLevel(int hp, int level){
        return hp + (level - 1) * (int)(hp * 0.5);
    }

    public static int getDPSByLevel(int dps, int level){
        return dps + (level -1) * (int)((dps) * 0.4);
    }

    public static int getArmorByLevel(int armor, int level){
        if (armor == 0) armor = 5;
        return armor + (level -1) * (int)((armor) * 0.3);
    }

    public static int getBasicHp(int currentHp, int level) {
        return currentHp - (level - 1) * (int) (currentHp * 0.5);
    }

    public static int getBasicDPS(int currentDPS, int level) {
        return currentDPS - (level - 1) * (int) (currentDPS * 0.4);
    }

    public static int getBasicArmor(int currentArmor, int level) {
        if (currentArmor == 0) currentArmor = 5;
        return currentArmor - (level - 1) * (int) (currentArmor * 0.3);
    }





}

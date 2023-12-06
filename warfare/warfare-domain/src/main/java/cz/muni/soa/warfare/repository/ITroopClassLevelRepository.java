package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.LevelOfTroopClass;
import cz.muni.soa.warfare.domain.Troop;
import cz.muni.soa.warfare.domain.TroopClass;

import java.util.Map;

public interface ITroopClassLevelRepository {
    LevelOfTroopClass getById(Long id);

    Map<TroopClass,Integer> getAllTroopClassLevels(Long kingdomId);

    int getLevelOfTroopClass(TroopClass troopClass, Long kingdomId);

    void persist(LevelOfTroopClass l);

    void levelUpTroopClass(TroopClass troopClass, Long kingdomId);

    void deleteClassLevels(Long kingdomId);

}
package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.troop.TroopClassLevel;
import cz.muni.soa.warfare.domain.troop.TroopClass;

import java.util.Map;

public interface ITroopClassLevelRepository {
    TroopClassLevel getById(Long id);

    Map<TroopClass,Integer> getAllTroopClassLevels(Long kingdomId);

    int getLevelOfTroopClass(TroopClass troopClass, Long kingdomId);

    void persist(TroopClassLevel l);


    void deleteClassLevels(Long kingdomId);

}

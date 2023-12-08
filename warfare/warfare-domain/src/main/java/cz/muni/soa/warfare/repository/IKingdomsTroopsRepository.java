package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.KingdomsTroops;
import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopClass;
import cz.muni.soa.warfare.domain.troop.TroopType;

import java.util.List;

public interface IKingdomsTroopsRepository {
    KingdomsTroops getById(Long id);
    List<Troop> getAllTroops(Long kingdomId);

    List<Troop> getAllTroopsOfClass(TroopClass troopClass, Long kingdomId);

    List<Troop> getAllTroopsOfType(TroopType troopType, Long kingdomId);
    void persist(KingdomsTroops kT);

    void deleteKingdomsTroops(Troop t, Long kingdomId);

    void deleteKingdomsTroopsList(List<Troop> troops,Long kingdomId);



}

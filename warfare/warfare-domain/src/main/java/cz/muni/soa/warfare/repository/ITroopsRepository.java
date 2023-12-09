package cz.muni.soa.warfare.repository;


import cz.muni.soa.warfare.domain.troop.Troop;

import java.util.List;

public interface ITroopsRepository {
    Troop getById(Long id);
    void persist(Troop t);

    void persist(List<Troop> t);

    List<Troop> getAll();

    List<Troop> getById(List<Long> troopIds);

    void deleteTroop(Troop t);

    void deleteTroopsList(List<Troop> troops);

    void updateToWar(List<Long> ids);
}

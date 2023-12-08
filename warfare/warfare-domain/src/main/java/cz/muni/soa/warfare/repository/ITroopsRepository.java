package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.troop.Troop;

import java.util.List;

public interface ITroopsRepository {
    
    Troop getById(Long id);
    
    void persist(Troop t);

    List<Troop> getAll();

    void deleteTroop(Troop t);

    void deleteTroopsList(List<Troop> troops);
}

package cz.muni.soa.simulation.repository;

import cz.muni.soa.simulation.domain.Troop;

import java.util.List;

public interface ITroopRepository {

    Troop getById(Long id);

    void persist(Troop troop);

    void persist(List<Troop> troopList);
}

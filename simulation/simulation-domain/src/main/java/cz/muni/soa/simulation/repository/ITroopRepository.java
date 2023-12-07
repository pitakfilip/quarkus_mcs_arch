package cz.muni.soa.simulation.repository;

import cz.muni.soa.simulation.domain.Troop;

public interface ITroopRepository {

    Troop getById(Long id);

    void persist(Troop troop);
}

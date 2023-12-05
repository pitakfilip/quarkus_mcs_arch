package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.Troop;

import java.util.List;

public interface ITroopsRepository {
    void persist(Troop t);

    List<Troop> getAll();
}

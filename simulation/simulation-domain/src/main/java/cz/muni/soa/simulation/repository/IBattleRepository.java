package cz.muni.soa.simulation.repository;

import cz.muni.soa.simulation.domain.Battle;

import java.util.List;

public interface IBattleRepository {

    Battle getById(Long id);

    void persist(Battle battle);
}

package cz.muni.soa.simulation.repository;

import cz.muni.soa.simulation.domain.Battle;

public interface IBattleRepository {

    Battle getById(Long id);

    void persist(Battle battle);
}

package cz.muni.soa.simulation.repository;

import cz.muni.soa.simulation.domain.Battle;
import cz.muni.soa.simulation.domain.BattleStatus;

import java.util.Optional;

public interface IBattleRepository {

    Battle getById(Long id);

    void persist(Battle battle);

    public Optional<Battle> getFirstUnfinishedBattle();
}

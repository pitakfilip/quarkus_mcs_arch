package cz.muni.soa.simulation.repository;

import cz.muni.soa.simulation.domain.Battle;
import cz.muni.soa.simulation.domain.BattleStatus;
import cz.muni.soa.simulation.repository.panache.BattlePanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class BattleRepository implements IBattleRepository {

    @Inject
    BattlePanacheRepository battleRepository;

    @Override
    public Battle getById(Long id) {
        return battleRepository.findById(id);
    }

    @Override
    public void persist(Battle battle) {
        battleRepository.create(battle);
    }

    @Override
    public Optional<Battle> getFirstUnfinishedBattle() {
        return battleRepository.getFirstUnfinishedBattle();
    }
}

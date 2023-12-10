package cz.muni.soa.simulation.repository.panache;

import cz.muni.soa.simulation.domain.Battle;
import cz.muni.soa.simulation.domain.BattleStatus;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class BattlePanacheRepository implements PanacheRepository<Battle> {
    public void create(Battle battle) {
        persist(battle);
    }

    public Optional<Battle> getFirstUnfinishedBattle() {
        return find("status <> ?1", BattleStatus.FINISHED).firstResultOptional();
    }
}

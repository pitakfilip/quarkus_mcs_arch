package cz.muni.soa.simulation.repository.panache;

import cz.muni.soa.simulation.domain.Battle;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BattlePanacheRepository implements PanacheRepository<Battle> {
    public void create(Battle battle) {
        persist(battle);
    }
}

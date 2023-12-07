package cz.muni.soa.simulation.repository.panache;

import cz.muni.soa.simulation.domain.Troop;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TroopPanacheRepository implements PanacheRepository<Troop> {
    public void create(Troop troop) {
        persist(troop);
    }
}

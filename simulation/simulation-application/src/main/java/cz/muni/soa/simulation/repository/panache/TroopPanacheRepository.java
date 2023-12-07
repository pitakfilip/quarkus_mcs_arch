package cz.muni.soa.simulation.repository.panache;

import cz.muni.soa.simulation.domain.Troop;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class TroopPanacheRepository implements PanacheRepository<Troop> {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Troop troop) {
        persist(troop);
    }

    public void create(List<Troop> troopList) {
        for (Troop troop : troopList) {
            Troop managedTroop = entityManager.merge(troop);
            entityManager.persist(managedTroop);
        }
    }
}

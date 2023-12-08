package cz.muni.soa.warfare.repository.panache;

import cz.muni.soa.warfare.domain.troop.TroopClassLevel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TroopClassLevelPanacheRepository implements PanacheRepository<TroopClassLevel> {
}

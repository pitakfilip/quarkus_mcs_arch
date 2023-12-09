package cz.muni.soa.warfare.repository.panache;

import cz.muni.soa.warfare.domain.troop.Troop;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TroopsPanacheRepository implements PanacheRepository<Troop> {


}

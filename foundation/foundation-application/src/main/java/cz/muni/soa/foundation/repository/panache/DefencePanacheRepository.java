package cz.muni.soa.foundation.repository.panache;

import cz.muni.soa.foundation.domain.defence.Defence;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DefencePanacheRepository implements PanacheRepository<Defence> {
}

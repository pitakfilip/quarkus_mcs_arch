package cz.muni.soa.foundation.repository.panache;

import cz.muni.soa.foundation.domain.Foundation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class FoundationPanacheRepository implements PanacheRepository<Foundation> {
}

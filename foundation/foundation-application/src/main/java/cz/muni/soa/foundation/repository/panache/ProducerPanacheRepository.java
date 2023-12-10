package cz.muni.soa.foundation.repository.panache;

import cz.muni.soa.foundation.domain.resource.producer.ResourceProducer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProducerPanacheRepository implements PanacheRepository<ResourceProducer> {
}

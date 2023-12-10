package cz.muni.soa.foundation.repository;

import cz.muni.soa.foundation.domain.repository.IProducerRepository;
import cz.muni.soa.foundation.domain.resource.producer.ResourceProducer;
import cz.muni.soa.foundation.repository.panache.ProducerPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProducerRepository implements IProducerRepository {
    
    @Inject
    ProducerPanacheRepository repository;
    
    @Override
    public void persist(ResourceProducer producer) {
        repository.persist(producer);
    }

    @Override
    public void persist(List<ResourceProducer> producers) {
        repository.persist(producers);
    }
}

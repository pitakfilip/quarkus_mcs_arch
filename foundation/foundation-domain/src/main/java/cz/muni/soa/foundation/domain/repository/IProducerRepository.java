package cz.muni.soa.foundation.domain.repository;

import cz.muni.soa.foundation.domain.resource.producer.ResourceProducer;

import java.util.List;

public interface IProducerRepository {
    
    void persist(ResourceProducer producer);
    
    void persist(List<ResourceProducer> producers);
}

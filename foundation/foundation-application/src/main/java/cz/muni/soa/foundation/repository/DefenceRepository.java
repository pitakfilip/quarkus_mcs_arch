package cz.muni.soa.foundation.repository;

import cz.muni.soa.foundation.domain.defence.Defence;
import cz.muni.soa.foundation.domain.repository.IDefenceRepository;
import cz.muni.soa.foundation.repository.panache.DefencePanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DefenceRepository implements IDefenceRepository {
    
    @Inject
    DefencePanacheRepository repository;
    
    @Override
    public void persist(Defence defence) {
        repository.persist(defence);
    }

    @Override
    public void persist(List<Defence> defences) {
        repository.persist(defences);
    }
}

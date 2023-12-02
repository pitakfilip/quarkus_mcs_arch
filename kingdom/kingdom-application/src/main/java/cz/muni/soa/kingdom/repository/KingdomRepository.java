package cz.muni.soa.kingdom.repository;

import cz.muni.soa.kingdom.domain.Kingdom;
import cz.muni.soa.kingdom.repository.panache.KingdomPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class KingdomRepository implements IKingdomRepository {

    @Inject
    KingdomPanacheRepository repository;

    @Override
    public Kingdom getById(long id) {
        return repository.findById(id);
    }

    @Override
    public void persist(Kingdom kingdom) {
        repository.create(kingdom);
    }
}

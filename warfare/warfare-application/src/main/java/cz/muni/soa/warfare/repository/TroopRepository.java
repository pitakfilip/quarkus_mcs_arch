package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.Troop;
import cz.muni.soa.warfare.repository.panache.KingdomsTroopPanacheRepository;
import cz.muni.soa.warfare.repository.panache.TroopsPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TroopRepository implements ITroopsRepository{

    @Inject
    TroopsPanacheRepository troopRepo;
    @Transactional
    @Override
    public void persist(Troop t) {
        troopRepo.persist(t);
    }

    @Override
    public List<Troop> getAll() {
        return troopRepo.listAll();
    }


}

package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.repository.panache.TroopsPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TroopRepository implements ITroopsRepository{

    @Inject
    TroopsPanacheRepository troopRepo;

    @Override
    public Troop getById(Long id) {
        return troopRepo.findById(id);
    }


    @Override
    public void persist(Troop t) {
        troopRepo.persist(t);
    }

    @Override
    public List<Troop> getAll() {
        return troopRepo.listAll();
    }

    @Override
    public void deleteTroop(Troop t) {
        troopRepo.delete(t);
    }

    @Override
    public void deleteTroopsList(List<Troop> troops) {
        troops.forEach(this::deleteTroop);
    }


}

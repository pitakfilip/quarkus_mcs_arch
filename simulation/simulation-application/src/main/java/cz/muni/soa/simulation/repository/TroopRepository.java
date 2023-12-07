package cz.muni.soa.simulation.repository;

import cz.muni.soa.simulation.domain.Troop;
import cz.muni.soa.simulation.repository.panache.TroopPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TroopRepository implements ITroopRepository {

    @Inject
    TroopPanacheRepository troopRepository;

    @Override
    public Troop getById(Long id) {
        return troopRepository.findById(id);
    }

    @Override
    public void persist(Troop troop) {
        troopRepository.create(troop);
    }

    @Override
    public void persist(List<Troop> troopList) {
        troopRepository.create(troopList);
    }
}

package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.KingdomsTroops;
import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopClass;
import cz.muni.soa.warfare.domain.troop.TroopType;
import cz.muni.soa.warfare.repository.panache.KingdomsTroopPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class KingdomsTroopsRepository implements IKingdomsTroopsRepository {

    @Inject
    KingdomsTroopPanacheRepository troopRepo;

    @Override
    public KingdomsTroops getById(Long id) {
        return troopRepo.findById(id);
    }

    @Override
    public List<Troop> getAllTroops(Long kingdomId) {
        KingdomsTroops kT = getById(kingdomId);
        return kT.getTroops();
//        return troopRepo.list("WHERE kingdomid = ?1",kingdomId);
    }

    @Override
    public List<Troop> getAllTroopsOfClass(TroopClass troopClass, Long kingdomId) {
//        List<Troop> temp = getAllTroops(kingdomId);
//        return temp.stream()
//                .filter(troop -> troop.getTroopClass().equals(troopClass))
//                .collect(Collectors.toList());

//        return troopRepo.list("troopclass", troopClass);

        KingdomsTroops kT = getById(kingdomId);
        return kT.getTroops().stream().filter(troop ->
                troop.getTroopClass().equals(troopClass)).collect(Collectors.toList());
    }

    @Override
    public List<Troop> getAllTroopsOfType(TroopType troopType, Long kingdomId) {
        KingdomsTroops kingdomsTroops = getById(kingdomId);
        return kingdomsTroops.getTroops().stream().filter(troop ->
                troop.getTroopType().equals(troopType)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void persist(KingdomsTroops kingdomsTroops) {
        troopRepo.persist(kingdomsTroops);
    }

    @Override
    public void deleteKingdomsTroops(Troop troop, Long kingdomId) {
        KingdomsTroops kingdomsTroops = troopRepo.findById(kingdomId);
        List<Troop> troops = kingdomsTroops.getTroops();
        troops.remove(troop);
        kingdomsTroops.setTroops(troops);

    }

    @Override
    public void deleteKingdomsTroopsList(List<Troop> forRemoval, Long kingdomId) {
        KingdomsTroops kingdomsTroops = troopRepo.findById(kingdomId);
        List<Troop> troops = kingdomsTroops.getTroops();
        troops.removeAll(forRemoval);
        kingdomsTroops.setTroops(troops);
    }


}

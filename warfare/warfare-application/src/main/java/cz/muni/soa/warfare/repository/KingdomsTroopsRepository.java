package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.KingdomsTroops;
import cz.muni.soa.warfare.domain.Troop;
import cz.muni.soa.warfare.domain.TroopClass;
import cz.muni.soa.warfare.domain.TroopType;
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
//        List<Troop> temp = getAllTroops(kingdomId);
//        return temp.stream()
//                .filter(troop -> troop.getTroopType().equals(troopType))
//                .collect(Collectors.toList());
//        return troopRepo.list("trooptype", troopType);
        KingdomsTroops kT = getById(kingdomId);
        return kT.getTroops().stream().filter(troop ->
                troop.getTroopType().equals(troopType)).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void persist(KingdomsTroops kT) {
        troopRepo.persist(kT);
    }

    @Override
    public void deleteKingdomsTroops(Troop t, Long kingdomId) {
        KingdomsTroops kT = troopRepo.findById(kingdomId);
        List<Troop> kTTroops = kT.getTroops();
        kTTroops.remove(t);
        kT.setTroops(kTTroops);

    }

    @Override
    public void deleteKingdomsTroopsList(List<Troop> troops,Long kingdomId) {
        KingdomsTroops kT = troopRepo.findById(kingdomId);
        List<Troop> kTTroops = kT.getTroops();
        kTTroops.removeAll(troops);
        kT.setTroops(kTTroops);
    }


}

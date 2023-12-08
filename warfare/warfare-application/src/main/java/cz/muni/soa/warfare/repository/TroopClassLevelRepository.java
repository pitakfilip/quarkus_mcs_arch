package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.troop.TroopClassLevel;
import cz.muni.soa.warfare.domain.troop.TroopClass;
import cz.muni.soa.warfare.repository.panache.TroopClassLevelPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Map;

@ApplicationScoped
public class TroopClassLevelRepository implements ITroopClassLevelRepository {
    
    @Inject
    TroopClassLevelPanacheRepository repo;

    
    @Override
    public TroopClassLevel getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Map<TroopClass, Integer> getAllTroopClassLevels(Long kingdomId) {
        TroopClassLevel entity = getById(kingdomId);
        return entity.getTroopLevel();
    }

    @Override
    public int getLevelOfTroopClass(TroopClass troopClass, Long kingdomId) {
        TroopClassLevel entity = repo.findById(kingdomId);
        return entity != null ? entity.getTroopLevel().get(troopClass) : -1;
    }

    @Transactional
    @Override
    public void persist(TroopClassLevel classLevel) {
        repo.persist(classLevel);
    }

    @Override
    public void levelUpTroopClass(TroopClass troopClass, Long kingdomId) {
        TroopClassLevel entity = repo.findById(kingdomId);
        var mapa = entity.getTroopLevel();
        int troopClassLevel = mapa.get(troopClass);
        troopClassLevel++;
        mapa.put(troopClass, troopClassLevel);
    }

    @Override
    public void deleteClassLevels(Long kingdomId) {
        repo.delete(repo.findById(kingdomId));
    }
}

package cz.muni.soa.warfare.repository;

import cz.muni.soa.warfare.domain.LevelOfTroopClass;
import cz.muni.soa.warfare.domain.TroopClass;
import cz.muni.soa.warfare.repository.panache.TroopClassLevelPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Map;

@ApplicationScoped
public class TroopClassLevelRepository implements ITroopClassLevelRepository{
    @Inject
    TroopClassLevelPanacheRepository repo;

    @Override
    public Map<TroopClass, Integer> getAllTroopClassLevels(Long kingdomId) {
        LevelOfTroopClass entity = repo.findById(kingdomId);
        return entity.getTroopLevel();
    }

    @Override
    public int getLevelOfTroopClass(TroopClass troopClass, Long kingdomId) {
        LevelOfTroopClass entity = repo.findById(kingdomId);
        return entity != null ? entity.getTroopLevel().get(troopClass) : null;
    }
    @Transactional
    @Override
    public void persist(LevelOfTroopClass l) {
        repo.persist(l);
    }

    @Override
    public void levelUpTroopClass(TroopClass troopClass, Long kingdomId) {
        LevelOfTroopClass entity = repo.findById(kingdomId);
        var mapa = entity.getTroopLevel();
        int troopClassLevel = mapa.get(troopClass);
        troopClassLevel++;
        mapa.put(troopClass,troopClassLevel);
    }
}

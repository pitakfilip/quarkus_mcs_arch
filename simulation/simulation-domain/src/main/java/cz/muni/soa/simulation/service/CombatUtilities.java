package cz.muni.soa.simulation.service;

import cz.muni.soa.simulation.domain.Battle;
import cz.muni.soa.simulation.domain.Troop;
import cz.muni.soa.simulation.domain.TroopType;
import cz.muni.soa.simulation.repository.IBattleRepository;
import cz.muni.soa.simulation.repository.ITroopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.max;

public class CombatUtilities {

    private final ITroopRepository troopRepository;
    private final IBattleRepository battleRepository;

    public CombatUtilities(ITroopRepository troopRepository, IBattleRepository battleRepository) {
        this.troopRepository = troopRepository;
        this.battleRepository = battleRepository;
    }

    // valid values for targets: [-2, number of enemies)
    // -2 and -1 are special values that result in no attack

    public boolean isEligibleTarget(Troop troop, Troop enemy, boolean enemyHasLivingMeleeTroops) {
        if (enemy.getHp() <= 0) {
            return false;
        }
        if (troop.getDps() <= enemy.getArmor()) {
            return false;
        }
        if (troop.getTroopType() == TroopType.MELEE && enemy.getTroopType() == TroopType.RANGED
                && enemyHasLivingMeleeTroops) {
            return false;
        }
        return true;
    }

    public boolean hasLivingMeleeTroops(List<Troop> troops) {
        for (Troop troop : troops) {
            if (troop.getHp() > 0 && troop.getTroopType() == TroopType.MELEE) {
                return true;
            }
        }
        return false;
    }

    public boolean hasLivingRangedTroops(List<Troop> troops) {
        for (Troop troop : troops) {
            if (troop.getHp() > 0 && troop.getTroopType() == TroopType.RANGED) {
                return true;
            }
        }
        return false;
    }


    public boolean hasBeenDestroyed(List<Troop> troops) {
        for (Troop troop : troops) {
            if (troop.getHp() > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean hasNoValidTargets(List<Troop> troops) {
        for (Troop troop : troops) {
            if (troop.getTarget() > 0) {
                return false;
            }
        }
        return true;
    }

    public List<Long> getDeceasedTroops(List<Troop> troops) {
        List<Long> deceasedTroops = new ArrayList<>();
        for (Troop troop : troops) {
            if (troop.getHp() <= 0) {
                deceasedTroops.add(troop.getId());
            }
        }
        return deceasedTroops;
    }

    public List<Long> getSurvivorTroops(List<Troop> troops) {
        List<Long> survivorTroops = new ArrayList<>();
        for (Troop troop : troops) {
            if (troop.getHp() > 0) {
                survivorTroops.add(troop.getId());
            }
        }
        return survivorTroops;
    }
}

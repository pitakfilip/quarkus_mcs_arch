package cz.muni.soa.simulation.service;

import cz.muni.soa.simulation.domain.Battle;
import cz.muni.soa.simulation.domain.Troop;
import cz.muni.soa.simulation.domain.TroopType;
import cz.muni.soa.simulation.repository.IBattleRepository;
import cz.muni.soa.simulation.repository.ITroopRepository;

import java.util.List;
import java.util.Random;

import static java.lang.Integer.max;

public class CombatUtilities {
    private final Random random = new Random();
    private final boolean log = true;

    private final ITroopRepository troopRepository;
    private final IBattleRepository battleRepository;

    public CombatUtilities(ITroopRepository troopRepository, IBattleRepository battleRepository) {
        this.troopRepository = troopRepository;
        this.battleRepository = battleRepository;
    }

    // valid values for targets: [-2, number of enemies)
    // -2 and -1 are special values that result in no attack

    public static boolean isEligibleTarget(Troop troop, Troop enemy, boolean enemyHasLivingMeleeTroops) {
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

    public static boolean hasLivingMeleeTroops(List<Troop> troops) {
        for (Troop troop : troops) {
            if (troop.getHp() > 0 && troop.getTroopType() == TroopType.MELEE) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasLivingRangedTroops(List<Troop> troops) {
        for (Troop troop : troops) {
            if (troop.getHp() > 0 && troop.getTroopType() == TroopType.RANGED) {
                return true;
            }
        }
        return false;
    }

    public void pickEligibleTarget(Troop troop, List<Troop> enemies) {
        int current_target = troop.getTarget();

        // -2 means no valid targets left, skip troop
        if (current_target == -2) {
            return;
        }

        boolean enemyHasLivingMeleeTroops = hasLivingMeleeTroops(enemies);

        // if the current target is still good, keep fighting him
        if (current_target >= 0 && isEligibleTarget(troop, enemies.get(current_target), enemyHasLivingMeleeTroops)) {
            return;
        }

        if (log) {
            System.out.println(troop.getTroopClass() + " " + troop.getId() +
                    " searches for a new target"
            );
        }

        // start on a random enemy and cycle through all of them
        int start = random.nextInt(enemies.size());
        for (int offset = 0; offset < enemies.size(); offset++) {
            int new_target = (start + offset) % enemies.size();
            if (isEligibleTarget(troop, enemies.get(new_target), enemyHasLivingMeleeTroops)) {
                troop.setTarget(new_target);
                return;
            }
        }

        // melee troop has only unreachable rangers left - keep searching
        if (troop.getTroopType() == TroopType.MELEE && hasLivingRangedTroops(enemies)) {
            troop.setTarget(-1);
            return;
        }

        // no valid targets left in the battle
        if (log) {
            System.out.println(troop.getTroopClass() + " " + troop.getId() +
                    " has no valid targets left"
            );
        }
        troop.setTarget(-2);
    }

    public void attackTarget(Troop troop, Troop enemy) {
        int damageDealt = max(0, troop.getDps() - enemy.getArmor());
        enemy.setHp(enemy.getHp() - damageDealt);
        if (log) {
            System.out.println(troop.getTroopClass() + " " + troop.getId() + " attacks " +
                    enemy.getTroopClass() + " " + enemy.getId() + " for " +
                    troop.getDps() + "-" + enemy.getArmor() + " damage (HP: " +
                    (enemy.getHp() + troop.getDps() - enemy.getArmor()) + " -> " +
                    enemy.getHp() + ")"
            );
        }
    }

    public void attackEnemies(List<Troop> dealers, List<Troop> receivers) {
        for (Troop troop : dealers) {
            if (troop.getHp() > 0) {
                pickEligibleTarget(troop, receivers);
                if (troop.getTarget() >= 0) {
                    attackTarget(troop, receivers.get(troop.getTarget()));
                }
            }
        }
    }

    public void combatRound(Battle battle) {
        battle.setRound(battle.getRound() + 1);
        if (log) {
            System.out.print("\n ===== ROUND " + battle.getRound() + " =====\n\n");
        }
        attackEnemies(battle.getAttackerTroops(), battle.getDefenderTroops());
        if (log) {
            System.out.println("----");
        }
        attackEnemies(battle.getDefenderTroops(), battle.getAttackerTroops());
    }

    public boolean hasBeenDestroyed(List<Troop> troops) {
        for (Troop troop : troops) {
            if (troop.getHp() > 0) {
                return false;
            }
        }
        return true;
    }
}

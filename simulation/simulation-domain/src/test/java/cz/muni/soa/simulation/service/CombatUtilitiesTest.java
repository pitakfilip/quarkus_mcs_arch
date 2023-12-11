package cz.muni.soa.simulation.service;

import cz.muni.soa.simulation.domain.Troop;
import cz.muni.soa.simulation.domain.TroopClass;
import cz.muni.soa.simulation.domain.TroopType;
import cz.muni.soa.simulation.repository.IBattleRepository;
import cz.muni.soa.simulation.repository.ITroopRepository;
import jakarta.inject.Inject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CombatUtilitiesTest {

    @Inject
    ITroopRepository troopRepository;

    @Inject
    IBattleRepository battleRepository;

    Troop generateTroop(Long id, TroopType troopType, TroopClass troopClass,
            int hp, int originalHp, int dps, int armor, int target
    ) {
        Troop troop = new Troop();
        troop.setId(id);
        troop.setTroopType(troopType);
        troop.setTroopClass(troopClass);
        troop.setHp(hp);
        troop.setOriginalHp(originalHp);
        troop.setDps(dps);
        troop.setArmor(armor);
        troop.setTarget(target);

        return troop;
    }

    Troop generateInfantrySword(Long id, int target) {
        return generateTroop(id, TroopType.MELEE, TroopClass.INFANTRYSWORD,
                40, 40, 8, 5, target);
    }

    Troop generateMaceman(Long id, int target) {
        return generateTroop(id, TroopType.MELEE, TroopClass.MACEMAN,
                65, 65, 5, 15, target);
    }

    Troop generateArcher(Long id, int target) {
        return generateTroop(id, TroopType.RANGED, TroopClass.ARCHER,
                40, 40, 8, 5, target);
    }

    @Test
    public void testIsEligibleTarget() {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Troop sword1 = generateInfantrySword(1L, -1);
        Troop sword2 = generateInfantrySword(2L, -1);
        Troop mace1 = generateArcher(3L, -1);
        Troop mace2 = generateArcher(4L, -1);
        Troop archer1 = generateArcher(5L, -1);
        Troop archer2 = generateArcher(6L, -1);

        assert(combatUtilities.isEligibleTarget(sword1, sword2, true));
        assert(combatUtilities.isEligibleTarget(sword2, sword1, true));

        // armor too high
        assert(!combatUtilities.isEligibleTarget(mace1, mace2, true));
        assert(!combatUtilities.isEligibleTarget(sword1, mace2, true));

        // attacking archers
        assert(!combatUtilities.isEligibleTarget(sword1, archer2, true));
        assert(combatUtilities.isEligibleTarget(sword1, archer2, false));

        // archers attacking archers
        assert(combatUtilities.isEligibleTarget(archer1, archer2, true));
        assert(combatUtilities.isEligibleTarget(archer1, archer2, false));
    }
}
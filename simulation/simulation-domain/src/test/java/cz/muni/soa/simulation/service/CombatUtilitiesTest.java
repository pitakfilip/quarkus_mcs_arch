package cz.muni.soa.simulation.service;

import cz.muni.soa.simulation.domain.Troop;
import cz.muni.soa.simulation.domain.TroopClass;
import cz.muni.soa.simulation.domain.TroopType;
import cz.muni.soa.simulation.repository.IBattleRepository;
import cz.muni.soa.simulation.repository.ITroopRepository;
import jakarta.inject.Inject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
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
        Troop mace1 = generateMaceman(3L, -1);
        Troop mace2 = generateMaceman(4L, -1);
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

    @Test
    public void testHasLivingTroops() {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Troop sword = generateInfantrySword(1L, -1);
        Troop mace = generateMaceman(2L, -1);
        Troop archer = generateArcher(3L, -1);

        List<Troop> troops1 = new ArrayList<>();
        troops1.add(sword);

        List<Troop> troops2 = new ArrayList<>();
        troops2.add(mace);
        troops2.add(archer);

        List<Troop> troops3 = new ArrayList<>();

        assert(combatUtilities.hasLivingMeleeTroops(troops1));
        assert(!combatUtilities.hasLivingRangedTroops(troops1));
        assert(!combatUtilities.hasBeenDestroyed(troops1));

        assert(combatUtilities.hasLivingMeleeTroops(troops2));
        assert(combatUtilities.hasLivingRangedTroops(troops2));
        assert(!combatUtilities.hasBeenDestroyed(troops2));

        assert(!combatUtilities.hasLivingMeleeTroops(troops3));
        assert(!combatUtilities.hasLivingRangedTroops(troops3));
        assert(combatUtilities.hasBeenDestroyed(troops3));
    }

    @Test
    public void testGetDeceasedAndSurvivorTroops() {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        Troop sword1 = generateInfantrySword(1L, -1);
        Troop sword2 = generateInfantrySword(2L, -1);
        Troop mace1 = generateMaceman(3L, -1);
        Troop mace2 = generateMaceman(4L, -1);
        Troop archer1 = generateArcher(5L, -1);
        Troop archer2 = generateArcher(6L, -1);

        sword1.setHp(-15);
        sword2.setHp(0);
        archer2.setHp(-100);

        List<Troop> troops1 = new ArrayList<>();
        troops1.add(sword1);
        troops1.add(sword2);

        List<Troop> troops2 = new ArrayList<>();
        troops2.add(mace1);
        troops2.add(mace2);
        troops2.add(archer1);
        troops2.add(archer2);

        List<Troop> troops3 = new ArrayList<>();

        assertThat(combatUtilities.getDeceasedTroops(troops1), is(Arrays.asList(1L, 2L)));
        assert(combatUtilities.getSurvivorTroops(troops1).isEmpty());

        assertThat(combatUtilities.getDeceasedTroops(troops2), is(Arrays.asList(6L)));
        assertThat(combatUtilities.getSurvivorTroops(troops2), is(Arrays.asList(3L, 4L, 5L)));

        assert(combatUtilities.getDeceasedTroops(troops3).isEmpty());
        assert(combatUtilities.getSurvivorTroops(troops3).isEmpty());
    }
}
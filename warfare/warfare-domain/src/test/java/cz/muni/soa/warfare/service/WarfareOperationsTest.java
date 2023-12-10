package cz.muni.soa.warfare.service;

import cz.muni.soa.warfare.domain.troop.TroopClass;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;
import cz.muni.soa.warfare.repository.ITroopsRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WarfareOperationsTest {
    ITroopClassLevelRepository classLevel = mock(ITroopClassLevelRepository.class);
    ITroopsRepository troop = mock(ITroopsRepository.class);
    IKingdomsTroopsRepository kTRepo = mock(IKingdomsTroopsRepository.class);
    WarfareOperations wfr = new WarfareOperations(kTRepo,classLevel,troop);

    @Test
    public void testSetupOfTroopClassLevels(){
        wfr.setUpLevelsForNewKingdom(1L);

        var comparingMapa  = Map.of(TroopClass.CALVARYSWORD, 1,
                TroopClass.INFANTRYSWORD, 1,
                TroopClass.MACEMAN, 1,
                TroopClass.ARCHER, 1,
                TroopClass.CROSSBOW_TROOP, 1,
                TroopClass.RAM_VEHICLE, 1,
                TroopClass.TREBUCHET, 1);

        when(classLevel.getAllTroopClassLevels(1L)).thenReturn(comparingMapa);
    }


}
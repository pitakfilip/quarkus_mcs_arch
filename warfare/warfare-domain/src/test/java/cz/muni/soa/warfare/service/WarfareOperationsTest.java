package cz.muni.soa.warfare.service;

import cz.muni.soa.warfare.domain.troop.TroopClass;
import cz.muni.soa.warfare.domain.troop.siege.Trebuchet;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;
import cz.muni.soa.warfare.repository.ITroopsRepository;
import cz.muni.soa.warfare.repository.Troopklas;
import org.junit.Assert;
import org.junit.Test;

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
    public static TroopClass fromDto(Troopklas dto) {
        return TroopClass.values()[dto.ordinal()];
    }
    @Test
    public void kokot(){
        TroopClass t = TroopClass.TREBUCHET;
        Troopklas d = Troopklas.TREBUCHET;

        String a = t.toString();
        String b = d.toString();

        Assert.assertEquals(a, b);


    }

//    @Test
//    public void testAddTroopsToKingdom(){
//        Troop t = new CalvarySword(1);
//        Troop r = new CalvarySword(1);
//        Troop e = new Archer(1);
//        Troop w = new MaceMan(1);
//        w.setAtWar(true);
//
//        Troop R = new RamVehicle(1);
//        Troop T = new Trebuchet(1);
//
//
//        List<Troop> temp = List.of(t,r,e,w);
//        KingdomsTroops k = new KingdomsTroops();
//        k.setKingdomId(1L);
//
//        k.setTroops(temp);
//        kTRepo.persist(k);
//
//        wfr.addTroopsToKingdom(List.of(T,R),1L);
//
//        when(kTRepo.getAllTroops(1L)).thenReturn(List.of(t,r,e,w,T,R));
//    }
//
//
//    @Test
//    public void testAvailableTroops(){
//        Troop t = new CalvarySword(1);
//        Troop r = new CalvarySword(1);
//        Troop e = new Archer(1);
//        Troop w = new MaceMan(1);
//        w.setAtWar(true);
//
//
//        List<Troop> temp = List.of(t,r,e,w);
//        KingdomsTroops k = new KingdomsTroops();
//        k.setKingdomId(1L);
//
//        k.setTroops(temp);
//        kTRepo.persist(k);
//
//        when(wfr.getAvailableTroops(1L)).thenReturn(List.of(t,r,e));
//    }
}
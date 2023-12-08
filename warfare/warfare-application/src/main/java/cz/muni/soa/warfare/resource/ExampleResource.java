package cz.muni.soa.warfare.resource;

import cz.muni.soa.warfare.domain.*;
import cz.muni.soa.warfare.domain.troop.TroopClassLevel;
import cz.muni.soa.warfare.domain.troop.melee.CalvarySword;
import cz.muni.soa.warfare.domain.troop.melee.InfantrySword;
import cz.muni.soa.warfare.domain.troop.melee.MaceMan;
import cz.muni.soa.warfare.domain.troop.ranged.Archer;
import cz.muni.soa.warfare.domain.troop.ranged.CrossBowTroop;
import cz.muni.soa.warfare.domain.troop.siege.RamVehicle;
import cz.muni.soa.warfare.domain.troop.siege.Trebuchet;
import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopClass;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;
import cz.muni.soa.warfare.repository.ITroopsRepository;
import cz.muni.soa.warfare.service.WarfareOperations;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.*;

@Path("/hello")
public class ExampleResource {

    @Inject
    ITroopsRepository troopsRepository;

    @Inject
    IKingdomsTroopsRepository kingdomsTroopsRepository;
    @Inject
    ITroopClassLevelRepository levelRepository;


    //    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public void jozkoVajda(List<DtoTroopRequest> requests) {
//        Map<DtoTroopClass, DtoAvailability> status = new HashMap<>();
//        for (DtoTroopRequest req: requests) {
//            // volanie do domain
//            // boolean isgut = checkAvailability(req.clazz, req.level, req.amount)
//            // if (isgut) pridat AVAILABLE do mapy
//            // ak nie tak skontrolovat ci mas money na trening
//        }
////        return status;
//    }
    @Transactional
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {


        Troop Calvar = new CalvarySword(1);
        Troop Infantry = new InfantrySword(1);
        Troop Mace = new MaceMan(1);
        Troop Archer = new Archer(1);

        Troop Cross = new CrossBowTroop(1);
        Troop Ram = new RamVehicle(1);
        Troop Trebuchet = new Trebuchet(1);


        Map<TroopClass, Integer> map = Map.of(Calvar.getTroopClass(), Calvar.getLevel(), Infantry.getTroopClass(), Infantry.getLevel(),
                Mace.getTroopClass(), Mace.getLevel(), Archer.getTroopClass(), Archer.getLevel(), Cross.getTroopClass(), Cross.getLevel(),
                Ram.getTroopClass(), Ram.getLevel(),
                Trebuchet.getTroopClass(), Trebuchet.getLevel());


        TroopClassLevel lw = new TroopClassLevel();
        lw.setKingdomId(1L);
        lw.setTroopLevel(map);

//        levelRepo.persist(lw);
//
//
//
//        Troop t = new CalvarySword(levelRepo.getLevelOfTroopClass(TroopClass.CALVARYSWORD,1L));
//        Troop r = new CalvarySword(levelRepo.getLevelOfTroopClass(TroopClass.CALVARYSWORD,1L));
//        Troop e = new Archer(levelRepo.getLevelOfTroopClass(TroopClass.ARCHER,1L));
//        Troop w = new MaceMan(levelRepo.getLevelOfTroopClass(TroopClass.MACEMAN,1L));
//
//
//        List<Troop> temp = List.of(t,r,e,w);
////
////
////
//            repository.persist(t);
//            repository.persist(r);
//            repository.persist(e);
//            repository.persist(w);
//
//        List<Troop> list = repository.getAll();
////
//        KingdomsTroops k = new KingdomsTroops();
//        k.setKingdomId(1L);
//
//        k.setTroops(temp);
//
////        KingdomsTroops l = new KingdomsTroops();
////        l.setKingdomId(2L);
////
//        kTRepo.persist(k);
////        kTRepo.persist(l);


        Troop t = new CalvarySword(1);
        Troop r = new CalvarySword(1);
        Troop e = new Archer(1);
        Troop w = new MaceMan(1);
        w.setAtWar(true);
        troopsRepository.persist(t);
        troopsRepository.persist(r);
        troopsRepository.persist(e);
        troopsRepository.persist(w);


        List<Troop> temp = new ArrayList<>(Arrays.asList(t,r,e,w));
        KingdomsTroops k = new KingdomsTroops();
        k.setId(1L);

        k.setTroops(temp);
        kingdomsTroopsRepository.persist(k);

        Troop R = new RamVehicle(1);
        Troop T = new Trebuchet(1);
        List<Troop> add = List.of(R, T);
        troopsRepository.persist(R);
        troopsRepository.persist(T);
//
        WarfareOperations wfr = new WarfareOperations(kingdomsTroopsRepository, levelRepository, troopsRepository);

        wfr.addTroopsToKingdom(add, 1L);
//
//
//        return wfr.getAvailableTroops(1L).toString();
//            return kTRepo.getAllTroops(1L).toString();
//
//
//            return kTRepo.getAllTroops(1L).toString();

//            return repository.getAll().toString();

//        List<Troop> available = wfr.getAvailableTroops(1L);

//        wfr.removeFallenTroops(List.of(t,R),1L);

//        return wfr.getAvailableTroops(1L).toString();

        return "onii-chan uWu";
    }
}

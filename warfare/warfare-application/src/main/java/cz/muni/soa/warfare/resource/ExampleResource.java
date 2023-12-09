package cz.muni.soa.warfare.resource;

import cz.muni.soa.warfare.assembler.TroopAssembler;
import cz.muni.soa.warfare.assembler.TroopClassAssembler;
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
import cz.muni.soa.warfare.dto.DtoTroopClass;
import cz.muni.soa.warfare.repository.IKingdomsTroopsRepository;
import cz.muni.soa.warfare.repository.ITroopClassLevelRepository;
import cz.muni.soa.warfare.repository.ITroopsRepository;
import cz.muni.soa.warfare.service.WarfareOperations;
import cz.muni.soa.warfare.service.WarfareService;
import io.quarkus.logging.Log;
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

    @Inject
    WarfareService service;


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
    @Path("/w")

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public  void hello() {
        service.levelUpClass(DtoTroopClass.ARCHER);
    }
}

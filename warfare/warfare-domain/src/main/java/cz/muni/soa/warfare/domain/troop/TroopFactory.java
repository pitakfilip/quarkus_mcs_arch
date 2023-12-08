package cz.muni.soa.warfare.domain.troop;


import cz.muni.soa.warfare.domain.troop.melee.CalvarySword;
import cz.muni.soa.warfare.domain.troop.melee.InfantrySword;
import cz.muni.soa.warfare.domain.troop.melee.MaceMan;
import cz.muni.soa.warfare.domain.troop.ranged.Archer;
import cz.muni.soa.warfare.domain.troop.ranged.CrossBowTroop;
import cz.muni.soa.warfare.domain.troop.siege.RamVehicle;
import cz.muni.soa.warfare.domain.troop.siege.Trebuchet;

public final class TroopFactory {
    
    public static Troop create(TroopClass clazz) {
        return switch (clazz) {
            case ARCHER -> new Archer();
            case MACEMAN -> new MaceMan();
            case CROSSBOW_TROOP -> new CrossBowTroop();
            case CALVARYSWORD -> new CalvarySword();
            case INFANTRYSWORD -> new InfantrySword();
            case RAM_VEHICLE -> new RamVehicle();
            case TREBUCHET -> new Trebuchet();
        };
    }
}

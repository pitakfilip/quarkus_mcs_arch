package cz.muni.soa.warfare.domain.troop.siege;

import cz.muni.soa.warfare.domain.troop.TroopClass;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("rams")
public class RamVehicle extends SiegeTroop {
    public RamVehicle(int level){
        super();
        setLevel(level);
        setTroopClass(TroopClass.RAM_VEHICLE);
        setDps(30);
        setArmor(25);
        setHp(150);
    }

    public RamVehicle() {
        super();
        setTroopClass(TroopClass.RAM_VEHICLE);
        setDps(30);
        setArmor(25);
        setHp(150);
    }
}

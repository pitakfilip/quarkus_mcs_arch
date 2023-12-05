package cz.muni.soa.warfare.domain.Ranged;

import cz.muni.soa.warfare.domain.TroopClass;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("crossbowmen")
public class CrossBowTroop extends RangedTroop {
    public CrossBowTroop(int level){
        super();
        setLevel(level);
        setTroopClass(TroopClass.CROSSBOW_TROOP);
        setDps(20);
        setArmor(0);
        setHp(35);
    }

    public CrossBowTroop() {
        super();
        setTroopClass(TroopClass.CROSSBOW_TROOP);
        setDps(20);
        setArmor(0);
        setHp(35);
    }
}

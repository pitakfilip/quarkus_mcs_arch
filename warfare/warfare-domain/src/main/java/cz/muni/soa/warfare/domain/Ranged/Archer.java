package cz.muni.soa.warfare.domain.Ranged;

import cz.muni.soa.warfare.domain.TroopClass;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("archers")
public class Archer extends RangedTroop {
    public Archer(int level){
        super();
        setLevel(level);
        setTroopClass(TroopClass.ARCHER);
        setDps(12);
        setArmor(5);
        setHp(30);
    }

    public Archer() {
        super();
        setTroopClass(TroopClass.ARCHER);
        setDps(12);
        setArmor(5);
        setHp(30);
    }
}

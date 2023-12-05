package cz.muni.soa.warfare.domain.Melee;

import cz.muni.soa.warfare.domain.TroopClass;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("calvary")
public class CalvarySword extends MeleeTroop {

    public CalvarySword(int level){
        super();
        setLevel(level);
        setTroopClass(TroopClass.CALVARYSWORD);
        setDps(15);
        setArmor(10);
        setHp(100);
    }

    public CalvarySword() {
        super();
        setTroopClass(TroopClass.CALVARYSWORD);
        setDps(15);
        setArmor(10);
        setHp(100);

    }
}

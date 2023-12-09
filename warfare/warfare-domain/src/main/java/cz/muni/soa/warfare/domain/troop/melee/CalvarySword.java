package cz.muni.soa.warfare.domain.troop.melee;

import cz.muni.soa.warfare.domain.troop.TroopClass;
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
        setCost(40);
    }

    public CalvarySword() {
        super();
        setTroopClass(TroopClass.CALVARYSWORD);
        setDps(15);
        setArmor(10);
        setHp(100);
        setCost(40);
    }
}

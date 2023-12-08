package cz.muni.soa.warfare.domain.troop.melee;

import cz.muni.soa.warfare.domain.troop.TroopClass;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("infantrysword")
public class InfantrySword extends MeleeTroop {
    public InfantrySword(int level){
        super();
        setLevel(level);
        setTroopClass(TroopClass.INFANTRYSWORD);
        setDps(8);
        setArmor(5);
        setHp(40);

    }

    public InfantrySword() {
        super();
        setTroopClass(TroopClass.INFANTRYSWORD);
        setDps(8);
        setArmor(5);
        setHp(40);

    }
}

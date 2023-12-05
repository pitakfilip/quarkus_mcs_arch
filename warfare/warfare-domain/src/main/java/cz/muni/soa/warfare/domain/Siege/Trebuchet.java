package cz.muni.soa.warfare.domain.Siege;

import cz.muni.soa.warfare.domain.TroopClass;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("trebuchets")
public class Trebuchet extends SiegeTroop {
    public Trebuchet(int level){
        super();
        setLevel(level);
        setTroopClass(TroopClass.TREBUCHET);
        setDps(50);
        setArmor(20);
        setHp(120);
    }

    public Trebuchet() {
        super();
        setTroopClass(TroopClass.TREBUCHET);
        setDps(50);
        setArmor(20);
        setHp(120);
    }
}

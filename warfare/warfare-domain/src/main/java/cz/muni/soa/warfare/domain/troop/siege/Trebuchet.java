package cz.muni.soa.warfare.domain.troop.siege;

import cz.muni.soa.warfare.domain.troop.TroopClass;
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
        setCost(250);
    }

    public Trebuchet() {
        super();
        setTroopClass(TroopClass.TREBUCHET);
        setDps(50);
        setArmor(20);
        setHp(120);
        setCost(250);
    }
}

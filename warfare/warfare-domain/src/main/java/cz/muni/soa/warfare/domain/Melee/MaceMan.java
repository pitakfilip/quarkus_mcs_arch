package cz.muni.soa.warfare.domain.Melee;

import cz.muni.soa.warfare.domain.TroopClass;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("macemen")
public class MaceMan extends MeleeTroop {
    public MaceMan(int level){
        super();
        setLevel(level);
        setTroopClass(TroopClass.MACEMAN);
        setDps(5);
        setArmor(15);
        setHp(65);
    }

    public MaceMan() {
        super();
        setTroopClass(TroopClass.MACEMAN);
        setDps(5);
        setArmor(15);
        setHp(65);
    }
}

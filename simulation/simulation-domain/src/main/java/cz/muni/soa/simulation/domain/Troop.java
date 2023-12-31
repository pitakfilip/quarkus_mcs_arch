package cz.muni.soa.simulation.domain;

import jakarta.persistence.*;
import jdk.jfr.Name;

@Entity
@Table(name = "simulation_troop")
public class Troop {
    @Id
    private Long id;

    // some fields are different from warfare domain, see TroopAssembler
    private TroopType troopType;
    private TroopClass troopClass;
    private int hp;
    private int originalHp;
    private int dps;
    private int armor;
    private int target;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TroopType getTroopType() {
        return troopType;
    }

    public void setTroopType(TroopType troopType) {
        this.troopType = troopType;
    }

    public TroopClass getTroopClass() {
        return troopClass;
    }

    public void setTroopClass(TroopClass troopClass) {
        this.troopClass = troopClass;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getOriginalHp() {
        return originalHp;
    }

    public void setOriginalHp(int originalHp) {
        this.originalHp = originalHp;
    }

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

}

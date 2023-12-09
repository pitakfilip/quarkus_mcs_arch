package cz.muni.soa.warfare.domain.troop;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Abstract", discriminatorType = DiscriminatorType.STRING)

public abstract class Troop {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private TroopType troopType;

    private TroopClass troopClass;
    
    private int level;

    private boolean atWar;

    @Transient
    private int hp;
    
    @Transient
    private int dps;
    
    @Transient
    private int armor;

    @Transient
    private int cost;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public TroopType getTroopType() {
        return troopType;
    }

    public void setTroopType(TroopType troopType) {
        this.troopType = troopType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public TroopClass getTroopClass() {
        return troopClass;
    }

    public void setTroopClass(TroopClass troopClass) {
        this.troopClass = troopClass;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean isAtWar() {
        return atWar;
    }

    public void setAtWar(boolean atWar) {
        this.atWar = atWar;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Troop{" +
                "id=" + id +
                ", troopType=" + troopType +
                ", troopClass=" + troopClass +
                ", level=" + level +
                ", atWar=" + atWar +
                ", hp=" + hp +
                ", dps=" + dps +
                ", armor=" + armor +
                '}';
    }
}

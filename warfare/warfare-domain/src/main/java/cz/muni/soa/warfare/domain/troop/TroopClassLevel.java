package cz.muni.soa.warfare.domain.troop;

import jakarta.persistence.*;
import java.util.Map;

@Entity
public class TroopClassLevel {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long kingdomId;

    private TroopClass troopClass;

    private int level;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @MapKeyJoinColumn(name = "troopClass_name")
    private Map<TroopClass,Integer> troopLevel;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public TroopClass getTroopClass() {
        return troopClass;
    }

    public void setTroopClass(TroopClass troopClass) {
        this.troopClass = troopClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Long getKingdomId() {
        return kingdomId;
    }

    public void setKingdomId(Long kingdomId) {
        this.kingdomId = kingdomId;
    }

    public Map<TroopClass, Integer> getTroopLevel() {
        return troopLevel;
    }

    public void setTroopLevel(Map<TroopClass, Integer> troopLevel) {
        this.troopLevel = troopLevel;
    }
}

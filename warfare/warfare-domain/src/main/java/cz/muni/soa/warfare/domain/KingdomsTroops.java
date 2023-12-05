package cz.muni.soa.warfare.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
public class KingdomsTroops {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long kingdomId;

    @OneToMany
    @JoinTable(
            name="kingdoms_troops",
            joinColumns = @JoinColumn( name="id"),
            inverseJoinColumns = @JoinColumn( name="troop_id")
    )
    private List<Troop> troops;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getKingdomId() {
        return kingdomId;
    }

    public void setKingdomId(Long kingdomId) {
        this.kingdomId = kingdomId;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }
}

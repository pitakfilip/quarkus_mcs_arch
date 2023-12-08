package cz.muni.soa.warfare.domain;

import cz.muni.soa.warfare.domain.troop.Troop;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class KingdomsTroops {

    @Id
    private Long id;

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


    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }

}

package cz.muni.soa.simulation.domain;

import jakarta.persistence.*;

@Entity
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long attacker;
    private long defender;
    private BattleStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAttacker() {
        return attacker;
    }

    public void setAttacker(long attacker) {
        this.attacker = attacker;
    }

    public long getDefender() {
        return defender;
    }

    public void setDefender(long defender) {
        this.defender = defender;
    }

    public BattleStatus getStatus() {
        return status;
    }

    public void setStatus(BattleStatus status) {
        this.status = status;
    }
}

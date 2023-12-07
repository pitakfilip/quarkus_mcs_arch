package cz.muni.soa.simulation.domain;

import jakarta.persistence.*;

import java.util.Comparator;
import java.util.List;

@Entity
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BattleStatus status;
    private BattleResult result;
    private long round;

    private long attacker;
    private long defender;

    @OneToMany
    @JoinTable(
            name="attacker_troops",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="troop_id")
    )
    private List<Troop> attackerTroops;

    @OneToMany
    @JoinTable(
            name="defender_troops",
            joinColumns = @JoinColumn(name="id"),
            inverseJoinColumns = @JoinColumn(name="troop_id")
    )
    private List<Troop> defenderTroops;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BattleStatus getStatus() {
        return status;
    }

    public void setStatus(BattleStatus status) {
        this.status = status;
    }

    public BattleResult getResult() {
        return result;
    }

    public void setResult(BattleResult result) {
        this.result = result;
    }

    public long getRound() {
        return round;
    }

    public void setRound(long round) {
        this.round = round;
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

    public List<Troop> getAttackerTroops() {
        // keep a stable order for evaluation of battles
        attackerTroops.sort(Comparator.comparing(Troop::getId));
        return attackerTroops;
    }

    public void setAttackerTroops(List<Troop> attackerTroops) {
        this.attackerTroops = attackerTroops;
    }

    public List<Troop> getDefenderTroops() {
        defenderTroops.sort(Comparator.comparing(Troop::getId));
        return defenderTroops;
    }

    public void setDefenderTroops(List<Troop> defenderTroops) {
        this.defenderTroops = defenderTroops;
    }

}

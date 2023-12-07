package cz.muni.soa.simulation.dto;

import java.util.List;

public class DtoBattle {
    public long id;

    public DtoBattleStatus status;

    public long attacker;
    public long defender;

    public List<DtoTroop> attackerTroops;
    public List<DtoTroop> defenderTroops;
}

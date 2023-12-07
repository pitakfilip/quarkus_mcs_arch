package cz.muni.soa.simulation.dto;

public class DtoTroop {
    // some fields from warfare domain are left out because they're not needed here
    public Long id;
    public DtoTroopType troopType;
    public DtoTroopClass troopClass;
    public int hp;
    public int dps;
    public int armor;
}

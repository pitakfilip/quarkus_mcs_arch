package cz.muni.soa.warfare.dto;

public enum DtoTroopClass {
    INFANTRYSWORD("sword"),
    MACEMAN("mace"),
    CALVARYSWORD("calvary"),
    ARCHER("archer"),
    CROSSBOW_TROOP("crossbow"),
    RAM_VEHICLE("ram"),
    TREBUCHET("trebuchet");



    DtoTroopClass(String name){
        this.name = name;
    }

    public final String name;

    public String getName() {
        return name;
    }

    public DtoTroopClass fromString(String s){
        return DtoTroopClass.valueOf(s);
    }
}

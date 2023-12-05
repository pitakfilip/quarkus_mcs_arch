package cz.muni.soa.warfare.domain;

public enum TroopClass {
    INFANTRYSWORD("sword"),
    MACEMAN("mace"),
    CALVARYSWORD("calvary"),
    ARCHER("archer"),
    CROSSBOW_TROOP("crossbow"),
    RAM_VEHICLE("ram"),
    TREBUCHET("trebuchet");



    TroopClass(String name){
        this.name = name;
    }

    public final String name;

    public String getName() {
        return name;
    }

    public TroopClass fromString(String s){
        return TroopClass.valueOf(s);
    }

}

package cz.muni.soa.foundation.domain.defence;

import cz.muni.soa.foundation.domain.defence.active.ArcherTower;
import cz.muni.soa.foundation.domain.defence.active.Batman;
import cz.muni.soa.foundation.domain.defence.active.Cannon;
import cz.muni.soa.foundation.domain.defence.passive.GalacticShield;
import cz.muni.soa.foundation.domain.defence.passive.PerimeterWall;

public final class DefenceFactory {
    
    public static ArcherTower archerTower() {
        ArcherTower archerTower = new ArcherTower();
        archerTower.setLevel(1);
        archerTower.setHealth(60);
        archerTower.setArmor(15);
        archerTower.setDps(25);
        return archerTower;
    }
    
    public static Cannon cannon() {
        Cannon cannon = new Cannon();
        cannon.setLevel(1);
        cannon.setHealth(110);
        cannon.setArmor(23);
        cannon.setDps(14);
        return cannon;
    }
    
    public static Batman batman() {
        Batman batman = new Batman();
        batman.setLevel(1);
        batman.setHealth(500);
        batman.setArmor(120);
        batman.setDps(73);
        return batman;
    }
    
    public static GalacticShield shield() {
        GalacticShield shield = new GalacticShield();
        shield.setLevel(1);
        shield.setHealth(650);
        shield.setArmor(250);
        return shield;
    }
    
    public static PerimeterWall wall() {
        PerimeterWall wall = new PerimeterWall();
        wall.setLevel(1);
        wall.setHealth(140);
        wall.setArmor(20);
        return wall;
    }
}

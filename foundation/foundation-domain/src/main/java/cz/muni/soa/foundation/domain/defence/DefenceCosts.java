package cz.muni.soa.foundation.domain.defence;

import cz.muni.soa.foundation.domain.resource.ResourceType;

import java.util.Map;

public final class DefenceCosts {

    // Cannot buy
    public static Map<ResourceType, Long> perimeterWall() {
        return null;
    }

    public static Map<ResourceType, Long> galacticShield() {
        return Map.of(
                ResourceType.WOOD, 2000L,
                ResourceType.IRON, 1000L,
                ResourceType.GOLD, 800L
        );
    }

    public static Map<ResourceType, Long> archerTower() {
        return Map.of(
                ResourceType.WOOD, 120L,
                ResourceType.IRON, 120L,
                ResourceType.GOLD, 50L
        );
    }

    public static Map<ResourceType, Long> cannon() {
        return Map.of(
                ResourceType.WOOD, 210L,
                ResourceType.IRON, 180L,
                ResourceType.GOLD, 90L
        );
    }

    public static Map<ResourceType, Long> batman() {
        return Map.of(
                ResourceType.WOOD, 1200L,
                ResourceType.IRON, 1200L,
                ResourceType.GOLD, 5000L
        );
    }
}

package cz.muni.soa.foundation.domain.resource.producer;

import cz.muni.soa.foundation.domain.resource.ResourceType;

import java.util.Map;

public final class ProducerCosts {

    public static Map<ResourceType, Long> of(ResourceType type) {
        return switch (type) {
            case WOOD -> lumberjack();
            case IRON -> ironMine();
            case GOLD -> goldSmelter();
        };
    }
    
    private static Map<ResourceType, Long> lumberjack() {
        return Map.of(
                ResourceType.WOOD, 250L,
                ResourceType.IRON, 100L,
                ResourceType.GOLD, 0L
                );
    }

    private static Map<ResourceType, Long> ironMine() {
        return Map.of(
                ResourceType.WOOD, 350L,
                ResourceType.IRON, 200L,
                ResourceType.GOLD, 50L
        );
    }

    private static Map<ResourceType, Long> goldSmelter() {
        return Map.of(
                ResourceType.WOOD, 450L,
                ResourceType.IRON, 300L,
                ResourceType.GOLD, 150L
        );
    }
}

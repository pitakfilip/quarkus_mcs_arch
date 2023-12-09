package cz.muni.soa.foundation.domain.resource.producer;

import cz.muni.soa.foundation.domain.resource.ResourceType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LUMBER")
public class Lumberjack extends ResourceProducer {

    private final ResourceType produce = ResourceType.WOOD;

    private final double produceMultiplier = 2.3;

    private final int perMinuteBase = 60;
}

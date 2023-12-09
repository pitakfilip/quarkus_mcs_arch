package cz.muni.soa.foundation.domain.resource.producer;

import cz.muni.soa.foundation.domain.resource.ResourceType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GOLD")
public class GoldSmelter extends ResourceProducer {

    private final ResourceType produce = ResourceType.GOLD;

    private final double produceMultiplier = 1.4;

    private final int perMinuteBase = 30;
}

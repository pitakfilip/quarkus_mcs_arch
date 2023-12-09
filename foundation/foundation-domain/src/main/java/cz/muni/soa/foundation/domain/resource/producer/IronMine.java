package cz.muni.soa.foundation.domain.resource.producer;

import cz.muni.soa.foundation.domain.resource.ResourceType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("IRON")
public class IronMine extends ResourceProducer {

    private final ResourceType produce = ResourceType.IRON;

    private final double produceMultiplier = 2;

    private final int perMinuteBase = 45;
}

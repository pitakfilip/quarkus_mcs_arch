package cz.muni.soa.foundation.domain.resource.producer;

import cz.muni.soa.foundation.domain.resource.ResourceType;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PRODUCE", discriminatorType = DiscriminatorType.STRING)
public abstract class ResourceProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int level;

    @Transient
    private final ResourceType produce = null;
    
    @Transient
    private final double produceMultiplier = 0;
    
    @Transient
    private final int perMinuteBase = 0;
    
    
    public Integer collectionRate(int duration) {
        double base = perMinuteBase * produceMultiplier;
        double rate = (Math.log(level + 1) / Math.log(2)) * base;
        return (int) rate;
    }
}

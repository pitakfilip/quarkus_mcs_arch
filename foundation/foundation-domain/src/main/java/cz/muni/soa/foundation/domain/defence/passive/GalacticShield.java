package cz.muni.soa.foundation.domain.defence.passive;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SHIELD")
public class GalacticShield extends PassiveDefence {
}

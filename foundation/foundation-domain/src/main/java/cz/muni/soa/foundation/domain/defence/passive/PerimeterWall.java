package cz.muni.soa.foundation.domain.defence.passive;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("WALL")
public class PerimeterWall extends PassiveDefence {
}

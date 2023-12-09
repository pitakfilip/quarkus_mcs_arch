package cz.muni.soa.foundation.domain.defence.active;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CANNON")
public class Cannon extends ActiveDefence{
}

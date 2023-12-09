package cz.muni.soa.foundation.domain.defence.active;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BATMAN")
public class Batman extends ActiveDefence {
}

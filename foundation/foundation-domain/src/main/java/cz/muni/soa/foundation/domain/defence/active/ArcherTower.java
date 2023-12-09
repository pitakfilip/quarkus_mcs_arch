package cz.muni.soa.foundation.domain.defence.active;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ARCHER_TOWER")
public class ArcherTower extends ActiveDefence {

}

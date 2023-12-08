package cz.muni.soa.warfare.domain.troop.ranged;

import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopType;

public abstract class RangedTroop extends Troop {
    RangedTroop(){
        setTroopType(TroopType.RANGED);
        setAtWar(false);

    }
}

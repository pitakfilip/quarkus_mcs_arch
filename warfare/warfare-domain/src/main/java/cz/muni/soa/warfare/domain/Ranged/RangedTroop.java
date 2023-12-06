package cz.muni.soa.warfare.domain.Ranged;

import cz.muni.soa.warfare.domain.Troop;
import cz.muni.soa.warfare.domain.TroopType;

public abstract class RangedTroop extends Troop {
    RangedTroop(){
        setTroopType(TroopType.RANGED);
        setAtWar(false);

    }
}

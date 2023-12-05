package cz.muni.soa.warfare.domain.Siege;

import cz.muni.soa.warfare.domain.Troop;
import cz.muni.soa.warfare.domain.TroopType;

public abstract class SiegeTroop extends Troop {
    SiegeTroop(){
        setTroopType(TroopType.SIEGE);
    }

}

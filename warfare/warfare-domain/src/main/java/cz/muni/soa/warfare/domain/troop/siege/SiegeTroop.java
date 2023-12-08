package cz.muni.soa.warfare.domain.troop.siege;

import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopType;

public abstract class SiegeTroop extends Troop {
    SiegeTroop(){
        setTroopType(TroopType.SIEGE);
        setAtWar(false);
    }

}

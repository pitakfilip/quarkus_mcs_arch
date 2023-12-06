package cz.muni.soa.warfare.domain.Melee;

import cz.muni.soa.warfare.domain.Troop;
import cz.muni.soa.warfare.domain.TroopType;

public abstract class MeleeTroop extends Troop {

    MeleeTroop() {
        setTroopType(TroopType.MELEE);
        setAtWar(false);
    }


}

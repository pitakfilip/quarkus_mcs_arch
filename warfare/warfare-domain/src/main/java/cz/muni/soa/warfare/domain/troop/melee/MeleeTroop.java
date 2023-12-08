package cz.muni.soa.warfare.domain.troop.melee;

import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopType;

public abstract class MeleeTroop extends Troop {

    MeleeTroop() {
        setTroopType(TroopType.MELEE);
        setAtWar(false);
    }


}

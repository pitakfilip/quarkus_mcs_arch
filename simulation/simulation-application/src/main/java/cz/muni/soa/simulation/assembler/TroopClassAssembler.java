package cz.muni.soa.simulation.assembler;

import cz.muni.soa.simulation.domain.TroopClass;
import cz.muni.soa.simulation.dto.DtoTroopClass;
import cz.muni.soa.simulation.dto.DtoTroopType;

public class TroopClassAssembler {
    
    public static DtoTroopClass toDto(TroopClass troopClass) {
        return DtoTroopClass.values()[troopClass.ordinal()];
    }
    
    public static TroopClass fromDto(DtoTroopClass dto) {
        return TroopClass.values()[dto.ordinal()];
    }

    ////////////////////////////////////////////////////////////////////////////////

    // One way only.

    public static DtoTroopClass dtoFromWarfareDto(cz.muni.soa.warfare.dto.DtoTroopClass troopClass) {
        return DtoTroopClass.values()[troopClass.ordinal()];
    }
}

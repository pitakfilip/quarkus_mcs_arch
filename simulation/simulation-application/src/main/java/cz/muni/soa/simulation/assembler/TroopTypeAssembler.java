package cz.muni.soa.simulation.assembler;

import cz.muni.soa.simulation.domain.TroopType;
import cz.muni.soa.simulation.dto.DtoTroopType;

public class TroopTypeAssembler {
    
    public static DtoTroopType toDto(TroopType troopType) {
        return DtoTroopType.values()[troopType.ordinal()];
    }
    
    public static TroopType fromDto(DtoTroopType dto) {
        return TroopType.values()[dto.ordinal()];
    }

    ////////////////////////////////////////////////////////////////////////////////

    // One way only.

    public static DtoTroopType dtoFromWarfareDto(cz.muni.soa.warfare.dto.DtoTroopType troopType) {
        return DtoTroopType.values()[troopType.ordinal()];
    }
}

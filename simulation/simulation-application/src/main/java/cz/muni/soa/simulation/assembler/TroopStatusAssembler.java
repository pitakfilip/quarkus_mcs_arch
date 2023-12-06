package cz.muni.soa.simulation.assembler;

import cz.muni.soa.simulation.domain.TroopType;
import cz.muni.soa.simulation.dto.DtoTroopType;

public class TroopStatusAssembler {
    
    public static DtoTroopType toDto(TroopType troopType) {
        return DtoTroopType.values()[troopType.ordinal()];
    }
    
    public static TroopType fromDto(DtoTroopType dto) {
        return TroopType.values()[dto.ordinal()];
    }
}

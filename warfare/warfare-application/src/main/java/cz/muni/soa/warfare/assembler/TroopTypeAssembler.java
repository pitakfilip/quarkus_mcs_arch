package cz.muni.soa.warfare.assembler;

import cz.muni.soa.warfare.domain.troop.TroopType;
import cz.muni.soa.warfare.dto.DtoTroopType;

public class TroopTypeAssembler {

    public static DtoTroopType toDto(TroopType status) {
        return DtoTroopType.valueOf(status.name());
    }

    public static TroopType fromDto(DtoTroopType dto) {
        return TroopType.valueOf(dto.name());
    }

}

package cz.muni.soa.warfare.assembler;

import cz.muni.soa.warfare.domain.TroopClass;
import cz.muni.soa.warfare.domain.TroopType;
import cz.muni.soa.warfare.dto.DtoTroopClass;
import cz.muni.soa.warfare.dto.DtoTroopType;

public class TroopTypeAssembler {

    public static DtoTroopType toDto(TroopType status) {
        return DtoTroopType.values()[status.ordinal()];
    }

    public static TroopType fromDto(DtoTroopType dto) {
        return TroopType.values()[dto.ordinal()];
    }


}

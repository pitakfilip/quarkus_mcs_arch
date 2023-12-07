package cz.muni.soa.warfare.assembler;

import cz.muni.soa.warfare.domain.TroopClass;
import cz.muni.soa.warfare.dto.DtoTroopClass;

public class TroopClassAssembler {
    public static DtoTroopClass toDto(TroopClass status) {
        return DtoTroopClass.values()[status.ordinal()];
    }

    public static TroopClass fromDto(DtoTroopClass dto) {
        return TroopClass.values()[dto.ordinal()];
    }
}
package cz.muni.soa.warfare.assembler;

import cz.muni.soa.warfare.domain.troop.TroopClass;
import cz.muni.soa.warfare.dto.DtoTroopClass;

public class TroopClassAssembler {
    
    public static DtoTroopClass toDto(TroopClass status) {
        return DtoTroopClass.valueOf(status.name());
    }

    public static TroopClass fromDto(DtoTroopClass dto) {
        return TroopClass.valueOf(dto.name());
    }
    
}

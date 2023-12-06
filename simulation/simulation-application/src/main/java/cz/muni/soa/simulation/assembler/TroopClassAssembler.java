package cz.muni.soa.simulation.assembler;

import cz.muni.soa.simulation.domain.TroopClass;
import cz.muni.soa.simulation.dto.DtoTroopClass;

public class TroopClassAssembler {
    
    public static DtoTroopClass toDto(TroopClass troopClass) {
        return DtoTroopClass.values()[troopClass.ordinal()];
    }
    
    public static TroopClass fromDto(DtoTroopClass dto) {
        return TroopClass.values()[dto.ordinal()];
    }
}

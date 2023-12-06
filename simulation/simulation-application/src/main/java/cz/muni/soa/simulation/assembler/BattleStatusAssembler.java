package cz.muni.soa.simulation.assembler;

import cz.muni.soa.simulation.domain.BattleStatus;
import cz.muni.soa.simulation.dto.DtoBattleStatus;

public class BattleStatusAssembler {
    
    public static DtoBattleStatus toDto(BattleStatus status) {
        return DtoBattleStatus.values()[status.ordinal()];
    }
    
    public static BattleStatus fromDto(DtoBattleStatus dto) {
        return BattleStatus.values()[dto.ordinal()];
    }
}

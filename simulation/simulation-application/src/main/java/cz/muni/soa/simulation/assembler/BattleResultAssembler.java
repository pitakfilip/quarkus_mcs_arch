package cz.muni.soa.simulation.assembler;

import cz.muni.soa.simulation.domain.BattleResult;
import cz.muni.soa.simulation.dto.DtoBattleResult;

public class BattleResultAssembler {
    
    public static DtoBattleResult toDto(BattleResult result) {
        return DtoBattleResult.values()[result.ordinal()];
    }
    
    public static BattleResult fromDto(DtoBattleResult dto) {
        return BattleResult.values()[dto.ordinal()];
    }
}

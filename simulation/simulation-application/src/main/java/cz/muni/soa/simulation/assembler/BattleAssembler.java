package cz.muni.soa.simulation.assembler;

import cz.muni.soa.simulation.domain.Battle;
import cz.muni.soa.simulation.dto.DtoBattle;

public class BattleAssembler {
    
    public static DtoBattle toDto(Battle battle) {
        DtoBattle dto = new DtoBattle();
        dto.id = battle.getId();
        dto.attacker = battle.getAttacker();
        dto.defender = battle.getDefender();
        dto.status = BattleStatusAssembler.toDto(battle.getStatus());

        return dto;
    }
    
    public static Battle fromDto(DtoBattle dto) {
        Battle battle = new Battle();
        battle.setId(dto.id);
        battle.setAttacker(dto.attacker);
        battle.setDefender(dto.defender);
        battle.setStatus(BattleStatusAssembler.fromDto(dto.status));

        return battle;
    }
}

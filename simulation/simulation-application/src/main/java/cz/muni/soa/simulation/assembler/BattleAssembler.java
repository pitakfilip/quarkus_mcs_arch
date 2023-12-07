package cz.muni.soa.simulation.assembler;

import cz.muni.soa.simulation.domain.Battle;
import cz.muni.soa.simulation.dto.DtoBattle;

public class BattleAssembler {
    
    public static DtoBattle toDto(Battle battle) {
        DtoBattle dto = new DtoBattle();
        dto.id = battle.getId();
        dto.status = BattleStatusAssembler.toDto(battle.getStatus());
        dto.result = BattleResultAssembler.toDto(battle.getResult());
        dto.round = battle.getRound();
        dto.attacker = battle.getAttacker();
        dto.defender = battle.getDefender();
        dto.attackerTroops = TroopAssembler.toDto(battle.getAttackerTroops());
        dto.defenderTroops = TroopAssembler.toDto(battle.getDefenderTroops());
        return dto;
    }
    
    public static Battle fromDto(DtoBattle dto) {
        Battle battle = new Battle();
        battle.setId(dto.id);
        battle.setStatus(BattleStatusAssembler.fromDto(dto.status));
        battle.setResult(BattleResultAssembler.fromDto(dto.result));
        battle.setRound(dto.round);
        battle.setAttacker(dto.attacker);
        battle.setDefender(dto.defender);
        battle.setAttackerTroops(TroopAssembler.fromDto(dto.attackerTroops));
        battle.setDefenderTroops(TroopAssembler.fromDto(dto.defenderTroops));

        return battle;
    }
}

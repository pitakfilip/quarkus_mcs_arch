package cz.muni.soa.warfare.assembler;

import cz.muni.soa.kingdom.dto.DtoClassification;
import cz.muni.soa.kingdom.dto.DtoKingdom;
import cz.muni.soa.warfare.domain.Troop;
import cz.muni.soa.warfare.domain.TroopType;
import cz.muni.soa.warfare.dto.DtoTroop;
import cz.muni.soa.warfare.dto.DtoTroopClass;
import cz.muni.soa.warfare.service.TroopStatCalculator;

import java.util.List;

public class TroopAssembler {
    //dtocko from a to na troop a list troopakov kokotakov
    public static DtoTroop toDto (Troop troop) {
        DtoTroop dto = new DtoTroop();
        dto.id = troop.getId();
        dto.armor = TroopStatCalculator.getArmorByLevel(troop.getArmor(),troop.getLevel());
        dto.dps = TroopStatCalculator.getDPSByLevel(troop.getDps(),troop.getLevel());
        dto.hp = TroopStatCalculator.getHpByLevel(troop.getHp(),troop.getLevel());
        dto.troopClass = TroopClassAssembler.toDto(troop.getTroopClass());
        dto.troopType = TroopTypeAssembler.toDto(troop.getTroopType());
        dto.atWar = troop.isAtWar();
        dto.level = troop.getLevel();
        return dto;
    }

    public static List<DtoTroop> toDto (List<Troop> troops) {
        return troops.stream()
                .map(TroopAssembler::toDto)
                .toList();
    }
//
//    public static Kingdom fromDto (DtoKingdom dto) {
//        Kingdom k = new Kingdom(dto.name);
//        k.setId(dto.id);
//        k.setProgress(ProgressAssembler.fromDto(dto.progress));
//
//        return k;
//    }
//
//    public static List<Kingdom> fromDto (List<DtoKingdom> dtos) {
//        return dtos.stream()
//                .map(KingdomAssembler::fromDto)
//                .toList();
//    }
}

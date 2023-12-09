package cz.muni.soa.warfare.assembler;

import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopFactory;
import cz.muni.soa.warfare.dto.DtoTroop;
import cz.muni.soa.warfare.service.TroopStatCalculator;

import java.util.List;

public class TroopAssembler {

    public static DtoTroop toDto(Troop troop) {
        DtoTroop dto = new DtoTroop();
        dto.id = troop.getId();
        dto.armor = TroopStatCalculator.getArmorByLevel(troop.getArmor(), troop.getLevel());
        dto.dps = TroopStatCalculator.getDPSByLevel(troop.getDps(), troop.getLevel());
        dto.hp = TroopStatCalculator.getHpByLevel(troop.getHp(), troop.getLevel());
        dto.troopClass = TroopClassAssembler.toDto(troop.getTroopClass());
        dto.troopType = TroopTypeAssembler.toDto(troop.getTroopType());
        dto.atWar = troop.isAtWar();
        dto.level = troop.getLevel();
        return dto;
    }

    public static List<DtoTroop> toDto(List<Troop> troops) {
        return troops.stream()
                .map(TroopAssembler::toDto)
                .toList();
    }

    public static Troop fromDto(DtoTroop dto) {
        Troop troop = TroopFactory.create(TroopClassAssembler.fromDto(dto.troopClass));
        troop.setId(dto.id);
        troop.setLevel(dto.level);
        troop.setAtWar(dto.atWar);
        return troop;
    }
    
    public static List<Troop> fromDto(List<DtoTroop> dtoList) {
        return dtoList.stream()
                .map(TroopAssembler::fromDto)
                .toList();
    }

}

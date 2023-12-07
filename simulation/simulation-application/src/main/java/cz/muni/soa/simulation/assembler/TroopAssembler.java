package cz.muni.soa.simulation.assembler;

import cz.muni.soa.simulation.domain.Troop;
import cz.muni.soa.simulation.dto.DtoTroop;

import java.util.List;
import java.util.stream.Collectors;

public class TroopAssembler {

    public static DtoTroop toDto(Troop troop) {
        DtoTroop dto = new DtoTroop();
        dto.id = troop.getId();
        dto.troopType = TroopTypeAssembler.toDto(troop.getTroopType());
        dto.troopClass = TroopClassAssembler.toDto(troop.getTroopClass());
        dto.hp = troop.getHp();
        dto.dps = troop.getDps();
        dto.armor = troop.getArmor();
        dto.target = troop.getTarget();

        return dto;
    }

    public static Troop fromDto(DtoTroop dto) {
        Troop troop = new Troop();
        troop.setId(dto.id);
        troop.setTroopType(TroopTypeAssembler.fromDto(dto.troopType));
        troop.setTroopClass(TroopClassAssembler.fromDto(dto.troopClass));
        troop.setHp(dto.hp);
        troop.setOriginalHp(dto.hp);
        troop.setDps(dto.dps);
        troop.setArmor(dto.armor);
        troop.setTarget(dto.target);

        return troop;
    }

    public static List<DtoTroop> toDto (List<Troop> troops) {
        return troops.stream()
                .map(TroopAssembler::toDto)
                .collect(Collectors.toList());
    }

    public static List<Troop> fromDto (List<DtoTroop> dtos) {
        return dtos.stream()
                .map(TroopAssembler::fromDto)
                .collect(Collectors.toList());
    }
}

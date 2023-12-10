package cz.muni.soa.simulation.assembler;

import cz.muni.soa.simulation.domain.Troop;
import cz.muni.soa.simulation.dto.DtoTroop;
import cz.muni.soa.simulation.repository.IBattleRepository;
import cz.muni.soa.simulation.repository.ITroopRepository;
import cz.muni.soa.simulation.service.CombatUtilities;
import cz.muni.soa.warfare.dto.*;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class TroopAssembler {

    @Inject
    ITroopRepository troopRepository;

    @Inject
    IBattleRepository battleRepository;

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

    public static List<DtoTroop> toDto(List<Troop> troops) {
        return troops.stream()
                .map(TroopAssembler::toDto)
                .collect(Collectors.toList());
    }

    public static List<Troop> fromDto(List<DtoTroop> dtos) {
        return dtos.stream()
                .map(TroopAssembler::fromDto)
                .collect(Collectors.toList());
    }

    ////////////////////////////////////////////////////////////////////////////////

    // These are one way only.

    public static DtoTroop dtoFromWarfareDto(cz.muni.soa.warfare.dto.DtoTroop warfareDto) {
        DtoTroop dto = new DtoTroop();
        dto.id = warfareDto.id;
        dto.troopType = TroopTypeAssembler.dtoFromWarfareDto(warfareDto.troopType);
        dto.troopClass = TroopClassAssembler.dtoFromWarfareDto(warfareDto.troopClass);
        dto.hp = warfareDto.hp;
        dto.dps = warfareDto.hp;
        dto.armor = warfareDto.armor;
        dto.target = -1;

        return dto;
    }

    public static List<DtoTroop> dtoFromWarfareDto(List<cz.muni.soa.warfare.dto.DtoTroop> warfareDtos) {
        return warfareDtos.stream()
                .map(TroopAssembler::dtoFromWarfareDto)
                .collect(Collectors.toList());
    }

    public DtoWarResult dtoToWarResultDto(List<Troop> troops) {
        CombatUtilities combatUtilities = new CombatUtilities(troopRepository, battleRepository);

        DtoWarResult dto = new DtoWarResult();
        dto.survivorTroops = combatUtilities.getSurvivorTroops(troops);
        dto.deceasedTroops = combatUtilities.getDeceasedTroops(troops);
        return dto;
    }
}

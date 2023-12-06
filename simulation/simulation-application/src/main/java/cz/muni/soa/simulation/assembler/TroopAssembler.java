package cz.muni.soa.simulation.assembler;

import cz.muni.soa.kingdom.dto.DtoClassification;
import cz.muni.soa.kingdom.dto.DtoKingdom;
import cz.muni.soa.simulation.dto.DtoTroop;
import cz.muni.soa.simulation.domain.Troop;
import cz.muni.soa.simulation.dto.DtoTroop;

import java.util.List;

public class TroopAssembler {

    public static DtoTroop toDto(Troop troop) {
        DtoTroop dto = new DtoTroop();
        dto.id = troop.getId();
        // TODO THE REST (see KingdomDto)

        return dto;
    }

    public static Troop fromDto(DtoTroop dto) {
        Troop troop = new Troop();
        troop.setId(dto.id);
        // TODO THE REST

        return troop;
    }

    public static List<DtoTroop> toDto (List<Troop> troops) {
        return troops.stream()
                .map(TroopAssembler::toDto)
                .toList();
    }

    public static List<Troop> fromDto (List<DtoTroop> dtos) {
        return dtos.stream()
                .map(TroopAssembler::fromDto)
                .toList();
    }
}

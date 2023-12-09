package cz.muni.soa.warfare.assembler;

import cz.muni.soa.warfare.domain.troop.Troop;
import cz.muni.soa.warfare.domain.troop.TroopFactory;
import cz.muni.soa.warfare.domain.troop.TroopRequest;
import cz.muni.soa.warfare.dto.DtoTroop;
import cz.muni.soa.warfare.dto.DtoTroopRequest;
import cz.muni.soa.warfare.service.TroopStatCalculator;

import java.util.List;

public class RequestAssembler {
    public static DtoTroopRequest toDto(TroopRequest troop) {
        DtoTroopRequest dTR = new DtoTroopRequest();
        dTR.clazz = TroopClassAssembler.toDto(troop.clazz);
        dTR.amount = troop.amount;
        dTR.level = troop.level;
        return dTR;

    }

    public static List<DtoTroopRequest> toDto(List<TroopRequest> troops) {
        return troops.stream()
                .map(RequestAssembler::toDto)
                .toList();
    }

    public static TroopRequest fromDto(DtoTroopRequest dto) {
        TroopRequest res = new TroopRequest();
        res.amount = dto.amount;
        res.level = dto.level;
        res.clazz = TroopClassAssembler.fromDto(dto.clazz);
        return res;


    }

    public static List<TroopRequest> fromDto(List<DtoTroopRequest> dtoList) {
        return dtoList.stream()
                .map(RequestAssembler::fromDto)
                .toList();
    }
}

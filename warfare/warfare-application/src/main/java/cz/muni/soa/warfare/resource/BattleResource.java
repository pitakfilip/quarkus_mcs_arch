package cz.muni.soa.warfare.resource;

import cz.muni.soa.warfare.api.BattleApi;
import cz.muni.soa.warfare.assembler.TroopAssembler;
import cz.muni.soa.warfare.dto.DtoTroop;
import cz.muni.soa.warfare.service.WarfareService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

import java.util.List;

//@Path("battle")
public class BattleResource implements BattleApi {
    @Inject
    WarfareService service;


}

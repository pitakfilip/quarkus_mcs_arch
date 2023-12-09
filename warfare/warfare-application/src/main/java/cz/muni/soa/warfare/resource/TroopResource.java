package cz.muni.soa.warfare.resource;

import cz.muni.soa.warfare.api.TroopApi;
import cz.muni.soa.warfare.assembler.TroopAssembler;
import cz.muni.soa.warfare.dto.DtoTroop;
import cz.muni.soa.warfare.dto.DtoTroopClass;
import cz.muni.soa.warfare.service.WarfareService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/troops")
public class TroopResource implements TroopApi {
    @Inject
    WarfareService service;


    @Override
    public List<DtoTroop> getAvailableTroops() {
        return TroopAssembler.toDto(service.getAvailableTroops());
    }

    @Transactional
    @Override
    public Response addTroops(List<DtoTroop> troops) {
        service.addTroopsToKingdom(troops);
        return Response.ok().build();
    }

    @Override
    public Response levelUpClass(DtoTroopClass troopClass) {
        service.levelUpClass(troopClass);

        return Response.ok().build();
    }

}

package cz.muni.soa.simulation.resource;

import cz.muni.soa.simulation.api.BattleApi;
import cz.muni.soa.simulation.assembler.BattleAssembler;
import cz.muni.soa.simulation.assembler.BattleStatusAssembler;
import cz.muni.soa.simulation.domain.Battle;
import cz.muni.soa.simulation.domain.BattleStatus;
import cz.muni.soa.simulation.dto.DtoBattle;
import cz.muni.soa.simulation.dto.DtoBattleStatus;
import cz.muni.soa.simulation.dto.DtoTroop;
import cz.muni.soa.simulation.repository.IBattleRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;


@Path("/warfare")
public class BattleResource implements BattleApi {

    @Inject
    IBattleRepository battleRepository;

    @Transactional
    @Override
    public DtoBattle createBattle(long kingdom, long target, List<DtoTroop> troops) {
        // TODO verify arguments
        Battle battle = new Battle();
        battle.setAttacker(kingdom);
        battle.setDefender(target);
        battle.setStatus(BattleStatus.WAITING);
        battleRepository.persist(battle);

        return BattleAssembler.toDto(battle);
    }

    @Override
    public Response /*DtoBattleStatus*/ getBattleStatus(long id) {
        Battle battle = battleRepository.getById(id);
        if (battle == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Battle " + id + " not found.").build();
        }

        return Response.ok(BattleStatusAssembler.toDto(battle.getStatus())).build();
    }
}

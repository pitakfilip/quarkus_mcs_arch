package cz.muni.soa.kingdom.resource;

import cz.muni.soa.kingdom.api.KingdomApi;
import cz.muni.soa.kingdom.assembler.KingdomAssembler;
import cz.muni.soa.kingdom.service.KingdomOperations;
import cz.muni.soa.kingdom.dto.DtoKingdom;

import cz.muni.soa.kingdom.domain.Kingdom;
import cz.muni.soa.kingdom.repository.IKingdomRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;

@Path("/kingdom")
public class KingdomResource implements KingdomApi {

    @Inject
    IKingdomRepository kingdomRepository;


    @Override
    @Transactional
    public DtoKingdom createKingdom(String name) {
        Kingdom kingdom = new Kingdom(name);
        kingdomRepository.persist(kingdom);
        
        //  TODO need security context to get calling user from JWT, then create new kingdom for user
        //   return created kingdom instance
        return null;
    }

    @Override
    public DtoKingdom getKingdom(long id) {
        Kingdom result = kingdomRepository.getById(id);
        if (result == null) {
            return null;
        }

        return KingdomAssembler.toDto(result);
    }

    @Override
    public void addExperience(long id, int amount) {
        KingdomOperations ko = new KingdomOperations(kingdomRepository);
        ko.addExperience(id, amount);
    }
}

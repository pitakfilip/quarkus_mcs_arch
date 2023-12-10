package cz.muni.soa.kingdom.resource;

import cz.muni.soa.infrastructure.security.AuthContext;
import cz.muni.soa.kingdom.api.KingdomApi;
import cz.muni.soa.kingdom.assembler.KingdomAssembler;
import cz.muni.soa.kingdom.proxy.WarfareProxy;
import cz.muni.soa.kingdom.service.KingdomOperations;
import cz.muni.soa.kingdom.dto.DtoKingdom;

import cz.muni.soa.kingdom.domain.Kingdom;
import cz.muni.soa.kingdom.repository.IKingdomRepository;
import cz.muni.soa.kingdom.service.KingdomService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/kingdom")
@SecurityScheme(securitySchemeName = "JWT",
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT")
public class KingdomResource implements KingdomApi {

    @Inject
    IKingdomRepository kingdomRepository;

    @Inject
    KingdomService service;
    
    @Inject
    AuthContext authContext;
    
    @Override
    public DtoKingdom getKingdom(long id) {
        Kingdom result = kingdomRepository.getById(id);
        if (result == null) {
            return null;
        }

        return KingdomAssembler.toDto(result);
    }

    @Override
    public Response setupKingdom() throws Exception {
        service.initKingdom();
        return Response.ok().build();
    }
    
    @Override
    public void addExperience(int amount) {
        KingdomOperations ko = new KingdomOperations(kingdomRepository);
        ko.addExperience(authContext.getKingdomId(), amount);
    }
}

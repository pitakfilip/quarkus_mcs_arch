package cz.muni.soa.simulation.proxy;

import cz.muni.soa.infrastructure.security.ServiceAuthHeaderPropagation;
import cz.muni.soa.warfare.api.BattleApi;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/battle")
@RegisterClientHeaders(ServiceAuthHeaderPropagation.class)
@RegisterRestClient(baseUri = "stork://warfare")
public interface WarfareBattleProxy extends BattleApi {
}

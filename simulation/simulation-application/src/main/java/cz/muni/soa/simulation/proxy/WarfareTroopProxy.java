package cz.muni.soa.simulation.proxy;

import cz.muni.soa.infrastructure.security.ServiceAuthHeaderPropagation;
import cz.muni.soa.warfare.api.TroopApi;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/troops")
@RegisterClientHeaders(ServiceAuthHeaderPropagation.class)
@RegisterRestClient(baseUri = "stork://warfare")
public interface WarfareTroopProxy extends TroopApi {
}

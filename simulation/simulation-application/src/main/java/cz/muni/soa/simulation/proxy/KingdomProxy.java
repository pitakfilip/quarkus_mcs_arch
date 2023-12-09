package cz.muni.soa.simulation.proxy;

import cz.muni.soa.infrastructure.security.ServiceAuthHeaderPropagation;
import cz.muni.soa.kingdom.api.KingdomApi;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/kingdom")
@RegisterClientHeaders(ServiceAuthHeaderPropagation.class)
@RegisterRestClient(baseUri = "stork://kingdom")
public interface KingdomProxy extends KingdomApi {
}

package cz.muni.soa.kingdom.proxy;

import cz.muni.soa.infrastructure.security.ServiceAuthHeaderPropagation;
import cz.muni.soa.warfare.api.WarfareApi;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/warfare")
@RegisterClientHeaders(ServiceAuthHeaderPropagation.class)
@RegisterRestClient(baseUri = "stork://warfare")
public interface WarfareProxy extends WarfareApi {
}

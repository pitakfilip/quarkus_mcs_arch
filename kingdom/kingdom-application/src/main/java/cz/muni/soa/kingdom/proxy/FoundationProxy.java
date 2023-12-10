package cz.muni.soa.kingdom.proxy;

import cz.muni.soa.foundation.api.FoundationApi;
import cz.muni.soa.infrastructure.security.ServiceAuthHeaderPropagation;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/foundation")
@RegisterClientHeaders(ServiceAuthHeaderPropagation.class)
@RegisterRestClient(baseUri = "stork://foundation")
public interface FoundationProxy extends FoundationApi {
}

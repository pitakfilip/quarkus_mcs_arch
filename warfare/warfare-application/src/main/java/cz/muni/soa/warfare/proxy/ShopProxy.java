package cz.muni.soa.warfare.proxy;

import cz.muni.soa.foundation.api.ShopApi;
import cz.muni.soa.infrastructure.security.ServiceAuthHeaderPropagation;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/shop")
@RegisterClientHeaders(ServiceAuthHeaderPropagation.class)
@RegisterRestClient(baseUri = "stork://foundation")
public interface ShopProxy extends ShopApi {
}

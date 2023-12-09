package cz.muni.soa.infrastructure.security;

import io.quarkus.rest.client.reactive.ReactiveClientHeadersFactory;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MultivaluedMap;

/**
 * Initial request from client contains JWT used for authorization. This factory 
 * service provides a transparent functionality of propagating the same bearer token to all 
 * following calls to other services registered in Consul.
 */
@ApplicationScoped
public class ServiceAuthHeaderPropagation extends ReactiveClientHeadersFactory {

    @Override
    public Uni<MultivaluedMap<String, String>> getHeaders(MultivaluedMap<String, String> incomingHeaders,
                                                          MultivaluedMap<String, String> clientOutgoingHeaders) {

        String tokenBearer = incomingHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        clientOutgoingHeaders.add(HttpHeaders.AUTHORIZATION, tokenBearer);
        return Uni.createFrom().item(clientOutgoingHeaders);
    }
    
}
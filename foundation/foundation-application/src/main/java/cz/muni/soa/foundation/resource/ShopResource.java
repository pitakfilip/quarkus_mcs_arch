package cz.muni.soa.foundation.resource;

import cz.muni.soa.foundation.api.ShopApi;
import cz.muni.soa.foundation.dto.DtoDefenceType;
import cz.muni.soa.foundation.dto.DtoProducerType;
import cz.muni.soa.foundation.dto.DtoResourceRequest;
import cz.muni.soa.foundation.dto.DtoResourceType;
import cz.muni.soa.foundation.service.ShopService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/shop")
public class ShopResource implements ShopApi {
    
    @Inject
    ShopService service;
 

    @Override
    public DtoResourceRequest hasResources(DtoResourceType type, long amount) throws Exception {
        return service.hasResources(type, amount);
    }

    @Override
    public Response spendResources(DtoResourceType type, long amount) throws Exception {
        service.spendResources(type, amount);
        return Response.ok().build();
    }

    @Override
    public Response buyDefence(DtoDefenceType type) throws Exception {
        service.buyDefence(type);
        return Response.ok().build();
    }

    @Override
    public Response buyProducer(DtoProducerType type) throws Exception {
        service.buyProducer(type);
        return Response.ok().build();
    }
}

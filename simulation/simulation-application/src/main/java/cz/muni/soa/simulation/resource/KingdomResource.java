package cz.muni.soa.simulation.resource;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class KingdomResource {

    public Response /*DtoKingdom*/ getKingdom(long id) {
        try (Client client = ClientBuilder.newClient()) {
            Response response = client.target("http://localhost:8080/kingdom/find/" + id).request().get();
            int status = response.getStatus();
            if (status == 404) {
                return Response.status(Response.Status.NOT_FOUND).entity("Kingdom " + id + " not found.").build();
            }
            else if (status != 200) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Kingdom " + id + " could not be verified (" + status + ").").build();
            }
            return response;
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Kingdom " + id + " could not be verified.").build();
        }
    }
}

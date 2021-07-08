package nl.lunatech.dataIngestionProducer;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Set;

@Path("/lametro")
@RegisterRestClient(configKey = "metro-api")
@ApplicationScoped
public interface LAMetroApiService {

    @GET
    @Path("/vehicles")
    Set<Vehicle> getVehicles();


}

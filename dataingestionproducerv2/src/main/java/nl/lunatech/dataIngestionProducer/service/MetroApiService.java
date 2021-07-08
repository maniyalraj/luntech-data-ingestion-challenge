package nl.lunatech.dataIngestionProducer.service;

import nl.lunatech.dataIngestionProducer.dto.ItemWrapper;
import nl.lunatech.dataIngestionProducer.model.Items;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;


@Path("/lametro")
@RegisterRestClient(configKey = "metro-api")
public interface MetroApiService {

    @GET
    @Path("/vehicles/")
    ItemWrapper getVehicles();


}

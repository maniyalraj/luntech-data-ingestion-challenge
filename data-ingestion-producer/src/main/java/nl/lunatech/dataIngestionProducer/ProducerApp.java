package nl.lunatech.dataIngestionProducer;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Collectors;

@QuarkusMain
public class ProducerApp implements QuarkusApplication {

    @Inject
    @RestClient
    LAMetroApiService laMetroApiService;

    @Override
    public int run(String... args) throws Exception {
        Set<Vehicle> vehicles = this.laMetroApiService.getVehicles();

        vehicles.stream().forEach(System.out::println);

        return 0;
    }
}

package nl.lunatech;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import nl.lunatech.BingMaps.BingMapsTransformer;
import nl.lunatech.dataIngestionProducer.model.Items;
import nl.lunatech.dataIngestionProducer.service.LaMetroProducer;
import nl.lunatech.dataIngestionProducer.service.MetroApiService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@QuarkusMain
public class ProducerApp implements QuarkusApplication {

    @Inject
    @RestClient
    MetroApiService laMetroService;

    @Inject
    LaMetroProducer kafkaProducer;

    @Inject
    Logger logger;


    @Override
    public int run(String... args) throws Exception {

        do {

            logger.info("Starting data-ingestion-producer");

            List<Items> items = laMetroService.getVehicles().getItems();

            items.stream().forEach(item -> {
                try {
                    Map<String, Integer> pixelMap = BingMapsTransformer.LatLongToPixelXY(item.getLatitude(), item.getLongitude(), 10);

                    Map<String, Integer> tileMap = BingMapsTransformer.PixelXYToTileXY(pixelMap.get(BingMapsTransformer.PIXELX), pixelMap.get(BingMapsTransformer.PIXELY));

                    String quadKey = BingMapsTransformer.TileXYToQuadKey(tileMap.get(BingMapsTransformer.TILEX), tileMap.get(BingMapsTransformer.TILEY), 10);

                    item.setQuadKey(quadKey);

                    kafkaProducer.sendRecordToKafka(item);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            });

            Thread.sleep(5000);

        } while (true);
    }
}

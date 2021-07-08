package nl.lunatech.dataIngestionProducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.smallrye.reactive.messaging.kafka.Record;
import nl.lunatech.dataIngestionProducer.model.Items;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LaMetroProducer {

    @Inject @Channel("la-metro-input")
    Emitter<Record<String, Items>> emitter;

    public void sendRecordToKafka(Items item) throws JsonProcessingException {
        emitter.send(Record.of(item.getRoute_id(), item));
    }


}

package nl.lunatech.dataIngestionProducer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {

    public String id;
    public Integer seconds_since_report;
    public Double latitude;
    public String run_id;
    public Boolean predictable;
    public String route_id;
    public Integer heading;
    public Double longitude;


}

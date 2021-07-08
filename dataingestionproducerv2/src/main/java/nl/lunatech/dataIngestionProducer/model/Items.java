package nl.lunatech.dataIngestionProducer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    public String id;
    public Integer seconds_since_report;
    public Double latitude;
    public String run_id;
    public Boolean predictable;
    public String route_id;
    public Integer heading;
    public Double longitude;
    public String quadKey;

}

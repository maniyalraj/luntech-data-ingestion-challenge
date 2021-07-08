package nl.lunatech.dataIngestionProducer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.lunatech.dataIngestionProducer.model.Items;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemWrapper {

    private List<Items> items;
}

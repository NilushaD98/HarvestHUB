package com.HarvestHUB.collection;

import com.HarvestHUB.enums.AvailableStatus;
import com.HarvestHUB.enums.MeasuringUnits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "harvest")
public class Harvest {
    @Id
    private String harvestId;
    private String farmerId;
    private String type;
    private MeasuringUnits measuringUnits;
    private double quantity;
    private double price;
    private AvailableStatus availability;
    private String location;
    private String description;
    private Date createdAt;
    private String imageURL;

    public Harvest(String farmerId, String type, MeasuringUnits measuringUnits, double quantity, double price, AvailableStatus availability, String location, String description, Date createdAt) {
        this.farmerId = farmerId;
        this.type = type;
        this.measuringUnits = measuringUnits;
        this.quantity = quantity;
        this.price = price;
        this.availability = availability;
        this.location = location;
        this.description = description;
        this.createdAt = createdAt;
    }
}

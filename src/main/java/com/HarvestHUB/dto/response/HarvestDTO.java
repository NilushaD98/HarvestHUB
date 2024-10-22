package com.HarvestHUB.dto.response;

import com.HarvestHUB.enums.AvailableStatus;
import com.HarvestHUB.enums.MeasuringUnits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HarvestDTO {
    private String harvestId;
    private String farmerId;
    private String type;
    private MeasuringUnits measuringUnits;
    private double quantity;
    private double price;
    private AvailableStatus availability;
    private String location;
    private String description;
    private String imageURL;
    private String createdAt;
}

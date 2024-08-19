package com.HarvestHUB.dto.request;

import com.HarvestHUB.enums.AvailableStatus;
import com.HarvestHUB.enums.MeasuringUnits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddHarvestDTO {

    private String farmerId;
    private String type;
    private MeasuringUnits measuringUnits;
    private double quantity;
    private double price;
    private AvailableStatus availability;
    private String location;
    private String description;

}

package com.HarvestHUB.dto.response;

import com.HarvestHUB.enums.AvailableStatus;
import com.HarvestHUB.enums.MeasuringUnits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllHarvestsDTO {

    private String harvestId;
    private String farmerId;
    private String email;
    private String name;
    private String address;
    private String contact;
    private String profilePicture;
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

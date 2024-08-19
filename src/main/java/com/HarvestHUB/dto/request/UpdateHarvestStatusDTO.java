package com.HarvestHUB.dto.request;

import com.HarvestHUB.enums.AvailableStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateHarvestStatusDTO {

    private String harvestID;
    private AvailableStatus availableStatus;
}

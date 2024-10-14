package com.HarvestHUB.dto.request;

import com.HarvestHUB.enums.AvailableStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchHarvestDTO {

    private String type;
    private String location;
    private Double minPrice;
    private Double maxPrice;
    private AvailableStatus availableStatus;
}

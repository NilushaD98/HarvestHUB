package com.HarvestHUB.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RateOrderDTO {

    private String orderID;
    private int rate;
    private String rateMessage;
}

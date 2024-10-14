package com.HarvestHUB.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private String orderID;
    private String harvestID;
    private String wholeSellerID;
    private String farmerID;
    private String description;
    private String orderLocation;
    private Boolean deliverStatus;
    private double price;
    private int rate;
    private String rateMessage;
    private String paymentID;
}

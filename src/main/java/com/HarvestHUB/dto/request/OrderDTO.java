package com.HarvestHUB.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDTO {

    private String harvestID;
    private String wholeSellerID;
    private String description;
    private String orderLocation;
    private Boolean deliverStatus;
    private double price;
    private int rate;
    private String rateMessage;
    private long cardNumber;
    private int expYear;
    private int expMonth;
    private int cvc;
}

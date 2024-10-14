package com.HarvestHUB.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StripeTokenDTO {

    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;
    private String token;
    private Boolean success;

}

package com.HarvestHUB.service;

import com.HarvestHUB.dto.request.OrderDTO;
import com.HarvestHUB.dto.request.RateOrderDTO;
import com.HarvestHUB.dto.request.StripeChargeDto;
import com.HarvestHUB.dto.request.StripeTokenDTO;
import com.HarvestHUB.dto.response.OrderSuccessDTO;

import java.util.List;

public interface PaymentService {

    public StripeTokenDTO createCardToken(StripeTokenDTO model);
    public StripeChargeDto charge(StripeChargeDto chargeRequest);

    OrderSuccessDTO order(OrderDTO orderDTO);

    Boolean updateDeliveryStatus(String orderID);

    List<com.HarvestHUB.dto.response.OrderDTO> myOrders(String wholeSellerID);

    Boolean rateOrder(RateOrderDTO rateOrderDTO);
}

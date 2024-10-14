package com.HarvestHUB.controller;

import com.HarvestHUB.dto.request.OrderDTO;
import com.HarvestHUB.dto.request.RateOrderDTO;
import com.HarvestHUB.dto.request.StripeChargeDto;
import com.HarvestHUB.dto.request.StripeTokenDTO;
import com.HarvestHUB.service.PaymentService;
import com.HarvestHUB.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/harvesthub/api/v1/payment/")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("createCardToken")
    public ResponseEntity<StandardResponse> createCardToken(@RequestBody StripeTokenDTO stripeTokenDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",paymentService.createCardToken(stripeTokenDTO)),HttpStatus.OK
        );
    }

    @PostMapping("charge")
    public ResponseEntity<StandardResponse> charge(@RequestBody StripeChargeDto stripeChargeDto){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",paymentService.charge(stripeChargeDto)),HttpStatus.OK
        );
    }
    @PostMapping("order")
    public ResponseEntity<StandardResponse> order(@RequestBody OrderDTO orderDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",paymentService.order(orderDTO)),HttpStatus.OK
        );
    }

    @GetMapping("my_orders")
    public ResponseEntity<StandardResponse> myOrders(@RequestParam("wholeSellerID")String wholeSellerID){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",paymentService.myOrders(wholeSellerID)),HttpStatus.OK
        );
    }

    @PutMapping("update_delivery_status")
    public ResponseEntity<StandardResponse> updateDeliveryStatus(
            @RequestParam("orderID") String orderID
    ){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Order Delivery Status Update: ",paymentService.updateDeliveryStatus(orderID)),HttpStatus.OK
        );
    }
    @PutMapping("rate_order")
    public ResponseEntity<StandardResponse> rateOrder(@RequestBody RateOrderDTO rateOrderDTO){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",paymentService.rateOrder(rateOrderDTO)),HttpStatus.OK
        );
    }
}

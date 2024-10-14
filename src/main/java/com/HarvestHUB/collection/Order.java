package com.HarvestHUB.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "orders")
public class Order {

    @Id
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

    public Order(String harvestID, String wholeSellerID, String farmerID, String description, String orderLocation, Boolean deliverStatus, double price, int rate, String rateMessage,  String paymentID) {
        this.harvestID = harvestID;
        this.wholeSellerID = wholeSellerID;
        this.farmerID = farmerID;
        this.description = description;
        this.orderLocation = orderLocation;
        this.deliverStatus = deliverStatus;
        this.price = price;
        this.rate = rate;
        this.rateMessage = rateMessage;
        this.paymentID = paymentID;
    }
}

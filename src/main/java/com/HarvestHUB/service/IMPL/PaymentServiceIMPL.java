package com.HarvestHUB.service.IMPL;

import com.HarvestHUB.collection.Harvest;
import com.HarvestHUB.collection.Order;
import com.HarvestHUB.collection.User;
import com.HarvestHUB.dto.request.OrderDTO;
import com.HarvestHUB.dto.request.RateOrderDTO;
import com.HarvestHUB.dto.request.StripeChargeDto;
import com.HarvestHUB.dto.request.StripeTokenDTO;
import com.HarvestHUB.dto.response.OrderSuccessDTO;
import com.HarvestHUB.enums.AvailableStatus;
import com.HarvestHUB.repo.HarvestRepository;
import com.HarvestHUB.repo.OrderRepository;
import com.HarvestHUB.repo.UserRepository;
import com.HarvestHUB.service.PaymentService;
import com.HarvestHUB.util.mappers.OrderMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceIMPL implements PaymentService {

    private final HarvestRepository harvestRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final JavaMailSender mailSender;
    private final OrderMapper orderMapper;

    @Value("${api.stripe.key}")
    private String stringApiKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = stringApiKey;
    }

    @Override
    public StripeTokenDTO createCardToken(StripeTokenDTO model) {

        log.info(model.toString());
        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", model.getCardNumber());
            card.put("exp_month", Integer.parseInt(model.getExpMonth()));
            card.put("exp_year", Integer.parseInt(model.getExpYear()));
            card.put("cvc", model.getCvc());
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
            if (token != null && token.getId() != null) {
                model.setSuccess(true);
                model.setToken(token.getId());
            }
            return model;
        } catch (StripeException e) {
            log.error("StripeService (createCardToken)", e);
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public StripeChargeDto charge(StripeChargeDto chargeRequest) {


//         catch (StripeException e) {
//            log.error("StripeService (charge)", e);
//            throw new RuntimeException(e.getMessage());
//        }
        return null;

    }

    @Override
    public OrderSuccessDTO order(OrderDTO orderDTO) {
        OrderSuccessDTO orderSuccessDTO = new OrderSuccessDTO();
        Optional<Order> order = orderRepository.findByHarvestIDEquals(orderDTO.getHarvestID());
        if(order.isPresent()){
            throw new RuntimeException();
        }else {
            try {
                Optional<Harvest> byId = harvestRepository.findByHarvestIdEqualsAndAvailabilityEquals(orderDTO.getHarvestID(), AvailableStatus.AVAILABLE);
                log.info("asdsa:::"+byId.get().getPrice()*byId.get().getQuantity());
                PaymentIntentCreateParams params =
                        PaymentIntentCreateParams.builder()
                                .setAmount((long) (byId.get().getPrice()*byId.get().getQuantity()))
                                .setCurrency("usd")
                                .setPaymentMethod("pm_card_visa")
                                .build();

                PaymentIntent paymentIntent = PaymentIntent.create(params);

                if (paymentIntent.getId()!= null) {
                    Order order1 = new Order(
                        byId.get().getHarvestId(),
                            orderDTO.getWholeSellerID(),
                            byId.get().getFarmerId(),
                            orderDTO.getDescription(),
                            orderDTO.getOrderLocation(),
                            false,
                            byId.get().getPrice()*byId.get().getQuantity(),
                            0,
                            "",
                            paymentIntent.getId()
                    );
                    orderRepository.save(order1);
                    byId.get().setAvailability(AvailableStatus.SOLD);
                    harvestRepository.save(byId.get());
                    Optional<User> farmer = userRepository.findById(byId.get().getFarmerId());
                    mailSender(farmer.get().getEmail(),"New Order","You Have New Order \n PaymentID:"+paymentIntent.getId());
                    Optional<User> wholeSeller = userRepository.findById(order1.getWholeSellerID());
                    mailSender(wholeSeller.get().getEmail(),"Order Placed","Your Order Placed Successfully \n PaymentID:"+paymentIntent.getId());
                    return new OrderSuccessDTO(
                            true,
                            paymentIntent.getId()
                    );

                }else {
                    return new OrderSuccessDTO(
                            false,
                            "0"
                    );
                }

            } catch (StripeException e) {
                log.error("Order Error::::::"+e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Boolean updateDeliveryStatus(String orderID) {
        Optional<Order> byId = orderRepository.findById(orderID);
        byId.get().setDeliverStatus(true);
        orderRepository.save(byId.get());
        return true;
    }

    @Override
    public List<com.HarvestHUB.dto.response.OrderDTO> myOrders(String wholeSellerID) {
        List<Order> orderList = orderRepository.findByWholeSellerIDEquals(wholeSellerID);
        return orderMapper.EntityListTODTOList(orderList);
    }

    @Override
    public Boolean rateOrder(RateOrderDTO rateOrderDTO) {
        Optional<Order> byId = orderRepository.findById(rateOrderDTO.getOrderID());
        byId.get().setRate(rateOrderDTO.getRate());
        byId.get().setRateMessage(rateOrderDTO.getRateMessage());
        orderRepository.save(byId.get());
        return true;
    }

    public boolean mailSender(String toMail, String subject, String body){
        try {
            SimpleMailMessage newMail = new SimpleMailMessage();
            newMail.setTo(toMail);
            newMail.setSubject(subject);
            newMail.setText(body);
            mailSender.send(newMail);
            log.info("Mail successfully send to "+ toMail);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

}

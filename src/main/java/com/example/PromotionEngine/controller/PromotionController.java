package com.example.PromotionEngine.controller;

import com.example.PromotionEngine.Exception.ApplicationException;
import com.example.PromotionEngine.helper.SavingsCalculator;
import com.example.PromotionEngine.model.CartItemsRequest;
import com.example.PromotionEngine.model.OrderTotalDetails;
import com.example.PromotionEngine.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class PromotionController {

    private final PromotionService promotionService;
    private final SavingsCalculator savingsCalculator;

    public PromotionController(PromotionService promotionService, SavingsCalculator savingsCalculator) {
        this.promotionService = promotionService;
        this.savingsCalculator = savingsCalculator;
    }

    @PostMapping(value = "/promotionEngine")
    public ResponseEntity<OrderTotalDetails> applyPromotion(
            @RequestBody CartItemsRequest cartItemsRequest
            ) throws ApplicationException {

        OrderTotalDetails orderTotalDetails = new OrderTotalDetails();

        orderTotalDetails.setOrderTotalWithoutPromotion(promotionService.countOrderTotalWithoutPromotion(cartItemsRequest));
        orderTotalDetails.setOrderTotalWithPromotion(promotionService.countOrderTotalWithPromotion(cartItemsRequest));
        orderTotalDetails.setSavings(savingsCalculator.calculateSavings(orderTotalDetails));

        return new ResponseEntity<>(orderTotalDetails, HttpStatus.OK);

    }

}

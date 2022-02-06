package com.example.PromotionEngine.helper;

import com.example.PromotionEngine.model.OrderTotalDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SavingsCalculator {

    public Integer calculateSavings(OrderTotalDetails orderTotalDetails) {

       Integer savings = 0;
       savings = orderTotalDetails.getOrderTotalWithoutPromotion() - orderTotalDetails.getOrderTotalWithPromotion();
        return savings;
    }
}

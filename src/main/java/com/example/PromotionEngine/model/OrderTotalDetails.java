package com.example.PromotionEngine.model;

import lombok.Data;

@Data
public class OrderTotalDetails {

    private Integer orderTotalWithoutPromotion;
    private Integer orderTotalWithPromotion;
    private Integer savings;
}

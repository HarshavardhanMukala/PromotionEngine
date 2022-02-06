package com.example.PromotionEngine.service;

import com.example.PromotionEngine.helper.SKUId;
import com.example.PromotionEngine.helper.prices;
import com.example.PromotionEngine.model.CartItemsRequest;
import org.springframework.stereotype.Component;

@Component
public class PromotionService {

    private final int promoA_limit = 3;
    private final int promoA_price = 130;
    private final int promoB_limit = 2;
    private final int promoB_price = 45;
    private final int promoMix_price = 30;

    public Integer countOrderTotalWithoutPromotion(CartItemsRequest cartItems) {
        int totalOrderValue = 0;
        int SKUID_A_Price = cartItems.getItems().stream().filter(item -> item.getSkuId().equalsIgnoreCase(SKUId.SKUID_A)).map(item -> item.getQuantity()).mapToInt(quantity -> (quantity * prices.A.getPrice())).sum();
        int SKUID_B_Price = cartItems.getItems().stream().filter(item -> item.getSkuId().equalsIgnoreCase(SKUId.SKUID_B)).map(item -> item.getQuantity()).mapToInt(quantity -> (quantity * prices.B.getPrice())).sum();
        int SKUID_C_Price = cartItems.getItems().stream().filter(item -> item.getSkuId().equalsIgnoreCase(SKUId.SKUID_C)).map(item -> item.getQuantity()).mapToInt(quantity -> (quantity * prices.C.getPrice())).sum();
        int SKUID_D_Price = cartItems.getItems().stream().filter(item -> item.getSkuId().equalsIgnoreCase(SKUId.SKUID_D)).map(item -> item.getQuantity()).mapToInt(quantity -> (quantity * prices.D.getPrice())).sum();

        totalOrderValue = totalOrderValue + SKUID_A_Price + SKUID_B_Price + SKUID_C_Price + SKUID_D_Price;

        return totalOrderValue;
    }

    public Integer countOrderTotalWithPromotion(CartItemsRequest cartItems) {
        int orderTotal = 0;

        //check if the cart is eligible for promotion A, if Yes then applyPromoA
        orderTotal = orderTotal + applyPromotionA(cartItems);

        //check if the cart is eligible for promotion B, if Yes then applyPromoB
        orderTotal = orderTotal + applyPromotionB(cartItems);

        //check if the cart is eligible for promotion CandD, if Yes then applyPromoCandD
        orderTotal = orderTotal + applyPromotionCAndD(cartItems);

        return orderTotal;
    }

    public Integer applyPromotionA(CartItemsRequest cartItemsRequest) {

        Integer promoAPrice = 0;

        Integer itemA_quantity = cartItemsRequest.getItems().stream().filter(items -> items.getSkuId().equalsIgnoreCase(SKUId.SKUID_A)).mapToInt(item -> item.getQuantity()).sum();
        Integer individualItemPrice = (itemA_quantity % promoA_limit) * prices.A.getPrice();
        Integer promoPrice = (itemA_quantity / promoA_limit) * promoA_price;

        promoAPrice = promoPrice + individualItemPrice;

        return promoAPrice;
    }

    public Integer applyPromotionB(CartItemsRequest cartItemsRequest) {

        Integer promoBPrice = 0;

        Integer itemB_quantity = cartItemsRequest.getItems().stream().filter(items -> items.getSkuId().equalsIgnoreCase(SKUId.SKUID_B)).mapToInt(item -> item.getQuantity()).sum();
        Integer individualItemPrice = (itemB_quantity % promoB_limit) * prices.B.getPrice();
        Integer promoPrice = (itemB_quantity / promoB_limit) * promoB_price;

        promoBPrice = promoPrice + individualItemPrice;

        return promoBPrice;
    }

    public Integer applyPromotionCAndD(CartItemsRequest cartItemsRequest) {

        Integer promoMixPrice = 0;
        Integer promoPrice = 0;
        Integer individualItemPrice = 0;

        Integer itemC_quantity = cartItemsRequest.getItems().stream().filter(items -> items.getSkuId().equalsIgnoreCase(SKUId.SKUID_C)).mapToInt(item -> item.getQuantity()).sum();
        Integer itemD_quantity = cartItemsRequest.getItems().stream().filter(items -> items.getSkuId().equalsIgnoreCase(SKUId.SKUID_D)).mapToInt(item -> item.getQuantity()).sum();
        if (itemC_quantity < itemD_quantity && itemC_quantity != 0 && null != itemC_quantity) {
            promoPrice = itemC_quantity * promoMix_price;
            individualItemPrice = ((itemC_quantity+itemD_quantity) % itemC_quantity) * prices.D.getPrice();
        } else if(itemC_quantity.equals(itemD_quantity) && itemD_quantity != 0 && itemC_quantity != 0 && null != itemC_quantity && null != itemD_quantity) {
            promoPrice = itemC_quantity * promoMix_price;
        } else if (null != itemD_quantity && itemD_quantity != 0) {
            promoPrice = itemD_quantity * promoMixPrice;
            individualItemPrice = ((itemC_quantity+itemD_quantity) % itemD_quantity) * prices.C.getPrice();
        } else if (itemC_quantity != null) {
            individualItemPrice = (itemC_quantity) * prices.C.getPrice();
        } else if (itemD_quantity != null) {
            individualItemPrice = (itemD_quantity) * prices.D.getPrice();
        }

        promoMixPrice = promoPrice + individualItemPrice;

        return promoMixPrice;
    }

}

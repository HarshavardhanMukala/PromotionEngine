package com.example.PromotionEngine.model;

import java.util.List;

public class CartItemsRequest {

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private List<Item> items;


}

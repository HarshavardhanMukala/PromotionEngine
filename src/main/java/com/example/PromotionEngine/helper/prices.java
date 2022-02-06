package com.example.PromotionEngine.helper;

public enum prices {

    A(50),
    B(30),
    C(20),
    D(15);

    private final Integer price;

    prices(Integer price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

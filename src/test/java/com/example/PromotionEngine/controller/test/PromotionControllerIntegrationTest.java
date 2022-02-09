package com.example.PromotionEngine.controller.test;

import com.example.PromotionEngine.model.CartItemsRequest;
import com.example.PromotionEngine.model.Item;
import com.example.PromotionEngine.model.OrderTotalDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PromotionControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private CartItemsRequest cartItemsRequest;

    @Test
    public void testScenarioA() {
        Item item1 = new Item();
        item1.setSkuId("A");
        item1.setQuantity(1);
        Item item2 = new Item();
        item2.setSkuId("B");
        item2.setQuantity(1);
        Item item3 = new Item();
        item3.setSkuId("C");
        item3.setQuantity(1);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        cartItemsRequest = new CartItemsRequest();
        cartItemsRequest.setItems(itemList);

        HttpEntity<CartItemsRequest> request = new HttpEntity<>(cartItemsRequest);

        ResponseEntity<OrderTotalDetails> response = restTemplate.postForEntity("/promotionEngine", request, OrderTotalDetails.class);

        assertEquals("100", response.getBody().getOrderTotalWithPromotion().toString());

    }

    @Test
    public void testScenarioB() {
        Item item1 = new Item();
        item1.setSkuId("A");
        item1.setQuantity(5);
        Item item2 = new Item();
        item2.setSkuId("B");
        item2.setQuantity(5);
        Item item3 = new Item();
        item3.setSkuId("C");
        item3.setQuantity(1);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        cartItemsRequest = new CartItemsRequest();
        cartItemsRequest.setItems(itemList);

        HttpEntity<CartItemsRequest> request = new HttpEntity<>(cartItemsRequest);

        ResponseEntity<OrderTotalDetails> response = restTemplate.postForEntity("/promotionEngine", request, OrderTotalDetails.class);

        assertEquals("370", response.getBody().getOrderTotalWithPromotion().toString());

    }

    @Test
    public void testScenarioC() {
        Item item1 = new Item();
        item1.setSkuId("A");
        item1.setQuantity(3);
        Item item2 = new Item();
        item2.setSkuId("B");
        item2.setQuantity(5);
        Item item3 = new Item();
        item3.setSkuId("C");
        item3.setQuantity(1);
        Item item4 = new Item();
        item4.setSkuId("D");
        item4.setQuantity(1);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);

        cartItemsRequest = new CartItemsRequest();
        cartItemsRequest.setItems(itemList);

        HttpEntity<CartItemsRequest> request = new HttpEntity<>(cartItemsRequest);

        ResponseEntity<OrderTotalDetails> response = restTemplate.postForEntity("/promotionEngine", request, OrderTotalDetails.class);

        assertEquals("280", response.getBody().getOrderTotalWithPromotion().toString());

    }

}

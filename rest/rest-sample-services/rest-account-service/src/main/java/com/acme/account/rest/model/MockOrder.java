package com.acme.account.rest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockOrder {

    public static Order getOne() {
        Order order = new Order();
        order.setOrderPrice(120);
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderName("Order-" + order.getOrderId());
        order.setNew20Property("New 2.0 Property");
        return order;
    }

    public static List<Order> getOrders() {
        List<Order> orders = new ArrayList<Order>();
        orders.add(getOne());
        orders.add(getOne());
        orders.add(getOne());
        orders.add(getOne());
        orders.add(getOne());
        orders.add(getOne());
        return orders;
    }

}
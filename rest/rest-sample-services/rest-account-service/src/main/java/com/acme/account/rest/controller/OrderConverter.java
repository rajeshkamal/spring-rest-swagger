package com.acme.account.rest.controller;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.account.rest.model.Order;

/**
 * Order Converter
 */
@Component
public class OrderConverter extends
        ResourceAssemblerSupport<com.acme.account.dal.model.Order, Order> {

    public OrderConverter() {
        super(OrderController.class, Order.class);
    }

    @Override
    public final Order toResource(final com.acme.account.dal.model.Order entity) {
        Order apiOrder = createResourceWithId(entity.getOrderId(), entity);
        apiOrder.setOrderId(entity.getOrderId());
        apiOrder.setOrderName(entity.getOrderName());
        apiOrder.setOrderPrice(entity.getOrderPrice());
        apiOrder.setNew20Property(entity.getNew20Property());
        return apiOrder;
    }

    public final com.acme.account.dal.model.Order fromResource(final Order apiOrder) {
        com.acme.account.dal.model.Order entityOrder = new com.acme.account.dal.model.Order();
        entityOrder.setOrderId(apiOrder.getOrderId());
        entityOrder.setOrderName(apiOrder.getOrderName());
        entityOrder.setOrderPrice(apiOrder.getOrderPrice());
        entityOrder.setNew20Property(apiOrder.getNew20Property());
        return entityOrder;
    }

}

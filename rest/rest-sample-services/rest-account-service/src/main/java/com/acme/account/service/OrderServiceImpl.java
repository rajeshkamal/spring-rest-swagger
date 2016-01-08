package com.acme.account.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.acme.account.dal.model.Order;
import com.acme.account.repository.OrderRepository;
import com.acme.common.rest.exception.NotFoundException;
import com.acme.common.search.RSQLSpecificationFactory;

@Component
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private RSQLSpecificationFactory<Order> rsqlSpecFactory;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository,
            final RSQLSpecificationFactory<Order> rsqlSpecFactory) {
        this.orderRepository = orderRepository;
        this.rsqlSpecFactory = rsqlSpecFactory;
    }


    @Override
    public Order create(Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        return orderRepository.save(order);
    }

    @Override
    public Order get(String orderId) {
        Order order = orderRepository.findOne(orderId);
        if (order != null) {
            return order;
        }
        throw new NotFoundException(Order.class, orderId);
    }

    @Override
    public void delete(String orderId) {
        orderRepository.delete(orderId);
    }

    @Override
    public Order update(Order order) {
        if (orderRepository.exists(order.getOrderId())) {
            return orderRepository.save(order);
        }
        throw new NotFoundException(Order.class, order.getOrderId());
    }

    @Override
    public Page<Order> search(Pageable pageable, String search) {
        return orderRepository.findAll(rsqlSpecFactory.createRSQLSpecification(search), pageable);
    }

}

package com.acme.account.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.acme.account.dal.model.Order;
import com.acme.common.rest.exception.NotFoundException;

/**
 * VDI Service
 */
public interface OrderService {

	Order create(Order vdi);

	Order get(String vdiId) throws NotFoundException;

	void delete(String vdiId);

	Order update(Order vdi);

	Page<Order> search(Pageable pageable, String search);

}

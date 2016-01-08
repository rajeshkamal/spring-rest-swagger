package com.acme.account.repository;

import com.acme.account.dal.model.Order;
import com.acme.common.repository.BaseRepository;

/**
 * The {@link Order} {@link BaseRepository}.
 */
public interface OrderRepository extends BaseRepository<Order, String> {
}

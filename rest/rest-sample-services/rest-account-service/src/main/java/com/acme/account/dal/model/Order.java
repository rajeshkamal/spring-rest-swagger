package com.acme.account.dal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.acme.common.rest.model.BaseEntity;

/**
 * The User Entity.
 */
@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    /** Ordr ID. */
    @Id
    private String orderId;

    /** Order Name. */
    @Column(nullable = false)
    private String orderName;

    /** Order Price. */
    @Column(nullable = false)
    private Integer orderPrice;

    /** New 2.0 Property. */
    private String new20Property;

}

package com.acme.account.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.acme.account.rest.model.MockOrder;
import com.acme.account.rest.model.Order;

@RestController
@RequestMapping("internals")
@ApiIgnore
public class HiddenController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Order> getInternalRacks() {
        return MockOrder.getOrders();
    }

}

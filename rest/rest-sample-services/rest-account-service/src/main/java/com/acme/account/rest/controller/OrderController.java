package com.acme.account.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.account.rest.model.MockTask;
import com.acme.account.rest.model.Order;
import com.acme.account.rest.model.Task;
import com.acme.account.service.OrderService;

/**
 * A Spring Controller to handle {@link Order} CRUD operations.
 */
@RestController
@RequestMapping("orders")
@Api(tags = { "OrderService" })
public class OrderController {

    private OrderService orderService;
    private OrderConverter orderConverter;

    @Autowired
    public OrderController(final OrderService orderService, final OrderConverter orderConverter) {
        this.orderService = orderService;
        this.orderConverter = orderConverter;
    }

    @ApiOperation(value = "List Orders", notes = "Get the list of all Orders from the system", nickname = "getOrders")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "Size of the page you want to retrieve. Default page size is 10.", required = false, defaultValue = "10", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "Page you want to retrieve. Default page number is 0.", required = false, defaultValue = "0", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "Properties that should be sorted by in the format property,property(,ASC|DESC). "
                    + "Default sort direction is ascending. Use multiple sort parameters if you want to switch directions, "
                    + "e.g. ?sort=firstname&sort=lastname,asc.", required = false, dataType = "String", paramType = "query") })
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public PagedResources<Order> getOrders(
            @PageableDefault final Pageable pageable,
            final PagedResourcesAssembler<com.acme.account.dal.model.Order> pagedAssembler,
            @ApiParam(value = "Search Order params", required = false) @RequestParam(value = "search", required = false) final String search) {
        return pagedAssembler.toResource(orderService.search(pageable, search), orderConverter);
    }

    /**
     * Create a {@link com.acme.account.rest.model.Order}.
     *
     * @param order
     *            The {@link com.acme.account.rest.model.Order} to create.
     * @return The {@link com.acme.account.rest.model.Order} if it was created successfully.
     */
    @ApiOperation(value = "Create Order", notes = "Create and configure Order", nickname = "createOrder", code = 201, response = Order.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public final Order createOrder(
            @ApiParam(value = "Create Order configuration", required = true) @RequestBody(required = true) final Order order) {
        return orderConverter.toResource(orderService.create(orderConverter.fromResource(order)));
    }

    /**
     * Given a ID, return a {@link com.acme.account.rest.model.Order}.
     *
     * @param orderId
     *            The ID to lookup a {@link com.acme.account.rest.model.Order} by.
     * @return The {@link com.acme.account.rest.model.Order} if found
     */
    @ApiOperation(value = "Get Order", notes = "Get the details of a specific Order", nickname = "getOrder", response = Order.class, code = 200)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public final Order getOrder(
            @ApiParam(value = "Order's unique identifier", required = true) @PathVariable final String orderId) {
        return orderConverter.toResource(orderService.get(orderId));
    }

    /**
     * Update a given {@link com.acme.account.rest.model.Order}.
     *
     * @param orderId
     *            The Order ID to update
     * @param order
     *            The {@link com.acme.account.rest.model.Order} to update
     * @return The updated {@link com.acme.account.rest.model.Order} if found
     */
    @ApiOperation(value = "Update Order", notes = "Update the configurable details of a Order", nickname = "updateOrder", response = Order.class, code = 200)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public final Order updateOrder(
            @ApiParam(value = "Order's unique identifier", required = true) @PathVariable final String orderId,
            @ApiParam(value = "Order's configuration that needs to be updated", required = true) @RequestBody(required = true) final Order order) {
        return orderConverter.toResource(orderService.update(orderConverter.fromResource(order)));
    }

    /**
     * Delete a {@link com.acme.account.rest.model.Order} given an ID.
     *
     * @param orderId
     *            The Order ID to be deleted
     */
    @ApiOperation(value = "Delete Order", notes = "Delete a specific Order", nickname = "deleteOrder", code = 204)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public final void deleteOrder(
            @ApiParam(value = "Order's unique identifier", required = true) @PathVariable final String orderId) {
        orderService.delete(orderId);
    }

    /**
     * Deprecated operation.
     *
     * @param orderId
     *            The Order ID to perform the deprecated operation
     */
    @ApiOperation(value = "Deprecated operation", nickname = "deprecatedOperation", code = 204)
    @Deprecated
    @RequestMapping(value = "/{orderId}/action/deprecated-op", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public final void deprecatedOperation(
            @ApiParam(value = "Order's unique identifier", required = true) @PathVariable final String orderId) {
    }

    /**
     * Cancel the Order.
     */
    @ApiOperation(value = "Cancel Order", notes = "Cancel the Order", nickname = "cancelOrder", code = 202)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(value = "/{orderId}/action/cancel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public final Task enterMaintenance(
            @ApiParam(value = "Order's unique identifier", required = true) @PathVariable final String orderId) {
        return MockTask.getOne(orderId, "Cancel-Order");
    }

    /**
     * Provide discount for the Order.
     *
     * @param orderId
     *            The Order ID to perform the private operation
     */
    @ApiOperation(hidden = true, value = "Provide discount for the Order", nickname = "provideDiscount", code = 204)
    @RequestMapping(value = "/{orderId}/action/discount", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public final void privateOperation(
            @ApiParam(value = "Order's unique identifier", required = true) @PathVariable final String orderId) {
    }

}

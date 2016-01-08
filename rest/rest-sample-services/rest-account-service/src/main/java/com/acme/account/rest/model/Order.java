package com.acme.account.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.hateoas.ResourceSupport;

import com.acme.common.rest.version.ApiVersion;
import com.google.gson.annotations.Since;

/**
 * Order Model.
 */
@Getter
@Setter
@ToString
@ApiModel(description = "Order model")
public class Order extends ResourceSupport {

    @ApiModelProperty(readOnly = true, value = "Order unique identifier", required = true)
    private String orderId;

    @ApiModelProperty(value = "Order name", required = true)
    private String orderName;

    @ApiModelProperty(value = "Order Price", required = true)
    private Integer orderPrice;

    @ApiModelProperty(value = "New 2.0 property")
    @Since(ApiVersion.VERSION_2_0)
    private String new20Property;

    @ApiModelProperty(value = "Deprecated property")
    @Deprecated
    private String deprecatedProperty;

}

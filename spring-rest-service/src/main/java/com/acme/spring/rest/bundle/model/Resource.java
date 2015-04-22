package com.acme.spring.rest.bundle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Common Resource Model")
@JsonInclude(Include.NON_NULL)
public class Resource {

    @ApiModelProperty(required = true, value = "Resource Name")
    protected String name;
    @ApiModelProperty(required = true, value = "Resource Identifier")
    protected String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}

package com.acme.spring.rest.bundle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Bundle Element Model")
@JsonInclude(Include.NON_NULL)
public class BundleElement {

    @ApiModelProperty(required = false, value = "Bundle Element File Path")
    protected String fileLocation;
    @ApiModelProperty(required = false, value = "Bundle Element Product Name")
    protected String productName;

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


}

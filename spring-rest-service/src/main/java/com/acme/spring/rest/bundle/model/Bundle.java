package com.acme.spring.rest.bundle.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Bundle Model")
@JsonInclude(Include.NON_NULL)
public class Bundle extends Resource {

	@ApiModelProperty(required = true, value = "Bundle Description")
	protected String description;
	@ApiModelProperty(required = true, value = "Bundle File Names")
	protected Set<String> fileNames;
	@ApiModelProperty(required = false, value = "Bundle Size")
	protected Long size;
	@ApiModelProperty(required = false, value = "Bundle Version")
	protected String version;
	@ApiModelProperty(required = false, value = "Bundle Elements")
	protected List<BundleElement> bundleElements;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(Set<String> fileNames) {
		this.fileNames = fileNames;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<BundleElement> getBundleElements() {
		return bundleElements;
	}

	public void setBundleElements(List<BundleElement> bundleElements) {
		this.bundleElements = bundleElements;
	}

}

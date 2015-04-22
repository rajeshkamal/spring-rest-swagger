package com.acme.spring.rest.bundle.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.spring.rest.bundle.model.Bundle;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
public class BundleController {

	@ApiOperation(value = "List all bundles")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/bundle", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			"application/vnd.acme.collection.bundle+json" })
	@ResponseStatus(value = HttpStatus.OK)
	public Collection<Bundle> list(
			@RequestParam(value = "version", required = false) String version) {
		return null;
	}

	@ApiOperation(value = "Create a bundle")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/bundle", method = RequestMethod.POST, consumes = { "application/vnd.acme.bundle+json" }, produces = { "application/vnd.acme.bundle+json" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public Bundle create(@RequestBody(required = true) Bundle bundle) {
		return null;
	}

	@ApiOperation(value = "Get a bundle")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/bundle/{bundleId}", method = RequestMethod.GET, produces = { "application/vnd.acme.bundle+json" })
	@ResponseStatus(value = HttpStatus.OK)
	public Bundle get(@PathVariable String bundleId) {
		return null;
	}

	@ApiOperation(value = "Update a bundle")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/bundle/{bundleId}", method = RequestMethod.PUT, consumes = { "application/vnd.acme.bundle+json" }, produces = { "application/vnd.acme.bundle+json" })
	@ResponseStatus(value = HttpStatus.OK)
	public Bundle update(@PathVariable String bundleId,
			@RequestBody(required = true) Bundle bundle) {
		return null;
	}

	@ApiOperation(value = "Delete a bundle")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "NO_CONTENT"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/bundle/{bundleId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public @ResponseBody void delete(@PathVariable String bundleId) {
	}

}
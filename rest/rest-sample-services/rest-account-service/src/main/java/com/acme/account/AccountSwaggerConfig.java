package com.acme.account;

import org.springframework.context.annotation.Configuration;

import com.acme.common.rest.springfox.SwaggerConfig;

@Configuration
public class AccountSwaggerConfig extends SwaggerConfig {

    @Override
    protected String getServiceName() {
        return "Account Service";
    }

    @Override
    protected String getServletContextPath() {
        return "/account";
    }

}
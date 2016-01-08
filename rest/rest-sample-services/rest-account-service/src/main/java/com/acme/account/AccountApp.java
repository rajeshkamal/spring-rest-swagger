package com.acme.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.acme.common.rest.BaseWebConfig;

/**
 * The Main Application.
 */
@SpringBootApplication
@ComponentScan({ "com.acme.account", "com.acme.common" })
public class AccountApp {

    /**
     * The main function for the application.
     *
     * @param args
     *            The arguments to main.
     */
    public static void main(final String[] args) {
        SpringApplication.run(
                new Object[] { AccountApp.class, AccountSwaggerConfig.class, BaseWebConfig.class }, args);
    }

}
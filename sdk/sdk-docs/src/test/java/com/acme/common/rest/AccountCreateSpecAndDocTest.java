package com.acme.common.rest;

import org.springframework.test.context.ContextConfiguration;

import com.acme.account.AccountApp;

@ContextConfiguration(classes = { AccountApp.class, BaseWebConfig.class })
public class AccountCreateSpecAndDocTest extends AbstractCreateSpecAndDocTest {

    @Override
    protected String getFeatureName() {
        return "account";
    }

}
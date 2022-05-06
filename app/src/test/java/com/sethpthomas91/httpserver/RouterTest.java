package com.sethpthomas91.httpserver;

import org.junit.Assert;
import org.junit.Test;

public class RouterTest {
    @Test
    public void testRouterReturnsTrueForResourceThatExists() {
        Router router = new Router();
        String uniformResourceIdentifier = "/simple_get";
        Assert.assertTrue(router.resourceExists(uniformResourceIdentifier));
    }

    @Test
    public void testRouterReturnsFalseForResourceThatExists() {
        Router router = new Router();
        String uniformResourceIdentifier = "/invalid_resource";
        Assert.assertFalse(router.resourceExists(uniformResourceIdentifier));
    }

    @Test
    public void testRouterReturnsTrueIfMethodIsAllowedOnGivenResource() {
        Router router = new Router();
        String uniformResourceIdentifier = "/simple_get";
        String typeOfRequest = "GET";
        Assert.assertTrue(router.methodAllowed(typeOfRequest, uniformResourceIdentifier));
    }

    @Test
    public void testRouterReturnsFalseIfMethodIsNotAllowedOnGivenResource() {
        Router router = new Router();
        String uniformResourceIdentifier = "/simple_get";
        String typeOfRequest = "POST";
        Assert.assertFalse(router.methodAllowed(typeOfRequest, uniformResourceIdentifier));
    }
}

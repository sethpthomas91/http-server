package com.sethpthomas91.httpserver.request;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestHeadersTest {

    @Test
    public void testRequestHeadersGetContentTypeShouldReturnContentType() {
        RequestHeaders headers = new RequestHeaders();
        String incomingHeaders = "Content-Type:application/json";
        headers.processHeaders(incomingHeaders);
        Assert.assertEquals("application/json", headers.getContentType());
    }
}

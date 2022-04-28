package com.sethpthomas91.httpserver;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HttpResponseWrapperTest {

    @Test
    public void testsStringifyHttpResponseReturnsProperStatusLine() {
        HttpResponseWrapper httpResponse = new HttpResponseWrapper();
        httpResponse.setHttpVersion("HTTP/1.1");
        httpResponse.setResponseText("OK");
        httpResponse.setStatusCode("202");
        Assert.assertEquals("HTTP/1.1 202 OK\r\n", httpResponse.stringifyHttpResponse());
    }
}
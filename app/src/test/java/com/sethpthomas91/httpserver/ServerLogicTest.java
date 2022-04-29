package com.sethpthomas91.httpserver;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerLogicTest {

    @Test
    public void testReturnsOptionsToMethodOptionsRequest() {
        HttpRequestWrapper request = new HttpRequestWrapper("OPTIONS /method_options HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header headers = response.getHeaders();
        String options = headers.options();
        Assert.assertEquals("GET, HEAD, OPTIONS", options);
    }

    @Test
    public void testReturnsOptionsToMethodOptions2Request() {
        HttpRequestWrapper request = new HttpRequestWrapper("OPTIONS /method_options2 HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header headers = response.getHeaders();
        String options = headers.options();
        Assert.assertEquals("GET, HEAD, OPTIONS, PUT, POST", options);
    }

    @Test
    public void testGetRequestToPageThatDoesNotExistReturns404() {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /unknown_page HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("404", statusCode);
    }

}
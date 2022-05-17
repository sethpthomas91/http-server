package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.ServerLogic;
import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HealthCheckHandlerTest {
    @Test
    public void testGetRequestToHealthCheckResponseShouldRespondWith200() throws IOException {
        String requestString = "GET /health-check.html HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        HealthCheckHandler healthCheckHandler = new HealthCheckHandler();
        HttpResponseWrapper httpResponse = healthCheckHandler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToHealthCheckResponseShouldRespondWithContentTypeHeader() throws IOException {
        String requestString = "GET /health-check.html HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        HealthCheckHandler healthCheckHandler = new HealthCheckHandler();
        HttpResponseWrapper httpResponse = healthCheckHandler.handle(httpRequest);
        Header header = httpResponse.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("text/html;charset=utf-8", contentType);
    }

    @Test
    public void testImageHandlerShouldReturnGetForAllowedMethods() throws IOException {
        HealthCheckHandler handler = new HealthCheckHandler();
        String[] allowedMethods = handler.getAllowedMethods();
        String[] expected = {"GET"};
        Assert.assertArrayEquals(expected, allowedMethods);
    }
}

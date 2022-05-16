package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MethodNotAllowedHandlerTest {
    @Test
    public void testGetRequestToSimpleGetShouldRespondWith405() throws IOException {
        String requestString = "GET /head_request HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        MethodNotAllowedHandler handler = new MethodNotAllowedHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("405", statusCode);
    }

    @Test
    public void testGetRequestToSimpleGetShouldRespondWithCorrectHeaders() throws IOException {
        String requestString = "GET /head_request HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        MethodNotAllowedHandler handler = new MethodNotAllowedHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        Header header = httpResponse.getHeaders();
        String allowedHeaders = header.options();
        Assert.assertEquals("HEAD, OPTIONS", allowedHeaders);
    }
}

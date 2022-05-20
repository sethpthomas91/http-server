package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HeadRequestHandlerTest {

    @Test
    public void testGetRequestToHeadReturnsStatus405() throws IOException {
        String requestString = "GET /head_request HTTP/1.1\r\n";
        HttpRequestWrapper request = new HttpRequestWrapper(requestString);
        HeadRequestHandler handler = new HeadRequestHandler();
        HttpResponseWrapper response = handler.handle(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("405", statusCode);
    }

    @Test
    public void testHeadRequestToHeadReturnsStatus200() throws IOException {
        String requestString = "HEAD /head_request HTTP/1.1\r\n";
        HttpRequestWrapper request = new HttpRequestWrapper(requestString);
        HeadRequestHandler handler = new HeadRequestHandler();
        HttpResponseWrapper response = handler.handle(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testHeadRequestShouldHaveAllowedHeadersOfHeadAndOptions() throws IOException {
        String requestString = "HEAD /head_request HTTP/1.1\r\n";
        HttpRequestWrapper request = new HttpRequestWrapper(requestString);
        HeadRequestHandler handler = new HeadRequestHandler();
        HttpResponseWrapper response = handler.handle(request);
        Header headers = response.getHeaders();
        String options = headers.options();
        Assert.assertEquals("HEAD, OPTIONS", options);
    }

}

package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class OptionsHandler1Test {

    @Test
    public void testOptionsRequestToMethodOptionsShouldReturn200() throws IOException {
        String requestString = "OPTIONS /method_options HTTP/1.1";
        HttpRequestWrapper request = new HttpRequestWrapper(requestString);
        OptionsHandler1 handler = new OptionsHandler1();
        HttpResponseWrapper response = handler.handle(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testOptionsRequestShouldReturnProperAllowedHeaders() throws IOException {
        String requestString = "OPTIONS /method_options HTTP/1.1";
        HttpRequestWrapper request = new HttpRequestWrapper(requestString);
        OptionsHandler1 handler = new OptionsHandler1();
        HttpResponseWrapper response = handler.handle(request);
        Header headers = response.getHeaders();
        String options = headers.options();
        Assert.assertEquals("GET, HEAD, OPTIONS", options);
    }
}

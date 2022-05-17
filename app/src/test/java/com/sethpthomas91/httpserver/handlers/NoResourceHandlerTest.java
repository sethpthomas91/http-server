package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class NoResourceHandlerTest {

    @Test
    public void testGetRequestToSimpleGetShouldRespondWith200() throws IOException {
        String requestString = "GET /not_a_resource HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        NoResourceHandler handler = new NoResourceHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("404", statusCode);
    }

}
package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DefaultHandlerTest {

    @Test
    public void testGetRequestToSimpleGetShouldRespondWith200() throws IOException {
        String requestString = "GET /simple_get HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToSimpleGetWithBodyShouldRespondWith200() throws IOException {
        String requestString = "GET /simple_get_with_body HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToSimpleGetWithBodyShouldRespondWithHelloWorldInBody() throws IOException {
        String requestString = "GET /simple_get_with_body HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler healthCheckHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = healthCheckHandler.handle(httpRequest);
        Body body = httpResponse.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("Hello world", bodyText);
    }

}

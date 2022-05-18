package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EchoHandlerTest {

    @Test
    public void testPostRequestToEchoBodyReturns200() throws IOException {
        String requestString = "POST /echo_body HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        EchoHandler handler = new EchoHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testPostRequestToEchoBodyReturnsSomeBody() throws IOException {
        String requestString = "POST /echo_body HTTP/1.1\r\n\r\nsome body";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        EchoHandler handler = new EchoHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        Body body = httpResponse.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("some body", bodyText);
    }

    @Test
    public void testGetAllowedMethodsReturnsProperAllowedMethods() {
        EchoHandler handler = new EchoHandler();
        String[] allowedMethods = handler.getAllowedMethods();
        String[] expectedMethods = {"POST"};
        Assert.assertArrayEquals(expectedMethods, allowedMethods);
    }


}
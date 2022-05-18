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

public class TextHandlerTest {

    @Test
    public void testGetRequestToTextResponseReturns200() throws IOException {
        String requestString = "GET /text_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        TextHandler handler = new TextHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToTextResponseReturnsTextInBody() throws IOException {
        String requestString = "GET /text_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        TextHandler handler = new TextHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        Body body = httpResponse.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("text response", bodyText);
    }

    @Test
    public void testGetRequestToTextResponseReturnsTextContentType() throws IOException {
        String requestString = "GET /text_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        TextHandler handler = new TextHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        Header header = httpResponse.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("text/plain;charset=utf-8", contentType);
    }

    @Test
    public void testGetAllowedMethodsReturnsProperMethods() {
        TextHandler handler = new TextHandler();
        String[] allowedMethods = handler.getAllowedMethods();
        String[] expectedMethods = {"GET"};
        Assert.assertArrayEquals(expectedMethods, allowedMethods);
    }
}

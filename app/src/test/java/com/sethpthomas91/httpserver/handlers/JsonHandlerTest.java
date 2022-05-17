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

public class JsonHandlerTest {

    @Test
    public void testGetRequestToJsonResponseReturnsJsonContentType() throws IOException {
        String requestString = "GET /json_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        JsonHandler handler = new JsonHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        Header header = httpResponse.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("application/json;charset=utf-8", contentType);
    }

    @Test
    public void testGetRequestToJsonResponseReturnsJsonInBody() throws IOException {
        String requestString = "GET /json_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        JsonHandler handler = new JsonHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        Body body = httpResponse.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", bodyText);
    }

    @Test
    public void testGetRequestToJsonResponseReturns200() throws IOException {
        String requestString = "GET /json_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        JsonHandler handler = new JsonHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testJsonHandlerReturnsProperAllowedMethods() {
        JsonHandler handler = new JsonHandler();
        String[] allowedMethods = handler.getAllowedMethods();
        String[] expected = {"GET"};
        Assert.assertArrayEquals(expected, allowedMethods);
    }
}

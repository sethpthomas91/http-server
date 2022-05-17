package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class HtmlHandlerTest {

    @Test
    public void testGetRequestToHtmlResponseShouldResponseWith200() throws IOException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /html_response HTTP/1.1\r\n");
        HtmlHandler handler = new HtmlHandler();
        HttpResponseWrapper response = handler.handle(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToHtmlResponseShouldRespondWithContentTypeHeader() throws IOException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /html_response HTTP/1.1\r\n");
        HtmlHandler handler = new HtmlHandler();
        HttpResponseWrapper response = handler.handle(request);
        Header header = response.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("text/html;charset=utf-8", contentType);
    }

    @Test
    public void testGetRequestToHtmlResponseShouldRespondWithHtmlInBody() throws IOException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /html_response HTTP/1.1\r\n");
        HtmlHandler handler = new HtmlHandler();
        HttpResponseWrapper response = handler.handle(request);
        Body body = response.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("<html><body><p>HTML Response</p></body></html>", bodyText);
    }

    @Test
    public void testJsonHandlerReturnsProperAllowedMethods() {
        HtmlHandler handler = new HtmlHandler();
        String[] allowedMethods = handler.getAllowedMethods();
        String[] expected = {"GET"};
        Assert.assertArrayEquals(expected, allowedMethods);
    }

}
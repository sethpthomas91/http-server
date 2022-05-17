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
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        Body body = httpResponse.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("Hello world", bodyText);
    }

    @Test
    public void testPostRequestToEchoBodyReturns200() throws IOException {
        String requestString = "POST /echo_body HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testPostRequestToEchoBodyReturnsSomeBody() throws IOException {
        String requestString = "POST /echo_body HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        Body body = httpResponse.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("some body", bodyText);
    }

    @Test
    public void testGetRequestToTextResponseReturns200() throws IOException {
        String requestString = "GET /text_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToTextResponseReturnsTextInBody() throws IOException {
        String requestString = "GET /text_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        Body body = httpResponse.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("text response", bodyText);
    }

    @Test
    public void testGetRequestToTextResponseReturnsTextContentType() throws IOException {
        String requestString = "GET /text_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        Header header = httpResponse.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("text/plain;charset=utf-8", contentType);
    }

    @Test
    public void testGetRequestToHtmlResponseReturns200() throws IOException {
        String requestString = "GET /html_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToHtmlResponseReturnsHtmlInBody() throws IOException {
        String requestString = "GET /html_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        Body body = httpResponse.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("<html><body><p>HTML Response</p></body></html>", bodyText);
    }

    @Test
    public void testGetRequestToHtmlResponseReturnsHtmlContentType() throws IOException {
        String requestString = "GET /html_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        Header header = httpResponse.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("text/html;charset=utf-8", contentType);
    }

    @Test
    public void testGetRequestToRedirectResponseReturnsStatus301() throws IOException {
        String requestString = "GET /redirect HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("301", statusCode);
    }

    @Test
    public void testGetRequestToRedirectResponseHeaderShouldIncludeLocation() throws IOException {
        String requestString = "GET /redirect HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        DefaultHandler defaultHandler = new DefaultHandler();
        HttpResponseWrapper httpResponse = defaultHandler.handle(httpRequest);
        Header header = httpResponse.getHeaders();
        String location = header.location();
        Assert.assertEquals("http://127.0.0.1:5000/simple_get", location);
    }

}

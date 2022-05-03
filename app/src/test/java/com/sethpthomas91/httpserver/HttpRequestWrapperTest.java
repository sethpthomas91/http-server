package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.request.RequestLine;
import org.junit.Assert;
import org.junit.Test;

public class HttpRequestWrapperTest {
    String CRLF = "\r\n";

    @Test
    public void testsForCorrectGetHttpMethod() {
        String incomingRequestString = "GET / HTTP/1.1" + CRLF + CRLF;
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(incomingRequestString);
        RequestLine requestLine = httpRequest.getRequestLine();
        String typeOfRequest = requestLine.getTypeOfRequest();
        Assert.assertEquals("GET", typeOfRequest);
    }

    @Test
    public void testsForCorrectPostHttpMethod() {
        String incomingRequestString = "POST / HTTP/1.1" + CRLF + CRLF;
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(incomingRequestString);
        RequestLine requestLine = httpRequest.getRequestLine();
        String typeOfRequest = requestLine.getTypeOfRequest();
        Assert.assertEquals("POST", typeOfRequest);
    }

    @Test
    public void testsHandlesBaseUriCall() {
        String incomingRequestString = "GET / HTTP/1.1" + CRLF + CRLF;
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(incomingRequestString);
        RequestLine requestLine = httpRequest.getRequestLine();
        String uri = requestLine.getUniformResourceIdentifier();
        Assert.assertEquals("/", uri);
    }

    @Test
    public void testsHandlesComplexUriCall() {
        String incomingRequestString = "GET simple_get/ HTTP/1.1" + CRLF + CRLF;
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(incomingRequestString);
        RequestLine requestLine = httpRequest.getRequestLine();
        String uri = requestLine.getUniformResourceIdentifier();
        Assert.assertEquals("simple_get/", uri);
    }

    @Test
    public void testsHandlesHttpVersion() {
        String incomingRequestString = "GET simple_get/ HTTP/1.1" + CRLF + CRLF;
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(incomingRequestString);
        RequestLine requestLine = httpRequest.getRequestLine();
        String httpVersion = requestLine.getHttpVersion();
        Assert.assertEquals("HTTP/1.1", httpVersion);
    }

    @Test
    public void testsSetRequestBody() {
        String incomingRequestString = "GET simple_get/ HTTP/1.1" + CRLF + CRLF + "This is the body";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(incomingRequestString);
        String body = httpRequest.getBody();
        Assert.assertEquals("This is the body", body);
    }
}
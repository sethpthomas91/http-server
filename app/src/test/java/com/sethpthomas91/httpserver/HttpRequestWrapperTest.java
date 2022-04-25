package com.sethpthomas91.httpserver;

import org.junit.Assert;
import org.junit.Test;

public class HttpRequestWrapperTest {
    String CRLF = "\r\n";

    @Test
    public void testsForCorrectGetHttpMethod() {
        String httpRequest = "GET / HTTP/1.1" + CRLF;
        HttpRequestWrapper httpRequestObject = new HttpRequestWrapper(httpRequest);
        Assert.assertEquals("GET", httpRequestObject.getTypeOfRequest());
    }

    @Test
    public void testsForFalsePositivesForGetRequests() {
        String httpRequest = "GET / HTTP/1.1" + CRLF;
        HttpRequestWrapper httpRequestObject = new HttpRequestWrapper(httpRequest);
        Assert.assertNotEquals(" ", httpRequestObject.getTypeOfRequest());
    }

    @Test
    public void testsForCorrectPostHttpMethod() {
        String httpRequest = "POST / HTTP/1.1" + CRLF;
        HttpRequestWrapper httpRequestObject = new HttpRequestWrapper(httpRequest);
        Assert.assertEquals("POST", httpRequestObject.getTypeOfRequest());
    }

    @Test
    public void testsHandlesBaseUriCall() {
        String httpRequest = "GET / HTTP/1.1" + CRLF;
        HttpRequestWrapper httpRequestObject = new HttpRequestWrapper(httpRequest);
        Assert.assertEquals("/", httpRequestObject.getUniformResourceIdentifier());
    }

    @Test
    public void testsHandlesComplexUriCall() {
        String httpRequest = "GET simple_get/ HTTP/1.1" + CRLF;
        HttpRequestWrapper httpRequestObject = new HttpRequestWrapper(httpRequest);
        Assert.assertEquals("simple_get/", httpRequestObject.getUniformResourceIdentifier());
    }

    @Test
    public void testsHandlesHttpVersion() {
        String httpRequest = "GET simple_get/ HTTP/1.1" + CRLF;
        HttpRequestWrapper httpRequestObject = new HttpRequestWrapper(httpRequest);
        Assert.assertEquals("HTTP/1.1", httpRequestObject.getHttpVersion());
    }
}
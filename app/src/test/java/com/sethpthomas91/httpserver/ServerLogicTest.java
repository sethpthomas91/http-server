package com.sethpthomas91.httpserver;

import org.junit.Assert;
import org.junit.Test;

public class ServerLogicTest {

    @Test
    public void testReturnsOptionsToMethodOptionsRequest() {
        HttpRequestWrapper request = new HttpRequestWrapper("OPTIONS /method_options HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header headers = response.getHeaders();
        String options = headers.options();
        Assert.assertEquals("GET, HEAD, OPTIONS", options);
    }

    @Test
    public void testReturnsOptionsToMethodOptions2Request() {
        HttpRequestWrapper request = new HttpRequestWrapper("OPTIONS /method_options2 HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header headers = response.getHeaders();
        String options = headers.options();
        Assert.assertEquals("GET, HEAD, OPTIONS, PUT, POST", options);
    }

    @Test
    public void testGetRequestToPageThatDoesNotExistReturns404() {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /unknown_page HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("404", statusCode);
    }

    @Test
    public void testHeadRequestToPageThatDoesExistReturns200() {
        HttpRequestWrapper request = new HttpRequestWrapper("HEAD /simple_get HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToHeadRequestResponseShouldBe405() {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /head_request HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String options = header.options();
        Assert.assertEquals("HEAD, OPTIONS", options);
    }

    @Test
    public void testHeadRequestToHeadRequestTheStatusCodeShouldBe200() {
        HttpRequestWrapper request = new HttpRequestWrapper("HEAD /head_request HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToSimpleGetWithBodyTheStatusCodeShouldBe200() {
        HttpRequestWrapper request = new HttpRequestWrapper("HEAD /simple_get_with_body HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToSimpleGetWithBodyTheBodyShouldBeHelloWorld() {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /simple_get_with_body HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Body body = response.getBody();
        String bodyText = body.getText();
        Assert.assertEquals("Hello world", bodyText);
    }

    @Test
    public void testGetRequestToRedirectShouldHaveStatusCode301() {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /redirect HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("301", statusCode);
    }

    @Test
    public void testGetRequestToRedirectShouldHaveHeaderWithNewLocation() {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /redirect HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String location = header.location();
        Assert.assertEquals("http://127.0.0.1:5000/simple_get", location);
    }

    @Test
    public void testGetRequestToRedirectShouldHaveBodySameAsRequestBody() {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /redirect HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String location = header.location();
        Assert.assertEquals("http://127.0.0.1:5000/simple_get", location);
    }

    @Test
    public void testPostRequestWithBodyShouldReturnStatusCode200() {
        HttpRequestWrapper request = new HttpRequestWrapper("POST /echo_body HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testPostRequestWithBodyShouldEchoTheBody() {
        String requestBody = "echo it back yall";
        HttpRequestWrapper request = new HttpRequestWrapper("POST /echo_body HTTP/1.1\r\n\r\n" + requestBody);
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Body body = response.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals(requestBody, bodyText);
    }

}
package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import com.sun.net.httpserver.Headers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class ServerLogicTest {

    @Test
    public void testReturnsOptionsToMethodOptionsRequest() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("OPTIONS /method_options HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header headers = response.getHeaders();
        String options = headers.options();
        Assert.assertEquals("GET, HEAD, OPTIONS", options);
    }

    @Test
    public void testReturnsOptionsToMethodOptions2Request() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("OPTIONS /method_options2 HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header headers = response.getHeaders();
        String options = headers.options();
        Assert.assertEquals("GET, HEAD, OPTIONS, PUT, POST", options);
    }

    @Test
    public void testGetRequestToPageThatDoesNotExistReturns404() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /unknown_page HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("404", statusCode);
    }

    @Test
    public void testHeadRequestToPageThatDoesExistReturns200() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("HEAD /simple_get HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToHeadRequestResponseShouldBe405() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /head_request HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String options = header.options();
        Assert.assertEquals("HEAD, OPTIONS", options);
    }

    @Test
    public void testHeadRequestToHeadRequestTheStatusCodeShouldBe200() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("HEAD /head_request HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToSimpleGetWithBodyTheStatusCodeShouldBe200() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /simple_get_with_body HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToSimpleGetWithBodyTheBodyShouldBeHelloWorld() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /simple_get_with_body HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Body body = response.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("Hello world", bodyText);
    }

    @Test
    public void testGetRequestToRedirectShouldHaveStatusCode301() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /redirect HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("301", statusCode);
    }

    @Test
    public void testGetRequestToRedirectShouldHaveHeaderWithNewLocation() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /redirect HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String location = header.location();
        Assert.assertEquals("http://127.0.0.1:5000/simple_get", location);
    }

    @Test
    public void testGetRequestToRedirectShouldHaveBodySameAsRequestBody() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /redirect HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String location = header.location();
        Assert.assertEquals("http://127.0.0.1:5000/simple_get", location);
    }

    @Test
    public void testPostRequestWithBodyShouldReturnStatusCode200() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("POST /echo_body HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testPostRequestWithBodyShouldEchoTheBody() throws IOException, URISyntaxException, InterruptedException {
        String requestBody = "some body";
        HttpRequestWrapper request = new HttpRequestWrapper("POST /echo_body HTTP/1.1\r\n\r\n" + requestBody);
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Body body = response.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals(requestBody, bodyText);
    }

    @Test
    public void testGetRequestToTextResponseShouldRespondWith200() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /text_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToTextResponseShouldRespondWithTextInBody() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /text_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Body body = response.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("text response", bodyText);
    }

    @Test
    public void testGetRequestToTextResponseShouldRespondWithContentTypeHeader() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /text_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("text/plain;charset=utf-8", contentType);
    }

    @Test
    public void testGetRequestToHtmlResponseShouldResponseWith200() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /html_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToHtmlResponseShouldRespondWithContentTypeHeader() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /html_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("text/html;charset=utf-8", contentType);
    }

    @Test
    public void testGetRequestToHtmlResponseShouldRespondWithHtmlInBody() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /html_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Body body = response.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("<html><body><p>HTML Response</p></body></html>", bodyText);
    }

    @Test
    public void testGetRequestToJsonResponseShouldResponseWith200() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /json_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToJsonResponseShouldRespondWithContentTypeHeader() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /json_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("application/json;charset=utf-8", contentType);
    }

    @Test
    public void testGetRequestToJsonResponseShouldRespondWithJsonInBody() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /json_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Body body = response.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", bodyText);
    }

    @Test
    public void testGetRequestToXmlResponseShouldResponseWith200() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /xml_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToXmlResponseShouldRespondWithContentTypeHeader() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /xml_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("application/xml;charset=utf-8", contentType);
    }

    @Test
    public void testGetRequestToXmlResponseShouldRespondWithXmlInBody() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /xml_response HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Body body = response.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("<note><body>XML Response</body></note>", bodyText);
    }

    @Test
    public void testGetRequestToHealthCheckResponseShouldRespondWith200() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /health-check.html HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToHealthCheckResponseShouldRespondWithContentTypeHeader() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /health-check.html HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("text/html;charset=utf-8", contentType);
    }

    @Test
    public void testGetRequestToKittehJpgShouldBe200() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /kitteh.jpg HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testGetRequestToKittehJpgHeaderContentTypeShouldBeImageJpeg() throws IOException, URISyntaxException, InterruptedException {
        HttpRequestWrapper request = new HttpRequestWrapper("GET /kitteh.jpg HTTP/1.1\r\n");
        ServerLogic serverLogic = new ServerLogic();
        HttpResponseWrapper response = serverLogic.processRequest(request);
        Header header = response.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("image/jpeg", contentType);
    }
}

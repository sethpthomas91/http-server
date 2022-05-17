package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class XmlHandlerTest {

    @Test
    public void testGetRequestToXmlResponseReturnsXmlInBody() throws IOException {
        String requestString = "GET /xml_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        XmlHandler handler = new XmlHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        Body body = httpResponse.getBody();
        String bodyText = body.getBody();
        Assert.assertEquals("<note><body>XML Response</body></note>", bodyText);
    }

    @Test
    public void testGetRequestToXmlResponseReturnsXmlContentType() throws IOException {
        String requestString = "GET /xml_response HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        XmlHandler handler = new XmlHandler();
        HttpResponseWrapper httpResponse = handler.handle(httpRequest);
        Header header = httpResponse.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("application/xml;charset=utf-8", contentType);
    }

    @Test
    public void testXmlHandlerReturnsProperAllowedMethods() {
        XmlHandler handler = new XmlHandler();
        String[] allowedMethods = handler.getAllowedMethods();
        String[] expected = {"GET"};
        Assert.assertArrayEquals(expected, allowedMethods);
    }

}
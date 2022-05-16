package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.handlers.ImageHandler;
import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import com.sethpthomas91.httpserver.utils.StaticVariables;
import org.junit.Assert;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class ImageHandlerTest {

    @Test
    public void testImageHandlerShouldTakeInKittehAndReturnStatusOf200() throws IOException {
        String requestString = "GET /kitteh.jpg HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        ImageHandler imageHandler = new ImageHandler();
        HttpResponseWrapper httpResponse = imageHandler.handle(httpRequest);
        StatusLine statusLine = httpResponse.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("200", statusCode);
    }

    @Test
    public void testImageHandlerShouldTakeInRequestAndReturnCorrectBody() throws IOException {
        String requestString = "GET /kitteh.jpg HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        ImageHandler imageHandler = new ImageHandler();
        HttpResponseWrapper httpResponse = imageHandler.handle(httpRequest);
        Body body = httpResponse.getBody();
        byte[] bodyBytes = body.getBodyBytes();
        String pathString = StaticVariables.getPublicDirectory() + "kitteh.jpg";
        byte[] imageData = Files.readAllBytes(new File(pathString).toPath());
        Assert.assertArrayEquals(imageData, bodyBytes);
    }

    @Test
    public void testImageHandlerShouldTakeInRequestAndReturnCorrectHeaders() throws IOException {
        String requestString = "GET /kitteh.jpg HTTP/1.1\r\n";
        HttpRequestWrapper httpRequest = new HttpRequestWrapper(requestString);
        ImageHandler imageHandler = new ImageHandler();
        HttpResponseWrapper httpResponse = imageHandler.handle(httpRequest);
        Header header = httpResponse.getHeaders();
        String contentType = header.getContentType();
        Assert.assertEquals("image/jpeg", contentType);
    }
}

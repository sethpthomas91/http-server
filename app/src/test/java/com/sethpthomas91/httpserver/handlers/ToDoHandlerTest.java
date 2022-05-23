package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.mocks.HttpClientWrapperMock;
import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class ToDoHandlerTest {

//    @Test
//    public void testPostRequestToToDoListReturnsStatusOf201IfIncomingBodyIsJson() throws IOException, URISyntaxException, InterruptedException {
//        String stringRequest = "POST /todo HTTP/1.1\r\nContent-Type: application/json\r\n\r\n{\"task\":\"a new task\"}";
//        HttpRequestWrapper request = new HttpRequestWrapper(stringRequest);
//        HttpClientWrapperMock clientMocker = new HttpClientWrapperMock();
//        ToDoHandler handler = new ToDoHandler(clientMocker);
//        HttpResponseWrapper response = handler.handle(request);
//        StatusLine statusLine = response.getStatusLine();
//        String statusCode = statusLine.getStatusCode();
//        Assert.assertEquals("201", statusCode);
//    }
//
//    @Test
//    public void testPostRequestToToDoListReturnsStatusOf415IfIncomingBodyIsNotJson() throws IOException, URISyntaxException, InterruptedException {
//        String stringRequest = "POST /todo HTTP/1.1\r\nContent-Type: text/xml\r\n";
//        HttpRequestWrapper request = new HttpRequestWrapper(stringRequest);
//        HttpClientWrapperMock clientMocker = new HttpClientWrapperMock();
//        ToDoHandler handler = new ToDoHandler(clientMocker);
//        HttpResponseWrapper response = handler.handle(request);
//        StatusLine statusLine = response.getStatusLine();
//        String statusCode = statusLine.getStatusCode();
//        Assert.assertEquals("415", statusCode);
//    }

}
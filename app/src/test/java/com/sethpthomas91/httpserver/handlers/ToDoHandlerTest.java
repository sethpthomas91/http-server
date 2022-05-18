package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ToDoHandlerTest {

    @Test
    public void testPostRequestToToDoListReturnsStatusOf201IfIncomingBodyIsJson() throws IOException {
        String stringRequest = "POST /to_do HTTP/1.1\r\nContent-Type: application/json\r\n";
        HttpRequestWrapper request = new HttpRequestWrapper(stringRequest);
        ToDoHandler handler = new ToDoHandler();
        HttpResponseWrapper response = handler.handle(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("201", statusCode);
    }

    @Test
    public void testPostRequestToToDoListReturnsStatusOf415IfIncomingBodyIsNotJson() throws IOException {
        String stringRequest = "POST /to_do HTTP/1.1\r\nContent-Type: text/xml\r\n";
        HttpRequestWrapper request = new HttpRequestWrapper(stringRequest);
        ToDoHandler handler = new ToDoHandler();
        HttpResponseWrapper response = handler.handle(request);
        StatusLine statusLine = response.getStatusLine();
        String statusCode = statusLine.getStatusCode();
        Assert.assertEquals("415", statusCode);
    }

}
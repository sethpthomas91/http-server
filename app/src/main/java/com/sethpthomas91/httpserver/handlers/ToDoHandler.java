package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;

import java.io.IOException;

public class ToDoHandler implements Handler {
    private String[] allowedMethods = {"POST"};

    @Override
    public HttpResponseWrapper handle(HttpRequestInterface httpRequest) throws IOException {
        HttpResponseWrapper httpResponse = new HttpResponseWrapper();
        httpResponse = handleStatusLine(httpRequest, httpResponse);
        httpResponse = handleBody(httpRequest, httpResponse);
        httpResponse = handleHeaders(httpRequest, httpResponse);
        return httpResponse;
    }

    private HttpResponseWrapper handleStatusLine(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) {
        StatusLine statusLine = new StatusLine();
        statusLine.setStatusCode("201");
        statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
        statusLine.setResponseText("Resource created");
        httpResponse.setStatusLine(statusLine);
        return httpResponse;
    }

    private boolean isJsonContentInHeader(HttpRequestInterface httpRequest) {
        return false;
    }

    private HttpResponseWrapper handleBody(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Body body = new Body();
        httpResponse.setBody(body);
        return httpResponse;
    }

    private HttpResponseWrapper handleHeaders(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Header header = new Header(httpRequest, httpResponse);
        httpResponse.setHeaders(header);
        return httpResponse;
    }

    @Override
    public String[] getAllowedMethods() {
        return allowedMethods;
    }
}

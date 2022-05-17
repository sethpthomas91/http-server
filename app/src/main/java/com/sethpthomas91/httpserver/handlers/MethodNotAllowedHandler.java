package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.Router;
import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import com.sethpthomas91.httpserver.utils.ByteArrayGenerator;

import java.io.IOException;

public class MethodNotAllowedHandler implements Handler{
    @Override
    public HttpResponseWrapper handle(HttpRequestInterface httpRequest) throws IOException {
        HttpResponseWrapper httpResponse = new HttpResponseWrapper();
        httpResponse = handleStatusLine(httpRequest, httpResponse);
        httpResponse = handleBody(httpRequest, httpResponse);
        httpResponse = handleHeaders(httpRequest, httpResponse);
        return httpResponse;
    }

    @Override
    public String[] getAllowedMethods() {
        return null;
    }

    private HttpResponseWrapper handleStatusLine(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) {
        StatusLine statusLine = new StatusLine();
        statusLine.setStatusCode("405");
        statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
        statusLine.setResponseText("Method not allowed");
        httpResponse.setStatusLine(statusLine);
        return httpResponse;
    }

    private HttpResponseWrapper handleBody(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Body body = new Body();
        httpResponse.setBody(body);
        return httpResponse;
    }

    private HttpResponseWrapper handleHeaders(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Header header = new Header(httpRequest, httpResponse);
        Router router = new Router();
        header.setAllowedHeaders(router.getAllowedMethodsForUri(httpRequest.getRequestLine().getUniformResourceIdentifier()));
        httpResponse.setHeaders(header);
        return httpResponse;
    }
}

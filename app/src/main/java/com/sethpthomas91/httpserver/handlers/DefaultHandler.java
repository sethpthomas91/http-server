package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;

import java.io.IOException;

public class DefaultHandler implements Handler{

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
        statusLine.setStatusCode("200");
        statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
        statusLine.setResponseText("OK");
        httpResponse.setStatusLine(statusLine);
        return httpResponse;
    }

    private HttpResponseWrapper handleBody(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Body body = new Body(httpRequest.getRequestLine().getUniformResourceIdentifier());
        switch (httpRequest.getRequestLine().getUniformResourceIdentifier()) {
            case "/simple_get_with_body": body.setBodyText("Hello world");
            break;
        }
        httpResponse.setBody(body);
        return httpResponse;
    }

    private HttpResponseWrapper handleHeaders(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Header header = new Header(httpRequest, httpResponse);
        httpResponse.setHeaders(header);
        return httpResponse;
    }
}

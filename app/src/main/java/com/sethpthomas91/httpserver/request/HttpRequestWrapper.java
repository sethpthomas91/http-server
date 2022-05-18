package com.sethpthomas91.httpserver.request;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.request.RequestLine;

import java.util.Arrays;

public class HttpRequestWrapper implements HttpRequestInterface {
    String CRLF = "\r\n";

    RequestLine requestLine;
    RequestHeaders headers;
    String body;

    public HttpRequestWrapper(String incomingRequest) {
        String[] splitRequest = incomingRequest.split(CRLF+CRLF);
        String top = splitRequest[0];
        this.requestLine = handleRequestLine(top);
        if (headersPresent(top.split(CRLF))) {
            this.headers = handleRequestHeaders(incomingRequest);
        }
        if (bodyPresent(splitRequest)) {
            this.body = handleBody(incomingRequest);
        }
    }

    private boolean headersPresent(String[] headersArray) {
        return headersArray.length >= 2;
    }

    public boolean hasHeaders() {
        return headers != null;
    }

    private boolean bodyPresent(String[] splitRequest) {
        return splitRequest.length == 2;
    }

    private RequestLine handleRequestLine(String requestLineAndHeaders) {
        String requestLineString = requestLineAndHeaders.split(CRLF)[0];
        return new RequestLine(requestLineString);
    }

    private RequestHeaders handleRequestHeaders(String incomingRequest) {
        String requestLineAndHeaders = incomingRequest.split(CRLF+CRLF)[0];
        String headersString = requestLineAndHeaders.split(CRLF, 2)[1];
        RequestHeaders headers = new RequestHeaders();
        headers.processHeaders(headersString);
        return headers;
    }

    private String handleBody(String incomingRequest) {
        String body = incomingRequest.split(CRLF+CRLF)[1];
        return body;
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    @Override
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public RequestHeaders getHeaders() {
        return this.headers;
    }
}

package com.sethpthomas91.httpserver.request;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.request.RequestLine;

public class HttpRequestWrapper implements HttpRequestInterface {
    String CRLF = "\r\n";

    RequestLine requestLine;
    RequestHeaders headers;
    String body;

    public HttpRequestWrapper(String incomingRequest) {
        String[] splitRequest = incomingRequest.split(CRLF+CRLF);
        String top = splitRequest[0];
        this.requestLine = handleRequestLine(top);
        if (hasHeaders(top.split(CRLF))) {
            this.headers = handleRequestHeaders(incomingRequest);
        }
        if (hasBody(splitRequest)) {
            this.body = handleBody(incomingRequest);
        }
    }

    private boolean hasHeaders(String[] headersArray) {
        return headersArray.length == 2;
    }

    private boolean hasBody(String[] splitRequest) {
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
}

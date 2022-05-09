package com.sethpthomas91.httpserver.request;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.request.RequestLine;

public class HttpRequestWrapper implements HttpRequestInterface {
    String CRLF = "\r\n";
    String SPACE = " ";

    RequestLine requestLine;
    Boolean hasBody;
    String body;
    String requestLineAndHeaders;

    public HttpRequestWrapper(String incomingRequest) {
        splitTopFromBody(incomingRequest);
        splitRequestLineAndHeaders(requestLineAndHeaders);
    }

    private void splitRequestLineAndHeaders(String requestLineAndHeaders) {
        String[] splitRequestLineAndHeaders = requestLineAndHeaders.split(CRLF);
        requestLine = new RequestLine(splitRequestLineAndHeaders[0]);
    }

    private void splitTopFromBody(String request) {
        System.out.println(request);
        String[] splitTopFromBody = request.split(CRLF+CRLF);
        requestLineAndHeaders = splitTopFromBody[0];
        if (checkRequestForBody(splitTopFromBody)) {
            setBody(splitTopFromBody[1]);
        }
    }

    private boolean checkRequestForBody(String[] splitRequest) {
        if (splitRequest.length == 2) {
            this.hasBody = true;
            return true;
        } else {
            this.hasBody = false;
            return false;
        }
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

    public boolean requestHasBody() {
        return hasBody;
    }
}

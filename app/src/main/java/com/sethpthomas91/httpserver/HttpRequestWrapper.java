package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;

public class HttpRequestWrapper implements HttpRequestInterface {
    String CRLF = "\r\n";
    String SPACE = " ";

    RequestLine requestLine;

    public HttpRequestWrapper(String incomingRequest) {
        splitHttpRequest(incomingRequest);
    }

    private void splitHttpRequest(String request) {
        String[] splitRequest = request.split(CRLF);
        requestLine = new RequestLine(splitRequest[0]);
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }
}

package com.sethpthomas91.httpserver;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class HttpRequestWrapper {
    String CRLF = "\r\n";
    String SPACE = " ";

    String requestLine;
    String typeOfRequest;
    String uniformResourceIdentifier;
    String httpVersion;

    public HttpRequestWrapper(String incomingRequest) {
        splitHttpRequest(incomingRequest);
        splitRequestLine(requestLine);
    }

    public String getTypeOfRequest() {
        return typeOfRequest;
    }

    public String getUniformResourceIdentifier() {
        return uniformResourceIdentifier;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    private void splitHttpRequest(String request) {
        String[] splitRequest = request.split(CRLF);
        requestLine = splitRequest[0];
    }

    private void splitRequestLine(String requestLine) {
        System.out.println(requestLine);
        String[] splitRequestLine = requestLine.split(SPACE);
        this.typeOfRequest = splitRequestLine[0];
        this.uniformResourceIdentifier = splitRequestLine[1];
        this.httpVersion = splitRequestLine[2];
    }
}

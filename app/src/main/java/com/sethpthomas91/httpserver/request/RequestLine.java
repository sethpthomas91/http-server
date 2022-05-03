package com.sethpthomas91.httpserver.request;

public class RequestLine {
    public String getTypeOfRequest;
    String typeOfRequest;
    String uniformResourceIdentifier;
    String httpVersion;

    public RequestLine(String requestLineString) {
        splitRequestLine(requestLineString);
    }

    private void splitRequestLine(String requestLine) {
        String[] splitRequestLine = requestLine.split(" ");
        this.typeOfRequest = splitRequestLine[0];
        this.uniformResourceIdentifier = splitRequestLine[1];
        this.httpVersion = splitRequestLine[2];
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
}

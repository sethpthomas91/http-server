package com.sethpthomas91.httpserver.response;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;

public class Header {
    String CRLF = "\r\n";

    String allowedHeaders;

    String contentLength;
    String newLocation;
    String contentType;
    String stringifiedHeaders = "";

    public Header(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) {
        setContentLengthHeader(httpResponse.getBody());
    }

    public String options() {
        return allowedHeaders;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setAllowedHeaders(String allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }
    public String getHeaders() {
        buildStringifiedHeaders();
        return stringifiedHeaders;
    }

    private void buildStringifiedHeaders() {
        if (allowedHeaders != null) {
            stringifiedHeaders += "Allow: " + allowedHeaders + CRLF;
        }
        if (contentLength != null) {
            stringifiedHeaders += "Content-Length: " + contentLength + CRLF;
        }
        if (newLocation != null) {
            stringifiedHeaders += "Location: " + newLocation + CRLF;
        }
        if (contentType != null) {
            stringifiedHeaders += "Content-Type: " + contentType + CRLF;
        }
    }

    private void setContentLengthHeader(Body body) {
        if (body.getBody() != null) {
            contentLength = String.valueOf(body.getBody().length());
        }
        if (body.getBodyBytes() != null) {
            contentLength = String.valueOf(body.getBodyBytes().length);
        }

    }

    public String location() {
        return newLocation;
    }

    public void setLocation(String location) {
        this.newLocation = location;
    }
}

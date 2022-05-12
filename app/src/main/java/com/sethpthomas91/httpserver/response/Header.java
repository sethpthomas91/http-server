package com.sethpthomas91.httpserver.response;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;

public class Header {
    String CRLF = "\r\n";

    String allowedHeaders;
    String methodOptionsAllowedHeaders = "GET, HEAD, OPTIONS";
    String methodOptions2AllowedHeaders = "GET, HEAD, OPTIONS, PUT, POST";
    String headRequestAllowedHeaders = "HEAD, OPTIONS";

    String contentLength;
    String newLocation;
    String contentType;
    String stringifiedHeaders = "";

    public Header(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) {
        processUri(httpRequest.getRequestLine().getUniformResourceIdentifier());
        setContentLengthHeader(httpResponse.getBody());
    }

    private void processUri(String uniformResourceIdentifier){
        if (uniformResourceIdentifier.equals("/method_options")) {
            allowedHeaders = methodOptionsAllowedHeaders;
        }
        else if (uniformResourceIdentifier.equals("/method_options2")) {
            allowedHeaders = methodOptions2AllowedHeaders;
        }
        else if (uniformResourceIdentifier.equals("/head_request")) {
            allowedHeaders = headRequestAllowedHeaders;
        }
        else if (uniformResourceIdentifier.equals("/redirect")) {
            newLocation = "http://127.0.0.1:5000/simple_get";
        }
        else if (uniformResourceIdentifier.equals("/text_response")) {
            contentType = "text/plain;charset=utf-8";
        }
        else if (uniformResourceIdentifier.equals("/html_response") || uniformResourceIdentifier.equals("/health-check.html")) {
            contentType = "text/html;charset=utf-8";
        }
        else if (uniformResourceIdentifier.equals("/json_response")) {
            contentType = "application/json;charset=utf-8";
        }
        else if (uniformResourceIdentifier.equals("/xml_response")) {
            contentType = "application/xml;charset=utf-8";
        }
        else if (uniformResourceIdentifier.equals("/kitteh.jpg")) {
            contentType = "image/jpeg";
        }
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
}

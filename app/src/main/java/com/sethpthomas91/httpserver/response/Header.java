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
    }

    public String options() {
        return allowedHeaders;
    }

    public String getHeaders() {
        if (allowedHeaders != null) {
            return "Allow: " + allowedHeaders + CRLF;
        }
        if (contentLength != null) {
            return "Content-Length: " + contentLength + CRLF;
        }
        if (newLocation != null) {
            return "Location: " + newLocation + CRLF;
        }
        else {
            return "";
        }
    }

    private void setContentLengthHeader(Body body) {
        if (body.getBody() != null) {
            contentLength = String.valueOf(body.getBody().length());
        }

    }

    public String location() {
        return newLocation;
    }
}

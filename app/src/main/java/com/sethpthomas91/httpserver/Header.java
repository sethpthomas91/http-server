package com.sethpthomas91.httpserver;

public class Header {
    String CRLF = "\r\n";

    String allowedHeaders;
    String methodOptionsAllowedHeaders = "GET, HEAD, OPTIONS";
    String methodOptions2AllowedHeaders = "GET, HEAD, OPTIONS, PUT, POST";
    String headRequestAllowedHeaders = "HEAD, OPTIONS";

    String contentLength;

    String newLocation;

    public Header(String typeOfRequest, String uniformResourceIdentifier) {
        processUri(uniformResourceIdentifier);
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
        else if (uniformResourceIdentifier.equals("/simple_get_with_body")) {
            contentLength = "11";
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

    public String location() {
        return newLocation;
    }
}

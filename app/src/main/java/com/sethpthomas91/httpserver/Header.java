package com.sethpthomas91.httpserver;

public class Header {
    String allowedHeaders;
    String methodOptionsAllowedHeaders = "GET, HEAD, OPTIONS";
    String methodOptions2AllowedHeaders = "GET, HEAD, OPTIONS, PUT, POST";

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
    }

    public String options() {
        return allowedHeaders;
    }

    public String getHeaders() {
        if (!allowedHeaders.isEmpty()) {
            return "Allow: " + allowedHeaders + "\r\n";
        }
        else {
            return "";
        }

    }
}

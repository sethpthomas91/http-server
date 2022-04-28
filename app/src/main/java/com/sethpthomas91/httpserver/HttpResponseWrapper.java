package com.sethpthomas91.httpserver;

public class HttpResponseWrapper {
    String CRLF = "\r\n";
    String SPACE = " ";

    String stringHttpResponse;

    String statusLine;
    String httpVersion;
    String statusCode;
    String responseText;

    Header header;
    boolean hasHeaders;

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String stringifyHttpResponse() {
        assembleStatusLine();
        stringHttpResponse = statusLine;
        if (hasHeaders) {
            this.stringHttpResponse += header.getHeaders();
        }
        return stringHttpResponse;
    }

    private void assembleStatusLine() {
        this.statusLine =  httpVersion + SPACE + statusCode + SPACE + responseText + CRLF;
    }

    public void setHeaders(Header header) {
        hasHeaders = true;
        this.header = header;
    }

    public Header getHeaders() {
        return this.header;
    }
}

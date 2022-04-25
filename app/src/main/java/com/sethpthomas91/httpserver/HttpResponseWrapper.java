package com.sethpthomas91.httpserver;

public class HttpResponseWrapper {
    String CRLF = "\r\n";
    String SPACE = " ";

    String statusLine;
    String httpVersion;
    String statusCode;
    String responseText;

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
        String stringHttpResponse = statusLine;
        return stringHttpResponse;
    }

    private void assembleStatusLine() {
        this.statusLine =  httpVersion + SPACE + statusCode + SPACE + responseText + CRLF;
    }

}

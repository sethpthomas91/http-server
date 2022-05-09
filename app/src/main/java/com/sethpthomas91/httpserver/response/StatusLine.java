package com.sethpthomas91.httpserver.response;

import com.sethpthomas91.httpserver.request.RequestLine;

public class StatusLine {
    String CRLF = "\r\n";
    String SPACE = " ";

    String httpVersion;
    String statusCode;
    String responseText;


    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getStatusLine() {
        return httpVersion + SPACE + statusCode + SPACE + responseText + CRLF;
    }

}

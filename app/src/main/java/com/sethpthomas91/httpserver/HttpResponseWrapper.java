package com.sethpthomas91.httpserver;

public class HttpResponseWrapper {

    String stringHttpResponse;

    StatusLine statusLine;

    Header header;
    boolean hasHeaders;

    public String stringifyHttpResponse() {
        stringHttpResponse = statusLine.getStatusLine();
        if (hasHeaders) {
            stringHttpResponse += header.getHeaders();
        }
        return stringHttpResponse;
    }


    public void setHeaders(Header header) {
        hasHeaders = true;
        this.header = header;
    }

    public Header getHeaders() {
        return this.header;
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public StatusLine getStatusLine() {
        return this.statusLine;
    }
}

package com.sethpthomas91.httpserver;

public class HttpResponseWrapper {

    String CRLF = "\r\n";
    String stringHttpResponse;
    StatusLine statusLine;
    Header header;
    boolean hasHeaders;
    Body body;
    boolean hasBody;

    public String stringifyHttpResponse() {
        stringHttpResponse = statusLine.getStatusLine();
        if (hasHeaders) {
            stringHttpResponse += header.getHeaders();
        }
        if (hasBody) {
            stringHttpResponse += CRLF + body.getBody();
        }
        System.out.println(stringHttpResponse);
        return stringHttpResponse;
    }


    public void setHeaders(Header header) {
        this.hasHeaders = true;
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

    public void setBody(Body body) {
        this.hasBody = true;
        this.body = body;
    }

    public Body getBody() {
        return this.body;
    }
}

package com.sethpthomas91.httpserver.response;

import com.sethpthomas91.httpserver.utils.ByteArrayGenerator;

import java.nio.charset.StandardCharsets;

public class HttpResponseWrapper {

    String CRLF = "\r\n";
    String stringHttpResponse;
    StatusLine statusLine;
    Header header;
    boolean hasHeaders;
    Body body;

    public String stringifyHttpResponse() {
        StringBuilder sb = new StringBuilder();
        sb.append(statusLine.getStatusLine());
        if (hasHeaders) {
            sb.append(header.getHeaders());
        }
        if (body.getBody() != null) {
            sb.append(CRLF);
            sb.append(body.getBody());
        }
        return sb.toString();
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
        this.body = body;
    }

    public Body getBody() {
        return this.body;
    }

    public byte[] getBytes() {
        return ByteArrayGenerator.convertStringToBytes(this.stringifyHttpResponse());
    }
}

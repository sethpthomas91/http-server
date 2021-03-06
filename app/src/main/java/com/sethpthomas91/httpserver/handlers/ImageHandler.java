package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import com.sethpthomas91.httpserver.utils.ByteArrayGenerator;
import com.sethpthomas91.httpserver.utils.StaticVariables;

import java.io.IOException;

public class ImageHandler implements Handler {
    private String[] allowedMethods = {"GET"};

    public HttpResponseWrapper handle(HttpRequestInterface httpRequest) throws IOException {
        HttpResponseWrapper httpResponse = new HttpResponseWrapper();
        httpResponse = handleStatusLine(httpRequest, httpResponse);
        httpResponse = handleBody(httpRequest, httpResponse);
        httpResponse = handleHeaders(httpRequest, httpResponse);
        return httpResponse;
    }

    @Override
    public String[] getAllowedMethods() {
        return allowedMethods;
    }

    private HttpResponseWrapper handleStatusLine(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) {
        StatusLine statusLine = new StatusLine();
        statusLine.setStatusCode("200");
        statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
        statusLine.setResponseText("OK");
        httpResponse.setStatusLine(statusLine);
        return httpResponse;
    }

    private HttpResponseWrapper handleBody(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Body body = new Body();
        String fileLocation = StaticVariables.getPublicDirectory() + httpRequest.getRequestLine().getUniformResourceIdentifier();
        body.setBodyBytes(ByteArrayGenerator.convertFileToBytes(fileLocation));
        httpResponse.setBody(body);
        return httpResponse;
    }

    private String getFileType(HttpRequestInterface httpRequest) {
        return httpRequest.getRequestLine().getUniformResourceIdentifier().split("[.]")[1];
    }

    private HttpResponseWrapper handleHeaders(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse)throws IOException {
        Header header = new Header(httpRequest, httpResponse);
        switch (getFileType(httpRequest)) {
            case "jpg":
                header.setContentType("image/jpeg");
                break;
            case "png":
                header.setContentType("image/png");
                break;
            case "gif":
                header.setContentType("image/gif");
                break;
        }
        httpResponse.setHeaders(header);
        return httpResponse;
    }
}

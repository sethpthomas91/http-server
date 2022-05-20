package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.request.RequestHeaders;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import com.sethpthomas91.httpserver.utils.HttpClientWrapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ToDoHandler implements Handler {
    private String[] allowedMethods = {"POST"};

    @Override
    public HttpResponseWrapper handle(HttpRequestInterface httpRequest) throws IOException, URISyntaxException, InterruptedException {
        HttpResponseWrapper httpResponse = new HttpResponseWrapper();
        httpResponse = handleStatusLine(httpRequest, httpResponse);
        httpResponse = handleBody(httpRequest, httpResponse);
        httpResponse = handleHeaders(httpRequest, httpResponse);
        return httpResponse;
    }

    private HttpResponseWrapper handleStatusLine(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws URISyntaxException, IOException, InterruptedException {
        StatusLine statusLine = new StatusLine();
        System.out.println(httpRequest.hasHeaders());
        if (httpRequest.hasHeaders() && hasJsonContentType(httpRequest.getHeaders())) {

            HttpClientWrapper proxy = new HttpClientWrapper();
            proxy.actAsProxy(httpRequest);

            statusLine.setStatusCode("201");
            statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
            statusLine.setResponseText("Resource created");
        } else if (httpRequest.hasHeaders() && hasXFormContentType(httpRequest.getHeaders())){
            statusLine.setStatusCode("400");
            statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
            statusLine.setResponseText("Bad request");
        }else {
            statusLine.setStatusCode("415");
            statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
            statusLine.setResponseText("Unsupported media type");
        }
        httpResponse.setStatusLine(statusLine);
        return httpResponse;
    }

    private boolean hasJsonContentType(RequestHeaders headers) {
        String contentType = headers.getContentType();
        return contentType.equals("application/json");
    }

    private boolean hasXFormContentType(RequestHeaders headers) {
        String contentType = headers.getContentType();
        return contentType.equals("application/x-www-form-urlencoded");
    }

    private HttpResponseWrapper handleBody(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Body body = new Body();
        if (httpResponse.getStatusLine().getStatusCode().equals("201")) {
            body.setBodyText("{\"task\":\"a new task\"}");
        }
        httpResponse.setBody(body);
        return httpResponse;
    }

    private HttpResponseWrapper handleHeaders(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Header header = new Header(httpRequest, httpResponse);
        if (httpResponse.getStatusLine().getStatusCode() == "201") {
            header.setContentType("application/json;charset=utf-8");
        }
        httpResponse.setHeaders(header);
        return httpResponse;
    }

    @Override
    public String[] getAllowedMethods() {
        return allowedMethods;
    }
}

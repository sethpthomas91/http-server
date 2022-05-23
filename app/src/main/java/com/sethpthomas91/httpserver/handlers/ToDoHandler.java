package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.interfaces.ClientWrapperInterface;
import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.request.RequestHeaders;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;
import com.sethpthomas91.httpserver.utils.HttpClientWrapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class ToDoHandler implements Handler {
    private String[] allowedMethods = {"POST", "PUT", "DELETE"};
    private ClientWrapperInterface clientWrapper;

    public ToDoHandler() {
        this(new HttpClientWrapper());
    }

    public ToDoHandler(ClientWrapperInterface clientWrapper) {
        this.clientWrapper = clientWrapper;
    }

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
        if (httpRequest.hasHeaders() && hasJsonContentType(httpRequest.getHeaders())) {

            HttpResponse responseFromServer = this.clientWrapper.actAsProxy(httpRequest);
            String returnedStatusCode = String.valueOf(responseFromServer.statusCode());
            statusLine.setStatusCode(returnedStatusCode);
            statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
            statusLine.setResponseText("Resource created");
            if ((returnedStatusCode.equals("200") || returnedStatusCode.equals("404")) && httpRequest.getRequestLine().getTypeOfRequest().equals("DELETE")) {
                statusLine.setStatusCode("204");
            } else if (returnedStatusCode.equals("404") && httpRequest.getRequestLine().getTypeOfRequest().equals("DELETE")) {
                statusLine.setStatusCode("204");
            }
        } else if (httpRequest.hasHeaders() && hasXFormContentType(httpRequest.getHeaders())){
            statusLine.setStatusCode("400");
            statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
            statusLine.setResponseText("Bad request");
        } else {
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
        } else if (httpResponse.getStatusLine().getStatusCode().equals("200")) {
            body.setBodyText("{\"task\":\"an updated task\"}");
        }
        httpResponse.setBody(body);
        return httpResponse;
    }

    private HttpResponseWrapper handleHeaders(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Header header = new Header(httpRequest, httpResponse);
        if (httpResponse.getStatusLine().getStatusCode().equals("201") || httpResponse.getStatusLine().getStatusCode().equals("200")) {
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

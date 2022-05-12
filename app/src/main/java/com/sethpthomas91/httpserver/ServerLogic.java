package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.interfaces.ServerLogicInterface;
import com.sethpthomas91.httpserver.request.RequestLine;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;

import java.io.IOException;


public class ServerLogic implements ServerLogicInterface {
    Router router = new Router();
    HttpRequestInterface httpRequest;
    HttpResponseWrapper httpResponse;

    public HttpResponseWrapper processRequest(HttpRequestInterface httpRequest) throws IOException {
        if (httpRequest.getRequestLine().equals("/kitteh.jpg")) {
            return router.route(httpRequest);
        } else {
            this.httpRequest = httpRequest;
            this.httpResponse = new HttpResponseWrapper();

            StatusLine statusLine = createStatusLine(httpRequest.getRequestLine());
            httpResponse.setStatusLine(statusLine);

            Body body = createBody(httpRequest);
            httpResponse.setBody(body);

            Header header = createHeader(httpRequest, httpResponse);
            httpResponse.setHeaders(header);


            return httpResponse;
        }
    }


    private StatusLine createStatusLine(RequestLine requestLine) {
        StatusLine statusLine = new StatusLine();
        statusLine.setHttpVersion(requestLine.getHttpVersion());
        if (router.resourceExists(requestLine.getUniformResourceIdentifier())) {
            handleRequestType(statusLine, requestLine);
        } else {
            statusLine.setStatusCode("404");
            statusLine.setResponseText("Resource Does Not Exist");
        }
        return statusLine;
    }

    private StatusLine handleRequestType(StatusLine statusLine, RequestLine requestLine) {
        switch (requestLine.getTypeOfRequest()) {
            case "GET" -> createStatusLineForGetRequest(statusLine, requestLine.getUniformResourceIdentifier());
            case "OPTIONS", "HEAD", "POST" -> {
                statusLine.setStatusCode("200");
                statusLine.setResponseText("OK");
            }
        }
        return statusLine;
    }

    private StatusLine createStatusLineForGetRequest(StatusLine statusLine, String uniformResourceIdentifier) {
        switch (uniformResourceIdentifier) {
            case "/head_request": statusLine.setStatusCode("405");
            statusLine.setResponseText("Method Not Allowed");
            break;
            case "/redirect": statusLine.setStatusCode("301");
            statusLine.setResponseText("Moved Permanently");
            break;
            default: statusLine.setStatusCode("200");
            statusLine.setResponseText("OK");
        }
        return statusLine;
    }

    private Body createBody(HttpRequestInterface httpRequest) throws IOException {
        return new Body(httpRequest.getRequestLine().getUniformResourceIdentifier());
    }

    private Header createHeader(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) {
        return new Header(httpRequest, httpResponse);
    }

}

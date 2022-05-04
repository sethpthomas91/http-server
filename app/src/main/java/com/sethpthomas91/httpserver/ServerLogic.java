package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.interfaces.ServerLogicInterface;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;

import java.util.ArrayList;
import java.util.List;


public class ServerLogic implements ServerLogicInterface {
    HttpRequestInterface httpRequest;
    HttpResponseWrapper httpResponse;
    StatusLine statusLine;

    public HttpResponseWrapper processRequest(HttpRequestInterface httpRequest) {
        this.httpRequest = httpRequest;
        this.httpResponse = new HttpResponseWrapper();
        this.statusLine = new StatusLine();
        setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
        handleHeaders(httpRequest.getRequestLine().getTypeOfRequest(), httpRequest.getRequestLine().getUniformResourceIdentifier());
        handleRequestType();
        httpResponse.setStatusLine(statusLine);
        return httpResponse;
    }

    private void setHttpVersion (String httpVersion) {
        if (httpVersion.equals("HTTP/1.1")) {
            this.statusLine.setHttpVersion("HTTP/1.1");
        } else {
//            Error handling
        }
    }

    private void handleRequestType () {
        String typeOfRequest = httpRequest.getRequestLine().getTypeOfRequest();
        String uniformResourceIdentifier = httpRequest.getRequestLine().getUniformResourceIdentifier();
//        if (checkIfResourceExists(uniformResourceIdentifier)) {
//            switch(typeOfRequest) {
//                case "GET": handleGetRequest(typeOfRequest, uniformResourceIdentifier);
//                break;
//                case "OPTIONS": set200AndOKResponse();
//                break;
//                case "HEAD": set200AndOKResponse();
//                break;
//                case "POST": set200AndOKResponse();
//            }
//        }
        if (checkIfResourceExists(uniformResourceIdentifier)) {
            if (typeOfRequest.equals("GET")) {
                handleGetRequest(typeOfRequest, uniformResourceIdentifier);
            }
            else if (typeOfRequest.equals("OPTIONS")) {
                set200AndOKResponse();
            }
            else if (typeOfRequest.equals("HEAD")) {
                set200AndOKResponse();
            }
            else if (typeOfRequest.equals("POST")) {
                set200AndOKResponse();
                handlePostRequest(uniformResourceIdentifier);
            }
        } else {
            set404AndResponse();
        }
    }

    private void handlePostRequest(String uniformResourceIdentifier) {
        if (uniformResourceIdentifier.equals("/echo_body")) {
            Body body = createBody(uniformResourceIdentifier);
            String httpRequestBody = httpRequest.getBody();
            body.setBodyText(httpRequestBody);
            this.httpResponse.setBody(body);
        }
    }

    private void handleGetRequest(String typeOfRequest, String uniformResourceIdentifier) {

        if (uniformResourceIdentifier.equals("/head_request")) {
            set405AndResponse();
        }
        else if (uniformResourceIdentifier.equals("/simple_get_with_body")){
            set200AndOKResponse();
            Body body = createBody(uniformResourceIdentifier);
            this.httpResponse.setBody(body);
        }
        else if (uniformResourceIdentifier.equals("/redirect")){
            set301AndResponse();
        }
        else {
            set200AndOKResponse();
        }
    }

    private Body createBody(String uniformResourceIdentifier) {
        return new Body(uniformResourceIdentifier);
    }

    private Header createHeader(String typeOfRequest, String uniformResourceIdentifier) {
        return new Header(typeOfRequest, uniformResourceIdentifier);
    }

    private void handleHeaders(String typeOfRequest, String uniformResourceIdentifier) {
        Header header = createHeader(typeOfRequest, uniformResourceIdentifier);
        this.httpResponse.setHeaders(header);
    }

    private boolean checkIfResourceExists(String uniformResourceIdentifier) {
        List<String> resourceList = new ArrayList<>();
        resourceList.add("/");
        resourceList.add("/simple_get");
        resourceList.add("/simple_get_with_body");
        resourceList.add("/head_request");
        resourceList.add("/redirect");
        resourceList.add("/echo_body");
        resourceList.add("/method_options");
        resourceList.add("/method_options2");
        return resourceList.contains(uniformResourceIdentifier);
    }

    private void set200AndOKResponse() {
        this.statusLine.setStatusCode("200");
        this.statusLine.setResponseText("OK");
    }

    private void set301AndResponse() {
        this.statusLine.setStatusCode("301");
        this.statusLine.setResponseText("Moved Permanently");
    }

    private void set404AndResponse() {
        this.statusLine.setStatusCode("404");
        this.statusLine.setResponseText("Resource Does Not Exist");
    }

    private void set405AndResponse() {
        this.statusLine.setStatusCode("405");
        this.statusLine.setResponseText("Method Not Allowed");
    }
}

package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.interfaces.ServerLogicInterface;

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
        setHttpVersion();
        handleRequestType();
        httpResponse.setStatusLine(statusLine);
        return httpResponse;
    }

    private void setHttpVersion () {
        String httpVersion = httpRequest.getRequestLine().getHttpVersion();
        if (httpVersion.equals("HTTP/1.1")) {
            this.statusLine.setHttpVersion("HTTP/1.1");
        } else {
//            Error handling
        }
    }

    private void handleRequestType () {
        String typeOfRequest = httpRequest.getRequestLine().getTypeOfRequest();
        String uniformResourceIdentifier = httpRequest.getRequestLine().getUniformResourceIdentifier();
        if (typeOfRequest.equals("GET") && checkIfResourceExists(uniformResourceIdentifier)) {
            if (uniformResourceIdentifier.equals("/head_request")) {
                set405AndResponse();
                Header header = new Header(typeOfRequest, uniformResourceIdentifier);
                this.httpResponse.setHeaders(header);
            }
            else if (uniformResourceIdentifier.equals("/simple_get_with_body")){
                set200AndOKResponse();
                Body body = new Body(uniformResourceIdentifier);
                this.httpResponse.setBody(body);
                Header header = new Header(typeOfRequest, uniformResourceIdentifier);
                this.httpResponse.setHeaders(header);
            }
            else if (uniformResourceIdentifier.equals("/redirect")){
                set301AndResponse();
                Header header = new Header(typeOfRequest, uniformResourceIdentifier);
                this.httpResponse.setHeaders(header);
            }
            else {
                set200AndOKResponse();
            }
        }
        else if (typeOfRequest.equals("OPTIONS")) {
            set200AndOKResponse();
            Header header = new Header(typeOfRequest, uniformResourceIdentifier);
            this.httpResponse.setHeaders(header);
        }
        else if (typeOfRequest.equals("HEAD") && checkIfResourceExists(uniformResourceIdentifier)) {
            set200AndOKResponse();
        }
        else if (typeOfRequest.equals("POST") && checkIfResourceExists(uniformResourceIdentifier)) {
            set200AndOKResponse();
            Body body = new Body(uniformResourceIdentifier);
            if (uniformResourceIdentifier.equals("/echo_body")) {
                body.setBodyText(httpRequest.getBody());
            }
            this.httpResponse.setBody(body);
        }
        else {
            set404AndResponse();
        }
    }

    private boolean checkIfResourceExists(String uniformResourceIdentifier) {
        List<String> resourceList = new ArrayList<>();
        resourceList.add("/");
        resourceList.add("/simple_get");
        resourceList.add("/simple_get_with_body");
        resourceList.add("/head_request");
        resourceList.add("/redirect");
        resourceList.add("/echo_body");
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

//    private void processUniformResourceIdentifier() {
//        String publicDirectory = testing ? "Public" : "app/Public";
////        way to look from root
//        Path publicDirFile = Paths.get(publicDirectory + uniformResourceIdentifier);
//        System.out.println(String.format("PATH: [%s] equals [%s] ?", publicDirFile, uniformResourceIdentifier));
//        System.out.println(Files.exists(publicDirFile));
//        System.out.println(publicDirFile.toAbsolutePath());
//        if (Files.exists(publicDirFile)) {
//            System.out.println(String.format("File at %s does exist.", publicDirFile));
//            statusCode = "200";
//            reasonPhrase = "OK";
//        }
//    }
}

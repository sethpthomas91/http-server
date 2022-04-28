package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.interfaces.ServerLogicInterface;

public class ServerLogic implements ServerLogicInterface {
    HttpRequestInterface httpRequest;
    HttpResponseWrapper httpResponse;

    String statusCode200 = "200";
    String responseText200 = "OK";


    public HttpResponseWrapper processRequest(HttpRequestInterface httpRequest) {
        this.httpRequest = httpRequest;
        this.httpResponse = new HttpResponseWrapper();
        setHttpVersion();
        handleRequestType();
        return httpResponse;
    }

    private void setHttpVersion () {
        if (httpRequest.getHttpVersion().equals("HTTP/1.1")) {
            this.httpResponse.setHttpVersion("HTTP/1.1");
        } else {
//            Error handling
        }
    }

    private void handleRequestType () {
        String typeOfRequest = httpRequest.getTypeOfRequest();
        String uniformResourceIdentifier = httpRequest.getUniformResourceIdentifier();
        if (typeOfRequest.equals("GET")) {
            this.httpResponse.setStatusCode(statusCode200);
            this.httpResponse.setResponseText(responseText200);
        }
        else if (typeOfRequest.equals("OPTIONS")) {
            this.httpResponse.setStatusCode(statusCode200);
            this.httpResponse.setResponseText(responseText200);
            Header header = new Header(typeOfRequest, uniformResourceIdentifier);
            this.httpResponse.setHeaders(header);
        }
        else {
//            Handle errors here
        }
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

package com.sethpthomas91.httpserver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerLogic implements ServerLogicInterface{
    String httpResponse;
    String statusLine;
    String statusCode;
    String reasonPhrase = "OK";
//    String body;

    String CRLF = "\r\n";
    String SPACE = " ";

    String requestLine;
    String typeOfRequest;
    String uniformResourceIdentifier;
    String httpVersion;
//    String requestHeaders;
//    String requestBody;

    public String processString(String request) {
        disassembleRequest(request);
        splitRequestLine(requestLine);
        processUniformResourceIdentifier();
        assembleStatusLine();
        buildHttpResponse();
        return this.httpResponse;
    }

    private void disassembleRequest(String request) {
        String[] splitRequest = request.split(CRLF);
        requestLine = splitRequest[0];
//        requestHeaders = splitRequest[1];
//        requestBody = splitRequest[2];
    }

    private void splitRequestLine(String requestLine) {
        String[] splitRequestLine = requestLine.split(SPACE);
        this.typeOfRequest = splitRequestLine[0];
        this.uniformResourceIdentifier = splitRequestLine[1];
        this.httpVersion = splitRequestLine[2];
    }

    private void assembleStatusLine() {
        this.statusLine =  httpVersion + SPACE + statusCode + SPACE + reasonPhrase;
    }

    private void buildHttpResponse() {
        this.httpResponse = statusLine + CRLF;
    }

    private void processUniformResourceIdentifier() {
        Path publicDirFile = Paths.get("public" + uniformResourceIdentifier);
        System.out.println(publicDirFile);

        if (Files.exists(publicDirFile)) {
            System.out.println(String.format("File at %s does exist.", publicDirFile));
            statusCode = "200";
        }


    }

}

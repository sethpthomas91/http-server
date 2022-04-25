package com.sethpthomas91.httpserver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerLogic implements ServerLogicInterface{
    String httpResponse;
    String statusLine;
    String statusCode = "404";
    String reasonPhrase = "Resource not found";
    boolean testing = false;
<<<<<<< HEAD
    String responseHeaders = "";
=======
//    String body;
>>>>>>> 0fcd0182064db79ce1d9b5f563a490542222feb7

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
        System.out.println(requestLine);
        splitRequestLine(requestLine);
        System.out.println(typeOfRequest);
        System.out.println(uniformResourceIdentifier);
        System.out.println(httpVersion);
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
<<<<<<< HEAD
//              + responseHeaders + CRLF;
=======
>>>>>>> 0fcd0182064db79ce1d9b5f563a490542222feb7
    }

    public void setToTestingObject() {
        testing = true;
    }

    private void processUniformResourceIdentifier() {
        String publicDirectory = testing ? "Public" : "app/Public";
<<<<<<< HEAD
//        way to look from root

=======
>>>>>>> 0fcd0182064db79ce1d9b5f563a490542222feb7
        Path publicDirFile = Paths.get(publicDirectory + uniformResourceIdentifier);
        System.out.println(String.format("PATH: [%s] equals [%s] ?", publicDirFile, uniformResourceIdentifier));
        System.out.println(Files.exists(publicDirFile));
        System.out.println(publicDirFile.toAbsolutePath());
        if (Files.exists(publicDirFile)) {
            System.out.println(String.format("File at %s does exist.", publicDirFile));
            statusCode = "200";
            reasonPhrase = "OK";
<<<<<<< HEAD
//            if (uniformResourceIdentifier.equals("/method_options")) {
//                this.responseHeaders = "Allowed Headers : GET, HEAD, OPTIONS";
//            }
        }
=======
        }


>>>>>>> 0fcd0182064db79ce1d9b5f563a490542222feb7
    }

}

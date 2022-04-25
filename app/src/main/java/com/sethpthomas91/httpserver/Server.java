package com.sethpthomas91.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Server {
    ServerSocketWrapperInterface serverSocketWrapper;
    ServerLogicInterface serverLogic;

    public Server(ServerSocketWrapperInterface serverSocketWrapper, ServerLogicInterface serverLogic) {
        this.serverSocketWrapper = serverSocketWrapper;
        this.serverLogic = serverLogic;
    }

    public void start() throws IOException {
        try {
        serverSocketWrapper.listen();
        } catch (IOException error) {
            System.out.println("[Error creating server socket]");
        }

        while (true) {
            try {

                serverSocketWrapper.handleConnectedClient();
                String incomingRequest = serverSocketWrapper.incomingRequest();
                HttpRequestWrapper httpRequest = new HttpRequestWrapper(incomingRequest);
                HttpResponseWrapper httpResponse = serverLogic.processRequest(httpRequest);
                String outgoingResponse = httpResponse.stringifyHttpResponse();
                serverSocketWrapper.sendResponse(outgoingResponse);
                serverSocketWrapper.disconnectFromClient();

            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }
}

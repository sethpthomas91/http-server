package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.interfaces.ServerLogicInterface;
import com.sethpthomas91.httpserver.interfaces.ServerSocketWrapperInterface;
import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;

import java.io.IOException;

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
                byte[] outgoingResponseBytes = httpResponse.getBytes();
                System.out.println(outgoingResponseBytes);
                serverSocketWrapper.sendResponse(outgoingResponseBytes);
                serverSocketWrapper.disconnectFromClient();

            } catch (Exception error) {
                error.printStackTrace();
            }
        }
    }
}

package com.sethpthomas91.httpserver;

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
           String incomingRequest = serverSocketWrapper.incomingRequest();
           HttpRequestWrapper httpRequest = new HttpRequestWrapper(incomingRequest);
           HttpResponseWrapper httpResponse = serverLogic.processRequest(httpRequest);
           serverSocketWrapper.sendResponse(httpResponse.stringifyHttpResponse());
        } catch (Exception e) {
            serverSocketWrapper.disconnect();
            e.printStackTrace();
            System.out.println(String.format("[Server at port: %s encountered an error]",serverSocketWrapper.getPort()));
        }
    }
}

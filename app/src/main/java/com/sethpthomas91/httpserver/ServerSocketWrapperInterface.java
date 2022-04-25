package com.sethpthomas91.httpserver;

import java.io.IOException;

public interface ServerSocketWrapperInterface {
    void createServerSocket(int port) throws IOException;
    void listen() throws IOException;
    int getPort();
    void disconnect() throws IOException;
    String incomingRequest() throws IOException;
    void sendResponse(String stringifyHttpResponse);
    boolean getListeningStatus();
}

package com.sethpthomas91.httpserver;

import java.io.IOException;
import java.io.InputStream;

public interface ServerSocketWrapperInterface {
    void createServerSocket(int port) throws IOException;
    void listen() throws IOException;
    int getPort();
    String incomingRequest() throws IOException;
    void sendResponse(String stringifyHttpResponse);
    boolean getListeningStatus();
    void disconnectServerSocket() throws IOException;
    void disconnectFromClient() throws IOException;
    boolean isConnectedToClient();
    void handleConnectedClient() throws IOException;
}

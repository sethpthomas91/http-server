package com.sethpthomas91.httpserver.interfaces;

import java.io.IOException;

public interface ServerSocketWrapperInterface {
    void createServerSocket(int port) throws IOException;
    void listen() throws IOException;
    int getPort();
    String incomingRequest() throws IOException;
    void sendResponse(byte[] stringifyHttpResponse) throws IOException;
    boolean getListeningStatus();
    void disconnectServerSocket() throws IOException;
    void disconnectFromClient() throws IOException;
    boolean isConnectedToClient();
    void handleConnectedClient() throws IOException;
}

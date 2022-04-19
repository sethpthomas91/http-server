package com.sethpthomas91.httpserver;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketWrapper implements ServerSocketWrapperInterface{
    ServerSocket serverSocket = null;

    @Override
    public void createServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
}

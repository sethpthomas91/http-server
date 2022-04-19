package com.sethpthomas91.httpserver;

import java.io.IOException;
import java.net.ServerSocket;

public class MockServerSocket implements ServerSocketWrapperInterface{
    ServerSocket serverSocket = null;
    boolean createSocketCalled = false;

    public boolean createSocketWasCalled() {
        return createSocketCalled;
    }

    @Override
    public void createServerSocket(int port) throws IOException {
        createSocketCalled = true;
    }

}

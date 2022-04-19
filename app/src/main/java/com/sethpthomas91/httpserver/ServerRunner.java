package com.sethpthomas91.httpserver;

import java.io.IOException;

public class ServerRunner {
    int port = 5000;
    ServerSocketWrapperInterface serverSocketWrapper = null;

    public static void main(String[] args) throws IOException {
        ServerRunner serverRunner = new ServerRunner();
        serverRunner.startServer();
    }

    public int getPort() {
    return port;
    }

    public void setPort(int customPort) {
        this.port = customPort;
    }

    public void startServer() throws IOException {
        serverSocketWrapper.createServerSocket(this.port);
    }

    public void setServerSocketWrapper(ServerSocketWrapperInterface injectedServerSocketWrapper) {
        this.serverSocketWrapper = injectedServerSocketWrapper;
    }
}

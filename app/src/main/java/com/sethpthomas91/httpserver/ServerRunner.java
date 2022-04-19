package com.sethpthomas91.httpserver;

import java.io.IOException;

public class ServerRunner {
    int port = 5000;
    ServerSocketWrapperInterface serverSocketWrapper = null;

    public static void main(String[] args) throws IOException {
        int newPort = 5100;
        ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(newPort);
        ServerRunner serverRunner = new ServerRunner();
        serverRunner.setServerSocketWrapper(serverSocketWrapper);
        serverRunner.startServer();
    }

    public int getPort() {
    return port;
    }

    public void setPort(int customPort) {
        this.port = customPort;
    }

    public void startServer() throws IOException {
        try {
            serverSocketWrapper.listen();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("[Server at port: %s encountered an error]",this.port));
        }
    }

    public void setServerSocketWrapper(ServerSocketWrapperInterface injectedServerSocketWrapper) {
        this.serverSocketWrapper = injectedServerSocketWrapper;
    }
}

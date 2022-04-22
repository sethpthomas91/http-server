package com.sethpthomas91.httpserver;

import java.io.IOException;

public class ServerRunner {
    int port;
    ServerSocketWrapperInterface serverSocketWrapper = null;

    public static void main(String[] args) throws IOException {
        ServerRunner serverRunner = new ServerRunner();
        ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(serverRunner.getPort());
        serverRunner.setServerSocketWrapper(serverSocketWrapper);
        serverRunner.startServer();
    }

    public ServerRunner() {
        this(5000);
    }

    ServerRunner(int newPort) {
        this.port = newPort;
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

    public void setServerSocketWrapper(ServerSocketWrapperInterface serverSocketWrapper) {
        this.serverSocketWrapper = serverSocketWrapper;
    }
}

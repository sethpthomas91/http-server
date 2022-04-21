package com.sethpthomas91.httpserver;

import java.io.IOException;

public class ServerRunner {
    int port;
    ServerSocketWrapperInterface serverSocketWrapper;
    ServerLogicInterface serverLogic;
    Server server;

    public static void main(String[] args) throws IOException {
        ServerRunner serverRunner = new ServerRunner();
        ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(serverRunner.getPort());
        serverRunner.setServerSocketWrapper(serverSocketWrapper);
        serverRunner.createServer();
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

    public void createServer() {
        this.server = new Server(this.serverSocketWrapper, this.serverLogic);
    }

    public void startServer() {
        server.start();
    }

    public void setServerSocketWrapper(ServerSocketWrapperInterface serverSocketWrapper) {
        this.serverSocketWrapper = serverSocketWrapper;
    }

    public void setServerLogic(ServerLogicInterface injectedServerLogic) {
        this.serverLogic = injectedServerLogic;
    }
}

package com.sethpthomas91.httpserver;

import java.io.IOException;

public class ServerRunner {
<<<<<<< HEAD
    private int port;
    private ServerSocketWrapperInterface serverSocketWrapper;
    private ServerLogicInterface serverLogic;
    private Server server;
=======
    int port;
    ServerSocketWrapperInterface serverSocketWrapper;
    ServerLogicInterface serverLogic;
    Server server;
>>>>>>> 0fcd0182064db79ce1d9b5f563a490542222feb7

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
<<<<<<< HEAD
=======
    }

    public void startServer() {
        server.start();
>>>>>>> 0fcd0182064db79ce1d9b5f563a490542222feb7
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

    public void setServerLogic(ServerLogicInterface injectedServerLogic) {
        this.serverLogic = injectedServerLogic;
    }
}

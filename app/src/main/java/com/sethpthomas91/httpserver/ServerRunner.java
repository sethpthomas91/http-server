package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.interfaces.ServerLogicInterface;
import com.sethpthomas91.httpserver.interfaces.ServerSocketWrapperInterface;

import java.io.IOException;

public class ServerRunner {
    private int port;
    private ServerSocketWrapperInterface serverSocketWrapper;
    private ServerLogicInterface serverLogic;
    private Server server;

    public static void main(String[] args) throws IOException {
        ServerRunner serverRunner = new ServerRunner();
        ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(serverRunner.getPort());
        ServerLogic serverLogic = new ServerLogic();
        serverRunner.setServerSocketWrapper(serverSocketWrapper);
        serverRunner.setServerLogic(serverLogic);
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

    public void startServer () throws IOException {
            server.start();
    }

    public void setServerSocketWrapper (ServerSocketWrapperInterface serverSocketWrapper){
            this.serverSocketWrapper = serverSocketWrapper;
    }

    public void setServerLogic (ServerLogicInterface injectedServerLogic){
            this.serverLogic = injectedServerLogic;
    }
}


package com.sethpthomas91.httpserver;

public class Server {
    ServerSocketWrapperInterface serverSocketWrapper;
    ServerLogicInterface serverLogic;

    public Server(ServerSocketWrapperInterface serverSocketWrapper, ServerLogicInterface serverLogic) {
        this.serverSocketWrapper = serverSocketWrapper;
        this.serverLogic = serverLogic;
    }

    public void start(){
        try {
            serverSocketWrapper.listen();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("[Server at port: %s encountered an error]",serverSocketWrapper.getPort()));
        }
    }

}

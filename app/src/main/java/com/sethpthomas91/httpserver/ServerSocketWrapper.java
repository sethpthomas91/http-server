package com.sethpthomas91.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements ServerSocketWrapperInterface{
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    BufferedReader clientReader = null;
    PrintWriter clientWriter = null;
    boolean isListening = false;
    int port = 0;

    public ServerSocketWrapper(int newPort){
        port = newPort;
    }

    private void createWriter() throws IOException {
        clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("[Client Writer Created]");
    }

    private void createReader() throws IOException {
        clientReader = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("[Client Reader Created]");
    }

    private void createClientSocket() {
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[Failed to Create Client]");
        }
    }

    @Override
    public void createServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println(String.format("[LISTENING ON PORT: %s]", port));
    }

    @Override
    public void listen() throws IOException {
        isListening = true;
        createServerSocket(this.port);
        while (isListening) {
            createClientSocket();
            createReader();
            createWriter();
        }


    }
}

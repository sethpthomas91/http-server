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
    int port;

    public ServerSocketWrapper(int newPort){
        this.port = newPort;
    }

    public int getPort(){
        return port;
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
            System.out.println("[Connected to Client]");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[Failed to connect to Client]");
        }
    }

    @Override
    public void createServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println(String.format("[LISTENING ON PORT: %s]", port));
    }

    @Override
    public void listen() throws IOException {
        this.isListening = true;
        createServerSocket(this.port);
        try {
                createClientSocket();
                createReader();
                createWriter();
        } catch (IOException error) {
            this.isListening = false;
            disconnect();
        }
    }

    public void disconnect() throws IOException {
        System.out.println(String.format("[Client Socket at %s Disconnected]", clientSocket.getPort()));
        clientSocket.close();
    }

    public String incomingRequest() throws IOException {
        return clientReader.readLine();
    }

    public void sendResponse(String newMessage) {
        clientWriter.println(newMessage);
    }

    public boolean getListeningStatus() {
        return this.isListening;
    }
}

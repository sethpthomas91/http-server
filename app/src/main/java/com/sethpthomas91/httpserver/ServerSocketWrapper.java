package com.sethpthomas91.httpserver;

import com.google.common.primitives.Bytes;
import com.sethpthomas91.httpserver.interfaces.ServerSocketWrapperInterface;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class ServerSocketWrapper implements ServerSocketWrapperInterface {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    BufferedReader clientReader = null;
    PrintWriter clientWriter = null;
    boolean isListening = false;
    boolean connectedToClient = false;
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
//        BufferedImage clientImageReader = new BufferedImage(new ByteArrayInputStream())
        clientReader = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("[Client Reader Created]");
    }

    private void createClientSocket() {
        try {
            clientSocket = serverSocket.accept();
            connectedToClient = true;
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
        createServerSocket(this.port);
    }

    public void handleConnectedClient() throws IOException {
        createClientSocket();
        createReader();
        createWriter();
    }

    public void disconnectFromClient() throws IOException {
        System.out.println(String.format("[Client Socket at %s Disconnected]", clientSocket.getPort()));
        clientSocket.close();
        connectedToClient = false;
    }

    @Override
    public boolean isConnectedToClient() {
        return connectedToClient;
    }

    public void disconnectServerSocket() throws IOException {
        System.out.println("[Shutting down server]");
        serverSocket.close();
    }

    public String incomingRequest() throws IOException {
        StringBuilder result = new StringBuilder();
        do {
            result.append((char) clientSocket.getInputStream().read());
        } while (clientSocket.getInputStream().available() > 0);
        return result.toString();
    }

    public void sendResponse(byte[] responseBytes) throws IOException {
        OutputStream toClientOutputStream = clientSocket.getOutputStream();
        toClientOutputStream.write(responseBytes);
        toClientOutputStream.flush();
        toClientOutputStream.close();
    }

    public boolean getListeningStatus() {
        return this.isListening;
    }
}

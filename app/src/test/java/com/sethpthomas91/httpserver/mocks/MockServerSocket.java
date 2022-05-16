package com.sethpthomas91.httpserver.mocks;

import com.sethpthomas91.httpserver.interfaces.ServerSocketWrapperInterface;

import java.io.IOException;

public class MockServerSocket implements ServerSocketWrapperInterface {
    boolean createSocketCalled = false;
    boolean createReaderCalled = false;
    boolean createWriterCalled = false;
    int port;


    @Override
    public void createServerSocket(int port) throws IOException {
        createSocketCalled = true;
    }

    @Override
    public void listen() throws IOException {
        createServerSocket(this.port);
        createReader();
        createWriter();
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String incomingRequest() throws IOException {
        return null;
    }

    @Override
    public void sendResponse(byte[] stringifyHttpResponse) {

    }

    @Override
    public boolean getListeningStatus() {
        return false;
    }

    @Override
    public void disconnectServerSocket() throws IOException {

    }

    @Override
    public void disconnectFromClient() throws IOException {

    }

    @Override
    public boolean isConnectedToClient() {
        return false;
    }

    @Override
    public void handleConnectedClient() throws IOException {

    }

    private void createWriter() {
        createWriterCalled = true;
    }

    private void createReader() {
        createReaderCalled = true;
    }

}

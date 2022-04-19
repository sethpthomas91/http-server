package com.sethpthomas91.httpserver;

import java.io.IOException;
import java.net.ServerSocket;

public class MockServerSocket implements ServerSocketWrapperInterface{
    boolean createSocketCalled = false;
    boolean createReaderCalled = false;
    boolean createWriterCalled = false;
    int port = 0;

    public MockServerSocket(int newPort) {
        port = newPort;
    }

    public boolean createSocketWasCalled() {
        return createSocketCalled;
    }

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

    private void createWriter() {
        createWriterCalled = true;
    }

    private void createReader() {
        createReaderCalled = true;
    }

    public boolean createReaderWasCalled() {
        return createReaderCalled;
    }

    public boolean createWriterWasCalled() {
        return createWriterCalled;
    }
}

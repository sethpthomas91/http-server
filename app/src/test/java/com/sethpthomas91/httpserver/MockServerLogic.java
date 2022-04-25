package com.sethpthomas91.httpserver;

public class MockServerLogic implements ServerLogicInterface{
    boolean receivedMessage = false;

    public boolean receivedMessageWasCalled() {
        return receivedMessage;
    }

    public void processIncomingMessage() {
        receivedMessage = true;
    }

    @Override
    public HttpResponseWrapper processRequest(HttpRequestWrapper httpRequest) {
        return null;
    }
}

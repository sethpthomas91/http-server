package com.sethpthomas91.httpserver.mocks;

import com.sethpthomas91.httpserver.HttpRequestWrapper;
import com.sethpthomas91.httpserver.HttpResponseWrapper;
import com.sethpthomas91.httpserver.interfaces.ServerLogicInterface;

public class MockServerLogic implements ServerLogicInterface {
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

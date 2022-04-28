package com.sethpthomas91.httpserver.mocks;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;

public class MockHttpRequest implements HttpRequestInterface {

    String typeOfRequest;
    String uniformResourceIdentifier;
    String httpVersion;

    public void setTypeOfRequest(String typeOfRequest) {
        this.typeOfRequest = typeOfRequest;
    }

    @Override
    public String getTypeOfRequest() {
        return null;
    }

    public void setUniformResourceIdentifier(String uniformResourceIdentifier) {
        this.uniformResourceIdentifier = uniformResourceIdentifier;
    }

    @Override
    public String getUniformResourceIdentifier() {
        return null;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    @Override
    public String getHttpVersion() {
        return null;
    }
}

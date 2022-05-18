package com.sethpthomas91.httpserver.mocks;

import com.sethpthomas91.httpserver.request.RequestHeaders;
import com.sethpthomas91.httpserver.request.RequestLine;
import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;

public class MockHttpRequest implements HttpRequestInterface {

    String typeOfRequest;
    String uniformResourceIdentifier;
    String httpVersion;

    public void setTypeOfRequest(String typeOfRequest) {
        this.typeOfRequest = typeOfRequest;
    }

    public void setUniformResourceIdentifier(String uniformResourceIdentifier) {
        this.uniformResourceIdentifier = uniformResourceIdentifier;
    }


    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    @Override
    public RequestLine getRequestLine() {
        return null;
    }

    @Override
    public String getBody() {
        return null;
    }

    @Override
    public RequestHeaders getHeaders() {
        return null;
    }

    @Override
    public boolean hasHeaders() {
        return false;
    }

}

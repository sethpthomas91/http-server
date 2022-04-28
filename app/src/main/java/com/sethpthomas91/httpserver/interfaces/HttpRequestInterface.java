package com.sethpthomas91.httpserver.interfaces;

public interface HttpRequestInterface {
    String getTypeOfRequest();
    String getUniformResourceIdentifier();
    String getHttpVersion();

}

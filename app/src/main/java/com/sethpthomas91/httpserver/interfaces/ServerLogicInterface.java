package com.sethpthomas91.httpserver.interfaces;

import com.sethpthomas91.httpserver.response.HttpResponseWrapper;

import java.io.IOException;

public interface ServerLogicInterface {

    HttpResponseWrapper processRequest(HttpRequestInterface httpRequest) throws IOException;
}

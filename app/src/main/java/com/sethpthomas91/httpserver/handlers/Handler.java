package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;

import java.io.IOException;

public interface Handler {
    HttpResponseWrapper handle(HttpRequestInterface httpRequest) throws IOException;
    String[] getAllowedMethods();
}

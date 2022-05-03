package com.sethpthomas91.httpserver.interfaces;

import com.sethpthomas91.httpserver.response.HttpResponseWrapper;

public interface ServerLogicInterface {

    HttpResponseWrapper processRequest(HttpRequestInterface httpRequest);
}

package com.sethpthomas91.httpserver;

public interface ServerLogicInterface {

    HttpResponseWrapper processRequest(HttpRequestWrapper httpRequest);
}

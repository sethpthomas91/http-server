package com.sethpthomas91.httpserver.interfaces;

import com.sethpthomas91.httpserver.HttpRequestWrapper;
import com.sethpthomas91.httpserver.HttpResponseWrapper;

public interface ServerLogicInterface {

    HttpResponseWrapper processRequest(HttpRequestWrapper httpRequest);
}

package com.sethpthomas91.httpserver.interfaces;

import com.sethpthomas91.httpserver.request.RequestHeaders;
import com.sethpthomas91.httpserver.request.RequestLine;

public interface HttpRequestInterface {
    RequestLine getRequestLine();
    String getBody();
    RequestHeaders getHeaders();
    boolean hasHeaders();
}

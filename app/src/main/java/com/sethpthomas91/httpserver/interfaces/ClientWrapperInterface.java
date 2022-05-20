package com.sethpthomas91.httpserver.interfaces;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public interface ClientWrapperInterface {
    HttpResponse<String> actAsProxy(HttpRequestInterface incomingRequest) throws URISyntaxException, IOException, InterruptedException;
}

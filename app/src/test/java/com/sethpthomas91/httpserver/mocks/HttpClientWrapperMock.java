package com.sethpthomas91.httpserver.mocks;

import com.sethpthomas91.httpserver.interfaces.ClientWrapperInterface;
import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.utils.HttpClientWrapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class HttpClientWrapperMock implements ClientWrapperInterface {

    @Override
    public HttpResponse<String> actAsProxy(HttpRequestInterface incomingRequest) throws URISyntaxException, IOException, InterruptedException {
        return null;
    }
}

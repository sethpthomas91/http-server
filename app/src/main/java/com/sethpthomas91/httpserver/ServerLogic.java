package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.interfaces.ServerLogicInterface;
import com.sethpthomas91.httpserver.request.RequestLine;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;

import java.io.IOException;
import java.net.URISyntaxException;


public class ServerLogic implements ServerLogicInterface {
    Router router = new Router();

    public HttpResponseWrapper processRequest(HttpRequestInterface httpRequest) throws IOException, URISyntaxException, InterruptedException {
        if (router.availableRoute(httpRequest.getRequestLine().getUniformResourceIdentifier())) {
            return router.route(httpRequest);
        } else {
            return router.routeNoResource(httpRequest);
        }
    }
}

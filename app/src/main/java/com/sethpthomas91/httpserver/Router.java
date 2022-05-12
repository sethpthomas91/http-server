package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.handlers.DefaultHandler;
import com.sethpthomas91.httpserver.handlers.Handler;
import com.sethpthomas91.httpserver.handlers.HealthCheckHandler;
import com.sethpthomas91.httpserver.handlers.ImageHandler;
import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.request.HttpRequestWrapper;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Router {
    public Map<String, String[]> resources;
    private Map<String, Handler> resourcesWithHandlers;

    public Router() {
        this.resources = createResources();
        this.resourcesWithHandlers = createResourcesWithHandlers();
    }

    public boolean resourceExists(String uniformResourceIdentifier) {
        return resources.containsKey(uniformResourceIdentifier);
    }

    private Map<String, String[]> createResources() {
        Map<String, String[]> resources = new HashMap<>();
        resources.put("/", new String[]{"GET"});
        resources.put("/simple_get", new String[]{"GET", "HEAD"});
        resources.put("/simple_get_with_body", new String[]{"GET"});
        resources.put("/head_request", new String[]{"GET", "HEAD"});
        resources.put("/redirect", new String[]{"GET"});
        resources.put("/echo_body", new String[]{"POST"});
        resources.put("/method_options", new String[]{"OPTIONS"});
        resources.put("/method_options2", new String[]{"OPTIONS"});
        resources.put("/text_response", new String[]{"GET"});
        resources.put("/html_response", new String[]{"GET"});
        resources.put("/json_response", new String[]{"GET"});
        resources.put("/xml_response", new String[]{"GET"});
        resources.put("/health-check.html", new String[]{"GET"});
        resources.put("/kitteh.jpg", new String[]{"GET"});
        return resources;
    }

    private Map<String, Handler> createResourcesWithHandlers() {
        Map<String, Handler> resources = new HashMap<>();
        resources.put("/", new DefaultHandler());
        resources.put("/simple_get", new DefaultHandler());
        resources.put("/simple_get_with_body", new DefaultHandler());
        resources.put("/health-check.html", new HealthCheckHandler());
        resources.put("/kitteh.jpg", new ImageHandler());
        return resources;
    }

    public boolean methodAllowed(String typeOfRequest, String uniformResourceIdentifier) {
        String[] resourceMethods = resources.get(uniformResourceIdentifier);
        return Arrays.stream(resourceMethods).toList().contains(typeOfRequest);
    }

    public HttpResponseWrapper route(HttpRequestInterface httpRequest) throws IOException {
        Handler imageHandler = resourcesWithHandlers.get(httpRequest.getRequestLine().getUniformResourceIdentifier());
        return imageHandler.handle(httpRequest);
    }

    public boolean availableRoute(String uri) {
        return resourcesWithHandlers.containsKey(uri);
    }
}

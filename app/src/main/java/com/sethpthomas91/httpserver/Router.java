package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.handlers.*;
import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Router {
    public Map<String, String[]> resources;
    private final Map<String, Handler> resourcesWithHandlers;

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
        resources.put("/head_request", new String[]{"HEAD", "OPTIONS"});
        resources.put("/redirect", new String[]{"GET"});
        resources.put("/echo_body", new String[]{"POST"});
        resources.put("/method_options", new String[]{"GET", "HEAD", "OPTIONS"});
        resources.put("/method_options2", new String[]{"GET", "HEAD", "OPTIONS", "PUT", "POST"});
        resources.put("/text_response", new String[]{"GET"});
        resources.put("/html_response", new String[]{"GET"});
        resources.put("/json_response", new String[]{"GET"});
        resources.put("/xml_response", new String[]{"GET"});
        resources.put("/health-check.html", new String[]{"GET"});
        resources.put("/kitteh.jpg", new String[]{"GET"});
        resources.put("/doggo.png", new String[]{"GET"});
        resources.put("/kisses.gif", new String[]{"GET"});
        return resources;
    }

    private Map<String, Handler> createResourcesWithHandlers() {
        Map<String, Handler> resources = new HashMap<>();
        resources.put("/", new DefaultHandler());
        resources.put("/simple_get", new DefaultHandler());
        resources.put("/simple_get_with_body", new DefaultHandler());
        resources.put("/head_request", new DefaultHandler());
        resources.put("/redirect", new DefaultHandler());
        resources.put("/echo_body", new DefaultHandler());
        resources.put("/method_options", new DefaultHandler());
        resources.put("/method_options2", new DefaultHandler());
        resources.put("/text_response", new DefaultHandler());
        resources.put("/html_response", new HtmlHandler());
        resources.put("/json_response", new JsonHandler());
        resources.put("/xml_response", new XmlHandler());
        resources.put("/health-check.html", new HealthCheckHandler());
        resources.put("/kitteh.jpg", new ImageHandler());
        resources.put("/doggo.png", new ImageHandler());
        resources.put("/kisses.gif", new ImageHandler());
        return resources;
    }

    public boolean methodAllowed(String typeOfRequest, String uniformResourceIdentifier) {
        String[] resourceMethods = resources.get(uniformResourceIdentifier);
        return Arrays.asList(resourceMethods).contains(typeOfRequest);
    }

    public String getAllowedMethodsForUri(String uniformResourceIdentifier) {
        String[] allowedMethods = resources.get(uniformResourceIdentifier);
        StringBuilder allowedMethodsFormatted = new StringBuilder();
        for (int i = 0; i <= allowedMethods.length - 1; i++) {
            allowedMethodsFormatted.append(allowedMethods[i]);
            if (i != allowedMethods.length - 1) {
                allowedMethodsFormatted.append(", ");
            }
        }
        return allowedMethodsFormatted.toString();
    }

    public HttpResponseWrapper route(HttpRequestInterface httpRequest) throws IOException {
        if (methodAllowed(httpRequest.getRequestLine().getTypeOfRequest(), httpRequest.getRequestLine().getUniformResourceIdentifier())) {
            Handler handler = resourcesWithHandlers.get(httpRequest.getRequestLine().getUniformResourceIdentifier());
            return handler.handle(httpRequest);
        } else {
            Handler handler = new MethodNotAllowedHandler();
            return handler.handle(httpRequest);
        }
    }

    public HttpResponseWrapper routeNoResource(HttpRequestInterface httpRequest) throws IOException {
        NoResourceHandler handler = new NoResourceHandler();
        return handler.handle(httpRequest);
    }

    public boolean availableRoute(String uri) {
        return resourcesWithHandlers.containsKey(uri);
    }
}

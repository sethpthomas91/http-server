package com.sethpthomas91.httpserver;

import com.sethpthomas91.httpserver.handlers.*;
import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Router {
    private final Map<String, Handler> resourcesWithHandlers;

    public Router() {
        this.resourcesWithHandlers = createResourcesWithHandlers();
    }

    public boolean resourceExists(String uniformResourceIdentifier) {
        return resourcesWithHandlers.containsKey(uniformResourceIdentifier);
    }

    private Map<String, Handler> createResourcesWithHandlers() {
        Map<String, Handler> resources = new HashMap<>();
        resources.put("/", new DefaultHandler());
        resources.put("/simple_get", new DefaultHandler());
        resources.put("/simple_get_with_body", new DefaultHandler());
        resources.put("/head_request", new HeadRequestHandler());
        resources.put("/redirect", new DefaultHandler());
        resources.put("/echo_body", new EchoHandler());
        resources.put("/method_options", new OptionsHandler1());
        resources.put("/method_options2", new OptionsHandler2());
        resources.put("/text_response", new TextHandler());
        resources.put("/html_response", new HtmlHandler());
        resources.put("/json_response", new JsonHandler());
        resources.put("/xml_response", new XmlHandler());
        resources.put("/health-check.html", new HealthCheckHandler());
        resources.put("/kitteh.jpg", new ImageHandler());
        resources.put("/doggo.png", new ImageHandler());
        resources.put("/kisses.gif", new ImageHandler());
        resources.put("/todo", new ToDoHandler());
        return resources;
    }

    public boolean methodAllowed(String typeOfRequest, String uniformResourceIdentifier) {
        Handler handler = resourcesWithHandlers.get(uniformResourceIdentifier);
        String[] resourceMethods = handler.getAllowedMethods();
        return Arrays.asList(resourceMethods).contains(typeOfRequest);
    }

    public String getAllowedMethodsForUri(String uniformResourceIdentifier) {
        Handler handler = resourcesWithHandlers.get(uniformResourceIdentifier);
        String[] allowedMethods = handler.getAllowedMethods();
        StringBuilder allowedMethodsFormatted = new StringBuilder();
        for (int i = 0; i <= allowedMethods.length - 1; i++) {
            allowedMethodsFormatted.append(allowedMethods[i]);
            if (i != allowedMethods.length - 1) {
                allowedMethodsFormatted.append(", ");
            }
        }
        return allowedMethodsFormatted.toString();
    }

    public HttpResponseWrapper route(HttpRequestInterface httpRequest) throws IOException, URISyntaxException, InterruptedException {
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

package com.sethpthomas91.httpserver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Router {
    public Map<String, String[]> resources;

    public Router() {
        this.resources = createResources();
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
        return resources;
    }

    public boolean methodAllowed(String typeOfRequest, String uniformResourceIdentifier) {
        String[] resourceMethods = resources.get(uniformResourceIdentifier);
        return Arrays.stream(resourceMethods).toList().contains(typeOfRequest);
    }
}

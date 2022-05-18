package com.sethpthomas91.httpserver.request;


import java.util.HashMap;
import java.util.Map;

public class RequestHeaders {
    String contentType;

    public void processHeaders(String incomingHeaders) {
        Map<String, String> mapOfHeaders = deconstructHeaders(incomingHeaders);
        String contentType = getContentTypeFromMap(mapOfHeaders);
        setContentType(contentType);
    }


    private Map<String, String> deconstructHeaders(String incomingHeaders) {
        String[] listOfHeaders = incomingHeaders.split("\r\n");
        Map<String, String> mapOfHeaders = new HashMap<>();
        for (String titleAndHeaderString : listOfHeaders) {
            String[] titleAndHeaderList = titleAndHeaderString.split(":");
            mapOfHeaders.put(titleAndHeaderList[0], titleAndHeaderList[1]);
        }
        return mapOfHeaders;
    }

    private String getContentTypeFromMap(Map<String, String> mapOfHeaders) {
        return mapOfHeaders.get("Content-Type");
    }

    private void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}

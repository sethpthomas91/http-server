package com.sethpthomas91.httpserver.utils;

import com.sethpthomas91.httpserver.interfaces.ClientWrapperInterface;
import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientWrapper implements ClientWrapperInterface {

    private HttpClient createHttpClient() {
        return HttpClient.newHttpClient();
    }

    private HttpRequest createHttpRequest(HttpRequestInterface incomingRequest) throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:3000/tasks"))
                .headers("Content-Type", "application/json;charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(incomingRequest.getBody()))
                .build();
        return request;
    }

    private HttpResponse<String> sendRequest(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> actAsProxy(HttpRequestInterface incomingRequest) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = createHttpClient();
        HttpRequest requestToJsonAPI = null;
        switch(incomingRequest.getRequestLine().getTypeOfRequest()) {
            case "POST":
                requestToJsonAPI = createPostRequest(incomingRequest);
                break;
            case "PUT":
                requestToJsonAPI = createPutRequest(incomingRequest);
                break;
            case "DELETE":
                requestToJsonAPI = createDeleteRequest(incomingRequest);
                break;
        }
        
        HttpResponse responseFromJsonAPI = sendRequest(client, requestToJsonAPI);
        return responseFromJsonAPI;
    }

    private HttpRequest createPostRequest(HttpRequestInterface incomingRequest) throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:3000/tasks"))
                .headers("Content-Type", "application/json;charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(incomingRequest.getBody()))
                .build();
        return request;
    }

    private String extractTaskNumber(String uri) {
        return uri.split("/")[2];
    }

    private HttpRequest createPutRequest(HttpRequestInterface incomingRequest) throws URISyntaxException {
        String taskNumber = extractTaskNumber(incomingRequest.getRequestLine().getUniformResourceIdentifier());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:3000/tasks" + "/" + taskNumber))
                .headers("Content-Type", "application/json;charset=utf-8")
                .PUT(HttpRequest.BodyPublishers.ofString(incomingRequest.getBody()))
                .build();
        return request;
    }

    private HttpRequest createDeleteRequest(HttpRequestInterface incomingRequest) throws URISyntaxException {
        String taskNumber = extractTaskNumber(incomingRequest.getRequestLine().getUniformResourceIdentifier());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:3000/tasks" + "/" + taskNumber))
                .headers("Content-Type", "application/json;charset=utf-8")
                .DELETE()
                .build();
        return request;
    }
}

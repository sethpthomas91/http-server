package com.sethpthomas91.httpserver.utils;

import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientWrapper {

    private HttpClient createHttpClient() {
        return HttpClient.newHttpClient();
    }

    private HttpRequest createHttpRequest(HttpRequestInterface incomingRequest) throws URISyntaxException {

        System.out.println("Here is the URI");
        System.out.println(incomingRequest.getRequestLine().getUniformResourceIdentifier());


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
        HttpRequest requestToJsonAPI = createHttpRequest(incomingRequest);
        HttpResponse responseFromJsonAPI = sendRequest(client, requestToJsonAPI);
        return responseFromJsonAPI;
    }
}

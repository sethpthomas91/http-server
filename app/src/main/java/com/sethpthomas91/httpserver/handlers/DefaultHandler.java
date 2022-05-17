package com.sethpthomas91.httpserver.handlers;

import com.sethpthomas91.httpserver.Router;
import com.sethpthomas91.httpserver.interfaces.HttpRequestInterface;
import com.sethpthomas91.httpserver.response.Body;
import com.sethpthomas91.httpserver.response.Header;
import com.sethpthomas91.httpserver.response.HttpResponseWrapper;
import com.sethpthomas91.httpserver.response.StatusLine;

import java.io.IOException;

public class DefaultHandler implements Handler{

    @Override
    public HttpResponseWrapper handle(HttpRequestInterface httpRequest) throws IOException {
        HttpResponseWrapper httpResponse = new HttpResponseWrapper();
        httpResponse = handleStatusLine(httpRequest, httpResponse);
        httpResponse = handleBody(httpRequest, httpResponse);
        httpResponse = handleHeaders(httpRequest, httpResponse);
        return httpResponse;
    }

    private HttpResponseWrapper handleStatusLine(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) {
        StatusLine statusLine = new StatusLine();
        if (httpRequest.getRequestLine().getUniformResourceIdentifier().equals("/redirect")) {
            statusLine.setStatusCode("301");
            statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
            statusLine.setResponseText("Moved Permanently");
        } else {
            statusLine.setStatusCode("200");
            statusLine.setHttpVersion(httpRequest.getRequestLine().getHttpVersion());
            statusLine.setResponseText("OK");
        }
        httpResponse.setStatusLine(statusLine);
        return httpResponse;
    }

    private HttpResponseWrapper handleBody(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Body body = new Body();
        switch (httpRequest.getRequestLine().getUniformResourceIdentifier()) {
            case "/simple_get_with_body":
                body.setBodyText("Hello world");
                break;
            case "/echo_body":
                body.setBodyText("some body");
                break;
            case "/text_response":
                body.setBodyText("text response");
                break;
            case "/html_response":
                body.setBodyText("<html><body><p>HTML Response</p></body></html>");
                break;
            case "/json_response":
                body.setBodyText("{\"key1\":\"value1\",\"key2\":\"value2\"}");
                break;
            case "/xml_response":
                body.setBodyText("<note><body>XML Response</body></note>");
                break;
        }
        httpResponse.setBody(body);
        return httpResponse;
    }

    private HttpResponseWrapper handleHeaders(HttpRequestInterface httpRequest, HttpResponseWrapper httpResponse) throws IOException {
        Header header = new Header(httpRequest, httpResponse);
        Router router = new Router();
        switch (httpRequest.getRequestLine().getUniformResourceIdentifier()) {
            case "/head_request":
                header.setAllowedHeaders("HEAD, OPTIONS");
                break;
            case "/method_options":
            case "/method_options2":
                header.setAllowedHeaders(router.getAllowedMethodsForUri(httpRequest.getRequestLine().getUniformResourceIdentifier()));
                break;
            case "/text_response":
                header.setContentType("text/plain;charset=utf-8");
                break;
            case "/html_response":
                header.setContentType("text/html;charset=utf-8");
                break;
            case "/json_response":
                header.setContentType("application/json;charset=utf-8");
                break;
            case "/xml_response":
                header.setContentType("application/xml;charset=utf-8");
                break;
            case "/redirect":
                header.setLocation("http://127.0.0.1:5000/simple_get");
                break;
        }
        httpResponse.setHeaders(header);
        return httpResponse;
    }
}

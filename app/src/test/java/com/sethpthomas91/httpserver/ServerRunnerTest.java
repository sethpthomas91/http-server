package com.sethpthomas91.httpserver;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.http.HttpClient;

import static org.junit.Assert.*;

public class ServerRunnerTest {

    @Test
    public void testsServerPortDefaultsTo5000() {
        ServerRunner serverRunner = new ServerRunner();
        Assert.assertEquals(5000, serverRunner.getPort());
    }

    @Test
    public void testsRunServerCanAcceptCustomPort() {
        ServerRunner serverRunner = new ServerRunner();
        serverRunner.setPort(5010);
        Assert.assertEquals(5010, serverRunner.getPort());
    }

    @Test
    public void testsRunServerStartStartsSocketInsideServerSocketWrapper() throws IOException {
        ServerRunner serverRunner = new ServerRunner();
        MockServerSocket mockServerSocketWrapper = new MockServerSocket(5050);
        serverRunner.setServerSocketWrapper(mockServerSocketWrapper);
        serverRunner.createServer();
        serverRunner.startServer();
        Assert.assertTrue(mockServerSocketWrapper.createSocketWasCalled());
    }

    @Test
    public void testsRunServerStartStartsSocketReaderInsideServerSocketWrapper() throws IOException {
        ServerRunner serverRunner = new ServerRunner();
        MockServerSocket mockServerSocketWrapper = new MockServerSocket(5050);
        serverRunner.setServerSocketWrapper(mockServerSocketWrapper);
        serverRunner.createServer();
        serverRunner.startServer();
        Assert.assertTrue(mockServerSocketWrapper.createReaderWasCalled());
    }

    @Test
    public void testsRunServerStartStartsSocketWriterInsideServerSocketWrapper() throws IOException {
        ServerRunner serverRunner = new ServerRunner();
        MockServerSocket mockServerSocketWrapper = new MockServerSocket(5050);
        serverRunner.setServerSocketWrapper(mockServerSocketWrapper);
        serverRunner.createServer();
        serverRunner.startServer();
        Assert.assertTrue(mockServerSocketWrapper.createWriterWasCalled());
    }

    @Test
    public void testsServerLogicHandlesASimpleGetRequest() {
        ServerLogic serverLogic = new ServerLogic();
        serverLogic.setToTestingObject();
        String request = "GET / HTTP/1.1";
        String expectedResponse = "HTTP/1.1 200 OK\r\n";
        String actualResponse = serverLogic.processString(request);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testsServerLogicHandlesASimpleGetRequestToAvailableResource() {
        ServerLogic serverLogic = new ServerLogic();
        serverLogic.setToTestingObject();
        String request = "GET /simple_get HTTP/1.1";
        String expectedResponse = "HTTP/1.1 200 OK\r\n";
        String actualResponse = serverLogic.processString(request);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

}
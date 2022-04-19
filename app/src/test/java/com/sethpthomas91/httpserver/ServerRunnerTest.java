package com.sethpthomas91.httpserver;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

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
        MockServerSocket mockServerSocketWrapper = new MockServerSocket();
        serverRunner.setServerSocketWrapper(mockServerSocketWrapper);
        serverRunner.startServer();
        Assert.assertTrue(mockServerSocketWrapper.createSocketWasCalled());
    }



}
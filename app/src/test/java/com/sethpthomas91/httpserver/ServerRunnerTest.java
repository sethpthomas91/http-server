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
    public void testSetPort() {
        ServerRunner serverRunner = new ServerRunner();
        serverRunner.setPort(5010);
        Assert.assertEquals(5010, serverRunner.getPort());
    }

}
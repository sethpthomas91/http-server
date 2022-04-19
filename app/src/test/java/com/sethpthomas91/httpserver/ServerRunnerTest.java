package com.sethpthomas91.httpserver;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerRunnerTest {

    @Test
    public void testCasePassingTest() {
        ServerRunner newServer = new ServerRunner();
        Assert.assertTrue(newServer.testCasePassingTest());
    }

}
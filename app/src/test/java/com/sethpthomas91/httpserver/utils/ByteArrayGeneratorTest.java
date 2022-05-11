package com.sethpthomas91.httpserver.utils;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class ByteArrayGeneratorTest {

    @Test
    public void testGenerateReturnBytesFromString() {
        ByteArrayGenerator byteArrayGenerator = new ByteArrayGenerator();
        String message = "Hello World";
        byte[] stringBytes = byteArrayGenerator.convertStringToBytes(message);
        Assert.assertArrayEquals(message.getBytes(), stringBytes);
    }

}
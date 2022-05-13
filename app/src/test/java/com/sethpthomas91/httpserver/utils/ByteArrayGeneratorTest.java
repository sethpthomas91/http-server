package com.sethpthomas91.httpserver.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class ByteArrayGeneratorTest {

    @Test
    public void testGenerateReturnBytesFromString() {
        ByteArrayGenerator byteArrayGenerator = new ByteArrayGenerator();
        String message = "Hello World";
        byte[] stringBytes = byteArrayGenerator.convertStringToBytes(message);
        Assert.assertArrayEquals(message.getBytes(), stringBytes);
    }

    @Test
    public void testGenerateBytesFromFile() throws IOException {
        ByteArrayGenerator byteArrayGenerator = new ByteArrayGenerator();
        String pathString = System.getenv("PUBLIC_DIR") + "kitteh.jpg";
        byte[] fileBytes = byteArrayGenerator.convertFileToBytes(pathString);
        byte[] imageData = Files.readAllBytes(new File(pathString).toPath());
        Assert.assertArrayEquals(imageData, fileBytes);
    }

}
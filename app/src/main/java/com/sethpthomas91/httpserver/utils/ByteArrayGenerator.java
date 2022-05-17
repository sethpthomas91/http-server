package com.sethpthomas91.httpserver.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ByteArrayGenerator {

    public static byte[] convertStringToBytes(String message) {
        return message.getBytes();
    }

    public static byte[] convertFileToBytes(String pathString) throws IOException {
        return Files.readAllBytes(new File(pathString).toPath());
    }
}

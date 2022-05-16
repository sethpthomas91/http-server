package com.sethpthomas91.httpserver.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HtmlFileReader {
    public static String readHtmlFile(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader input = new BufferedReader(new FileReader(path));
        String pageContent;
        while ((pageContent = input.readLine()) != null) {
            stringBuilder.append(pageContent);
        }
        input.close();
        return stringBuilder.toString();
    }
}

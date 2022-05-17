package com.sethpthomas91.httpserver.utils;

public class StaticVariables {
    static public String getPublicDirectory() {
        return System.getenv().get("PUBLIC_DIR");
    }
}

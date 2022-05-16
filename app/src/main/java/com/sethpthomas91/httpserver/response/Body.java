package com.sethpthomas91.httpserver.response;

import com.sethpthomas91.httpserver.utils.ByteArrayGenerator;

import java.io.*;

public class Body {
    String bodyText;
    byte[] bodyBytes;


    public String getBody() {
        if (bodyText != null) {
            return this.bodyText;
        } else {
            return null;
        }
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public byte[] getBodyBytes() {
        if (bodyText != null) {
            bodyBytes = ByteArrayGenerator.convertStringToBytes(bodyText);
        }
        return this.bodyBytes;
    }

    public void setBodyBytes(byte[] bytes) {
        bodyBytes = bytes;
    }

}

package com.sethpthomas91.httpserver.response;

import com.sethpthomas91.httpserver.utils.ByteArrayGenerator;

import java.io.*;

public class Body {
    String bodyText;
    byte[] bodyBytes;

    public Body(String uniformResourceIdentifier) throws IOException {
        if (uniformResourceIdentifier.equals("/echo_body")) {
            this.bodyText = "some body";
        } else if (uniformResourceIdentifier.equals("/text_response")) {
            this.bodyText = "text response";
        } else if (uniformResourceIdentifier.equals("/html_response")) {
            this.bodyText = "<html><body><p>HTML Response</p></body></html>";
        } else if (uniformResourceIdentifier.equals("/json_response")) {
            this.bodyText = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        } else if (uniformResourceIdentifier.equals("/xml_response")) {
            this.bodyText = "<note><body>XML Response</body></note>";
        }
    }

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

package com.sethpthomas91.httpserver;

public class Body {
    String CRLF = "\r\n";
    String bodyText;

    public Body(String uniformResourceIdentifier) {
        if (uniformResourceIdentifier.equals("/simple_get_with_body")) {
            this.bodyText = "Hello world";
        }
    }

    public String getBody() {
        if (!bodyText.isEmpty()) {
            return this.bodyText + CRLF;
        } else {
            return "";
        }
    }

    public String getText() {
        return this.bodyText;
    }


}

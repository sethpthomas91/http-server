package com.sethpthomas91.httpserver.response;

public class Body {
    String bodyText;

    public Body(String uniformResourceIdentifier) {
        if (uniformResourceIdentifier.equals("/simple_get_with_body")) {
            this.bodyText = "Hello world";
        } else if (uniformResourceIdentifier.equals("/echo_body")) {
            this.bodyText = "some body";
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

}

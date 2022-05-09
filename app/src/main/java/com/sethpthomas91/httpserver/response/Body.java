package com.sethpthomas91.httpserver.response;

public class Body {
    String bodyText;

    public Body(String uniformResourceIdentifier) {
        if (uniformResourceIdentifier.equals("/simple_get_with_body")) {
            this.bodyText = "Hello world";
        } else if (uniformResourceIdentifier.equals("/echo_body")) {
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

}

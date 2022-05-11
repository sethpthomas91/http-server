package com.sethpthomas91.httpserver.response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class Body {
    String bodyText;

    public Body(String uniformResourceIdentifier) throws IOException {
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
        } else if (uniformResourceIdentifier.equals("/health-check.html")) {
            this.bodyText = "<strong>Status:</strong> pass";
        } else if (uniformResourceIdentifier.equals("/kitteh.jpg")) {
            this.bodyText = convertImageToBytes();
        }
    }

    public String convertImageToBytes() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("/Users/sthomas/Learning/Java/http-server/app/Public/kitteh.jpg"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        System.out.println(byteArrayOutputStream);
        String base64 = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        return byteArrayOutputStream.toString();

//        byte[] imageData = Files.readAllBytes(new File("/Users/sthomas/Learning/Java/http-server/app/Public/kitteh.jpg").toPath());
////        String base64 = Base64.getEncoder().encodeToString(imageData);
//        return imageData.toString();
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

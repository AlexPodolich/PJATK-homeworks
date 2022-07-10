package com.company;

import java.io.Serializable;

public class EchoResponse implements Serializable {
    public String message;

    public EchoResponse(String message) {
        this.message = "Echo " + message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "EchoResponse{" +
                "echoedMessage='" + message + '\'' +
                '}';
    }
}

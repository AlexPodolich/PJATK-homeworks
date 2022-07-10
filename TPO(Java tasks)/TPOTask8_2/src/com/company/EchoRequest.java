package com.company;

import java.io.Serializable;

public class EchoRequest implements Serializable {

    private String message;

    public EchoRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "EchoRequest{" +
                "messageToEcho='" + message + '\'' +
                '}';
    }
}

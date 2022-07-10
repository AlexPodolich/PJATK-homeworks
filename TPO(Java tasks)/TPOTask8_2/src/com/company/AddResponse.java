package com.company;

import java.io.Serializable;

public class AddResponse implements Serializable {
    public int sum;

    public AddResponse(int num1, int num2) {
        sum = responseAdd(num1, num2);
    }

    public int responseAdd(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public String toString() {
        return "AddResponse{" +
                "sum=" + sum +
                '}';
    }
}

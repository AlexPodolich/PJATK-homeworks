package com.company;

import java.io.Serializable;

public class AddRequest implements Serializable {
    private int num1;
    private int num2;

    public AddRequest(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    @Override
    public String toString() {
        return "AddRequest{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                '}';
    }
}

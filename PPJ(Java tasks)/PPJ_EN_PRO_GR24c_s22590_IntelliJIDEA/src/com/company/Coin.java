package com.company;

public class Coin extends Object {

    public Coin(String objectName, int objectCoordinateX, int objectCoordinateY) {
        super(objectName, objectCoordinateX, objectCoordinateY);
    }

    public Coin() {
        objectName = "coin";
        objectCoordinateX = 1;
        objectCoordinateY = 1;
    }
}

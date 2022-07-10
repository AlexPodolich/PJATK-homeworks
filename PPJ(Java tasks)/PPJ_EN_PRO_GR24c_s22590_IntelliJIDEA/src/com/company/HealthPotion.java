package com.company;

public class HealthPotion extends Object {

    public HealthPotion(String objectName, int objectCoordinateX, int objectCoordinateY) {
        super(objectName, objectCoordinateX, objectCoordinateY);
    }

    public HealthPotion() {
        objectName = "health potion";
        objectCoordinateX = 1;
        objectCoordinateY = 1;
    }
}

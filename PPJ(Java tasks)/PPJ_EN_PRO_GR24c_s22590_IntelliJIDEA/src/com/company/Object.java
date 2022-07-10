package com.company;

public class Object {
    public String objectName;
    public int objectCoordinateX;
    public int objectCoordinateY;

    public Object() {
        objectName = "object1";
        objectCoordinateX = 1;
        objectCoordinateY = 1;
    }

    public Object(String objectName, int objectCoordinateX, int objectCoordinateY) {
        this.objectName = objectName;
        this.objectCoordinateX = objectCoordinateX;
        this.objectCoordinateY = objectCoordinateY;
    }

    public String checkCoordinates() {
        return objectName + " coordinates are: " + "(X: " + objectCoordinateX + ", Y: " + objectCoordinateY + ")";
    }

    public void placeObjectOnMap(Map map) {
        objectCoordinateX = (int) (1 + Math.random() * (int) Math.ceil((float) map.getMapSize()));
        objectCoordinateY = (int) (1 + Math.random() * (int) Math.ceil((float) map.getMapSize()));
    }
}



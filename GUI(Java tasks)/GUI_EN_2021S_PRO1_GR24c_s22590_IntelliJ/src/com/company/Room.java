package com.company;

public class Room {
    private double length = 0;
    private double width = 0;
    private double height = 0;
    private double volume = 0;

    public Room(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Room(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Room{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public double getVolume(){
        double roomVolume;
        if(length * width * height == 0){
            return volume;
        }else{
            roomVolume = length * width * height;
            return roomVolume;
        }
    }
}


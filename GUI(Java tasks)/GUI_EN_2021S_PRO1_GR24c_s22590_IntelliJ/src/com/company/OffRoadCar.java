package com.company;

public class OffRoadCar extends Vehicle{
    private double trunkVolume;

    public OffRoadCar(String name, double volume, int weightKilo, String color, int yearOfProduction, double trunkVolume) {
        super(name, volume, weightKilo, color, yearOfProduction);
        this.trunkVolume = trunkVolume;
    }

    @Override
    public String toString() {
        return super.toString() + " trunkVolume: " + trunkVolume;
    }
}

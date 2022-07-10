package com.company;

public class CityCar extends Vehicle{
    private double engineVolume;

    public CityCar(String name, double volume, int weightKilo, String color, int yearOfProduction, double engineVolume) {
        super(name, volume, weightKilo, color, yearOfProduction);
        this.engineVolume = engineVolume;
    }

    @Override
    public String toString() {
        return super.toString() + " engineVolume: " + engineVolume;
    }
}

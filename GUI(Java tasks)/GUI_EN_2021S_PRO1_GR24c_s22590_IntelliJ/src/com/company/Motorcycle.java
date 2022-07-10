package com.company;

public class Motorcycle extends Vehicle{
    private int maxSpeed;

    public Motorcycle(String name, double volume, int weightKilo, String color, int yearOfProduction, int maxSpeed) {
        super(name, volume, weightKilo, color, yearOfProduction);
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return super.toString() + " maxSpeed: " + maxSpeed;
    }
}

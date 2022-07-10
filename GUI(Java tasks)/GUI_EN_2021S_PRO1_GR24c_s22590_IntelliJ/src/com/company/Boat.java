package com.company;

public class Boat extends Vehicle{
    private double motorPower;

    public Boat(String name, double volume, int weightKilo, String color, int yearOfProduction, double motorPower) {
        super(name, volume, weightKilo, color, yearOfProduction);
        this.motorPower = motorPower;
    }

    @Override
    public String toString() {
        return super.toString() + " motorPower: " + motorPower ;
    }
}

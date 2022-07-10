package com.company;

public class Amphibious extends Vehicle{
    private String engineType;

    public Amphibious(String name, double volume, int weightKilo, String color, int yearOfProduction, String engineType) {
        super(name, volume, weightKilo, color, yearOfProduction);
        this.engineType = engineType;
    }

    @Override
    public String toString() {

        return super.toString() + " engineType: " + engineType;
    }
}

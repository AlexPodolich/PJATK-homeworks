package com.company;

public abstract class Vehicle extends Item{
    private int weightKilo;
    private String color;
    private int yearOfProduction;

    public Vehicle(String name, double volume, int weightKilo, String color, int yearOfProduction) {
        super(name, volume);
        this.weightKilo = weightKilo;
        this.color = color;
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString() {
        return super.toString() + ", weight: " + weightKilo + " kg, color: " + color  + ", year of production: " + yearOfProduction + ", ";
    }

    public int getWeightKilo() {
        return weightKilo;
    }

    public void setWeightKilo(int weightKilo) {
        this.weightKilo = weightKilo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
}

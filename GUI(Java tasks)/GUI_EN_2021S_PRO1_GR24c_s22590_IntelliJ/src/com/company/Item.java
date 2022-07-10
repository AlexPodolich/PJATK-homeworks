package com.company;

import java.util.Collection;

public class Item implements Comparable<Item> {
    private String name;
    private double volume;


    public Item(String name, double volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return name + " with volume: " + volume + " m3";
    }

    @Override
    public int compareTo(Item otherItem) {
        return name.compareTo(otherItem.name);
    }
}

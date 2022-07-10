package com.company;

import java.util.Vector;

public class CustomVector {
    private Vector<Double> vector;
    private String className;

    public CustomVector(Vector<Double> vector, String className) {
        this.vector = vector;
        this.className = className;
    }

    public Vector<Double> getVector() {
        return vector;
    }

    public void setVector(Vector<Double> vector) {
        this.vector = vector;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "vector = " + vector + ", className = " + className;
    }
}

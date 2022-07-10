package com.company;

import java.util.ArrayList;
import java.util.Vector;

public class Cluster {
    private Vector<Double> centroidVector;
    private String clusterClassName;
    private ArrayList<CustomVector> arrOfVectors = new ArrayList<>();

    public Cluster(String clusterClassName) {
        this.clusterClassName = clusterClassName;
    }

    public Vector<Double> getCentroidVector() {
        return centroidVector;
    }

    public void setCentroidVector(Vector<Double> centroidVector) {
        this.centroidVector = centroidVector;
    }

    public String getClusterClassName() {
        return clusterClassName;
    }

    public void setClusterClassName(String clusterClassName) {
        this.clusterClassName = clusterClassName;
    }

    public ArrayList<CustomVector> getArrOfVectors() {
        return arrOfVectors;
    }

    public void setArrOfVectors(ArrayList<CustomVector> arrOfVectors) {
        this.arrOfVectors = arrOfVectors;
    }


    @Override
    public String toString() {
        return "Cluster{" +
                "centroidVector=" + centroidVector +
                ", clusterClassName='" + clusterClassName + '\'' +
                '}';
    }

}

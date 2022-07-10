package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Country {
    private String name;
    private boolean hasAirport;
    private boolean hasPort;
    private boolean isColdCountry;
    private boolean isHotCountry;
    private String pathFlagImg;
    private String pathCountryImg;
    private long countryPopulation;
    private long infectedPeople;
    private long unInfectedPeople;
    private long virusCases;
    private long curedPeople;
    private JButton countryBtn;
    private boolean isLockDown;
    private ArrayList<String> neighbourCountry;
    private ArrayList<String> portCountry;
    private ArrayList<String> airportCountry;

    public Country(String name, boolean hasAirport, boolean hasPort, boolean isColdCountry, boolean isHotCountry, long countryPopulation, String pathFlagImg, String pathCountryImg) {
        this.name = name;
        this.hasAirport = hasAirport;
        this.hasPort = hasPort;
        this.isColdCountry = isColdCountry;
        this.isHotCountry = isHotCountry;
        this.isLockDown = false;
        this.countryPopulation = countryPopulation;
        this.pathFlagImg = pathFlagImg;
        this.pathCountryImg = pathCountryImg;
        virusCases = 0;
        infectedPeople = 0;
        unInfectedPeople = countryPopulation;
        curedPeople = 0;
    }

    public Country(String name, boolean hasAirport, boolean hasPort, boolean isColdCountry, boolean isHotCountry, long countryPopulation, long infectedPeople, String pathFlagImg, String pathCountryImg) {
        this.name = name;
        this.hasAirport = hasAirport;
        this.hasPort = hasPort;
        this.isColdCountry = isColdCountry;
        this.isHotCountry = isHotCountry;
        this.isLockDown = false;
        this.countryPopulation = countryPopulation;
        this.infectedPeople = infectedPeople;
        this.pathFlagImg = pathFlagImg;
        this.pathCountryImg = pathCountryImg;
        virusCases = infectedPeople;
        unInfectedPeople = countryPopulation - infectedPeople;
        curedPeople = 0;
    }

    @Override
    public String toString() {
        return "Country " + name + " has " + infectedPeople + "/" + countryPopulation + " infected people";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasAirport() {
        return hasAirport;
    }

    public void setHasAirport(boolean hasAirport) {
        this.hasAirport = hasAirport;
    }

    public boolean isHasPort() {
        return hasPort;
    }

    public void setHasPort(boolean hasPort) {
        this.hasPort = hasPort;
    }

    public boolean isColdCountry() {
        return isColdCountry;
    }

    public void setColdCountry(boolean coldCountry) {
        isColdCountry = coldCountry;
    }

    public boolean isHotCountry() {
        return isHotCountry;
    }

    public void setHotCountry(boolean hotCountry) {
        isHotCountry = hotCountry;
    }

    public long getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(long countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public long getInfectedPeople() {
        return infectedPeople;
    }

    public void setInfectedPeople(long infectedPeople) {
        this.infectedPeople = infectedPeople;
    }

    public long getUnInfectedPeople() {
        return unInfectedPeople;
    }

    public void setUnInfectedPeople(long unInfectedPeople) {
        this.unInfectedPeople = unInfectedPeople;
    }

    public long getCuredPeople() {
        return curedPeople;
    }

    public void setCuredPeople(long curedPeople) {
        this.curedPeople = curedPeople;
    }

    public String getPathFlagImg() {
        return pathFlagImg;
    }

    public void setPathFlagImg(String pathFlagImg) {
        this.pathFlagImg = pathFlagImg;
    }

    public String getPathCountryImg() {
        return pathCountryImg;
    }

    public void setPathCountryImg(String pathCountryImg) {
        this.pathCountryImg = pathCountryImg;
    }

    public long getVirusCases() {
        return virusCases;
    }

    public void setVirusCases(long virusCases) {
        this.virusCases = virusCases;
    }

    public JButton getCountryBtn() {
        return countryBtn;
    }

    public void setCountryBtn(JButton countryBtn) {
        this.countryBtn = countryBtn;
    }

    public boolean isLockDown() {
        return isLockDown;
    }

    public void setLockDown(boolean lockDown) {
        isLockDown = lockDown;
    }

    public ArrayList<String> getNeighbourCountry() {
        return neighbourCountry;
    }

    public void setNeighbourCountry(ArrayList<String> neighbourCountry) {
        this.neighbourCountry = neighbourCountry;
    }

    public ArrayList<String> getPortCountry() {
        return portCountry;
    }

    public void setPortCountry(ArrayList<String> portCountry) {
        this.portCountry = portCountry;
    }

    public ArrayList<String> getAirportCountry() {
        return airportCountry;
    }

    public void setAirportCountry(ArrayList<String> airportCountry) {
        this.airportCountry = airportCountry;
    }
}

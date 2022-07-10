package com.company;

import java.util.List;

public class HousingEstate {
    private List<Apartment> apartments;

    public HousingEstate(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    @Override
    public String toString() {
        return "HousingEstate{" +
                "apartments=" + apartments +
                '}';
    }
}

package com.company;

import java.util.List;

public class Developer {
    private String name;
    private String surname;
    private List<HousingEstate> housingEstates;

    public Developer(String name, String surname, List<HousingEstate> housingEstates) {
        this.name = name;
        this.surname = surname;
        this.housingEstates = housingEstates;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", housingEstates=" + housingEstates +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<HousingEstate> getHousingEstates() {
        return housingEstates;
    }

    public void setHousingEstates(List<HousingEstate> housingEstates) {
        this.housingEstates = housingEstates;
    }
}





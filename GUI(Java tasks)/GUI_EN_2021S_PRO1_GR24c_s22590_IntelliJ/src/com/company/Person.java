package com.company;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String surname;
    private String peselNumber;
    private String address;
    private List<Apartment> personApartments;
    private List<ParkingPlace> parkingPlaces;
    private List<Apartment> tenatApartments;
    private List<ParkingPlace> tenatParkingPlace;
    private List<TenantLetter> tenantLetters;
    private boolean isTenant;
    private boolean isRentingParkingPlace;

    public Person(String name, String surname, String peselNumber, String address) {
        this.name = name;
        this.surname = surname;
        this.peselNumber = peselNumber;
        this.address = address;
        parkingPlaces = new ArrayList<>();
        personApartments = new ArrayList<>();
        tenatApartments = new ArrayList<>();
        tenatParkingPlace = new ArrayList<>();
        tenantLetters = new ArrayList<>();
        isTenant = false;
        isRentingParkingPlace = false;
    }

    @Override
    public String toString() {
        return "\n" + name + " " + surname + " with pesel number: " + peselNumber + " and address: " + address;
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

    public String getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(String peselNumber) {
        this.peselNumber = peselNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<TenantLetter> getTenantLetters() {
        return tenantLetters;
    }

    public void setTenantLetter(TenantLetter tenantLetter) {
        tenantLetters.add(tenantLetter);
    }

    public void setTenantLetters(List<TenantLetter> tenantLetters) {
        this.tenantLetters = tenantLetters;
    }

    public boolean isTenant() {
        return isTenant;
    }

    public void setTenant(boolean tenant) {
        isTenant = tenant;
    }

    public List<Apartment> getTenatApartments() {
        return tenatApartments;
    }

    public void setTenatApartments(List<Apartment> tenatApartments) {
        this.tenatApartments = tenatApartments;
    }

    public void setTenatApartment(Apartment apartment) {
        tenatApartments.add(apartment);
    }

    public List<ParkingPlace> getTenatParkingPlace() {
        return tenatParkingPlace;
    }

    public void setTenatParkingPlace(List<ParkingPlace> tenatParkingPlace) {
        this.tenatParkingPlace = tenatParkingPlace;
    }

    public List<Apartment> getPersonApartments() {
        return personApartments;
    }

    public void setPersonApartments(List<Apartment> personApartments) {
        this.personApartments = personApartments;
    }

    public void setPersonApartment(Apartment apartment) {
        personApartments.add(apartment);
    }

    public List<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }

    public void setParkingPlaces(List<ParkingPlace> parkingPlaces) {
        if(this.isTenant){
            isRentingParkingPlace = true;
            setTenatParkingPlace(parkingPlaces);
        }else {
            this.parkingPlaces = parkingPlaces;
        }
    }


    public boolean isRentingParkingPlace() {
        return isRentingParkingPlace;
    }

    public void setRentingParkingPlace(boolean rentingParkingPlace) {
        isRentingParkingPlace = rentingParkingPlace;
    }


}

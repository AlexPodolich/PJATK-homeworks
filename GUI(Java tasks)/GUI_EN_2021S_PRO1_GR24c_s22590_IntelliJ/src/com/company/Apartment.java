package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Apartment extends Room{
    private String apartmentNumber;
    private static int counter = 0;
    private int uniqueApartmentIdentifier;
    private Person tenant;
    private List<Person> people;
    private Date rentStartDate = new Date();
    private Date rentEndDate = new Date();
    private int numOfDelayPay = 0;
    private int numOfDelayPayForParkingPlace = 0;
    private ParkingPlace parkingPlace;

    public Apartment(String apartmentNumber, double length, double width, double height, ParkingPlace parkingPlace) {
        super(length, width, height);
        this.apartmentNumber = apartmentNumber;
        uniqueApartmentIdentifier = ++counter;
        this.parkingPlace = parkingPlace;
        people = new ArrayList<>();
    }

    public Apartment(String apartmentNumber, double volume, ParkingPlace parkingPlace) {
        super(volume);
        this.apartmentNumber = apartmentNumber;
        uniqueApartmentIdentifier = ++counter;
        this.parkingPlace = parkingPlace;
        people = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentNumber='" + apartmentNumber + '\'' +
                '}';
    }

    public Person getTenant() {
        return tenant;
    }

    public void setTenant(Person tenant) throws ProblematicTenantException{
        if(tenant.getTenantLetters().size() >= 3) throw new ProblematicTenantException("Person " + tenant.getName() + " had already renting rooms: " + tenant.getTenatApartments() + " (" + tenant.getName() + " has 3 or more tenant letters)");
        if((tenant.getTenatApartments().size() + tenant.getTenatParkingPlace().size()) < 5){
            this.tenant = tenant;
            tenant.setTenatApartment(this);
            startRenting();
            if(tenant.isRentingParkingPlace()){
                startRentingParkingPlace();
            }
        }else {
            System.out.println("This person has max number of rooms");
        }

    }

    public void startRenting(){
        long l = DateThread.currentDate.getTime();
        rentStartDate.setTime(l);
        rentEndDate.setTime(l + (2592000L * 1000));
        System.out.println("Tenant " + tenant.getName() + " " + tenant.getSurname() + " rented an apartment " + apartmentNumber +
                "\nStart date of renting for " + apartmentNumber + ": " + rentStartDate);
        System.out.println("End date of renting for " + apartmentNumber + ": " + rentEndDate);
        TenantLetter tenantLetter = new TenantLetter("You started renting apartment " + apartmentNumber + "(Today's date is " + DateThread.currentDate +")", "StartRentParkingPlace");
        tenant.setTenantLetter(tenantLetter);
    }

    public void cancelRenting(){
        if(tenant!=null){
            if(numOfDelayPay == 0){
                if(tenant.getTenatParkingPlace() != null){
                    List<ParkingPlace> allTenantParkingPlaces = tenant.getTenatParkingPlace();
                    for (int i = 0; i < allTenantParkingPlaces.size(); i++) {
                        if(allTenantParkingPlaces.get(i).getUniqueParkingPlaceIdentifier() == this.getParkingPlace().getUniqueParkingPlaceIdentifier()){
                            allTenantParkingPlaces.remove(i);
                            List<Item> items = this.getParkingPlace().getItems();
                            List<Vehicle> vehicles = this.getParkingPlace().getVehicles();
                            if(items!= null){
                                items.clear();
                                parkingPlace.setCurrentVolume(0.0);
                            }
                            if(vehicles!= null){
                                vehicles.clear();
                                parkingPlace.setCurrentVolume(0.0);
                            }
                            tenant.setTenatParkingPlace(allTenantParkingPlaces);
                            this.getParkingPlace().setItems(items);
                            this.getParkingPlace().setVehicles(vehicles);
                        }
                    }
                }
                if(tenant.getTenatApartments() != null){
                    List<Apartment> allTenantAparts = tenant.getTenatApartments();
                    for (int i = 0; i < allTenantAparts.size(); i++) {
                        if(allTenantAparts.get(i).getUniqueApartmentIdentifier() == this.getUniqueApartmentIdentifier()){
                            allTenantAparts.remove(i);
                            tenant.setTenatApartments(allTenantAparts);
                        }
                    }
                }
                if(people != null){
                    for (int i = 0; i < people.size(); i++) {
                        people.get(i).getPersonApartments().remove(this);
                    }
                    people.clear();
                }
                System.out.println(tenant.getName() + " " + tenant.getSurname() + " is no longer renting apartment " + apartmentNumber);
                tenant.setTenant(false);
                tenant.setRentingParkingPlace(false);
                tenant = null;
                System.out.println("All members, items and vehicles are no longer exist in " + apartmentNumber + " apartment");
            }else {
                System.out.println("You cant cancel rent if you have any delayed pays");
            }
        }else {
            System.out.println("Apartment "+ apartmentNumber + " doesn't renting at this moment");
        }
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Apartment.counter = counter;
    }

    public int getUniqueApartmentIdentifier() {
        return uniqueApartmentIdentifier;
    }

    public void setUniqueApartmentIdentifier(int uniqueApartmentIdentifier) {
        this.uniqueApartmentIdentifier = uniqueApartmentIdentifier;
    }

    public int getNumOfDelayPay() {
        return numOfDelayPay;
    }

    public void setNumOfDelayPay(int numOfDelayPay) {
        this.numOfDelayPay = numOfDelayPay;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    //in stead of renew rent, i made pay for rent and in this method also renew for 1 more month
    public void payForRent(){
        if(tenant!=null){
            numOfDelayPay = 0;
            long l = rentEndDate.getTime();
            rentEndDate.setTime(l + (2592000L * 1000));
            List<TenantLetter> letters = tenant.getTenantLetters();
            List<TenantLetter> warningLetters = tenant.getTenantLetters();
            int numOfWarningLetters = 0;
            for (int i = 0; i < letters.size(); i++) {
                if(letters.get(i).getType().equals("NotPayingInTimeRent")){
                    numOfWarningLetters++;
                    warningLetters.add(letters.get(i));
                }
            }
            if(numOfWarningLetters > 0){
                letters.remove(warningLetters.get(numOfWarningLetters-1));
                tenant.setTenantLetters(letters);
            }
            TenantLetter tenantLetter = new TenantLetter("You paid in time for " + apartmentNumber + "(Today's date is " + DateThread.currentDate +")", "RentPayment");
            tenant.setTenantLetter(tenantLetter);
            System.out.println("Tenat " +  tenant.getName() + " has successfully paid for rent of " + apartmentNumber + " apartment in this month");
            System.out.println("New rent end date: " + rentEndDate);
            System.out.println("Tenat " + tenant.getName() + " has " + numOfDelayPay + " delay pays for " + apartmentNumber + " apartment");
        }else {
            System.out.println("You cant pay for " + apartmentNumber + " apartment, because there is no tenant here");
        }
    }

    public void payForParkingPlace(){
        if(tenant!=null){
            if(tenant.isRentingParkingPlace()){
                numOfDelayPayForParkingPlace = 0;
                long l = parkingPlace.getParkingPlaceRentEndDate().getTime();
                parkingPlace.getParkingPlaceRentEndDate().setTime(l + (2592000L * 1000));
                List<TenantLetter> letters = tenant.getTenantLetters();
                List<TenantLetter> warningLetters = tenant.getTenantLetters();
                int numOfWarningLetters = 0;
                for (int i = 0; i < letters.size(); i++) {
                    if(letters.get(i).getType().equals("NotPayingInTimeParkingPlace")){
                        numOfWarningLetters++;
                        warningLetters.add(letters.get(i));
                    }
                }
                if(numOfWarningLetters > 0){
                    letters.remove(warningLetters.get(numOfWarningLetters-1));
                    tenant.setTenantLetters(letters);
                }
                TenantLetter tenantLetter = new TenantLetter("You paid in time for parking place for " + apartmentNumber + "(Today's date is " + DateThread.currentDate +")", "ParkingPlacePayment");
                tenant.setTenantLetter(tenantLetter);
                System.out.println("Tenat " +  tenant.getName() + " has successfully paid for parking place rent of " + apartmentNumber + " apartment in this month");
                System.out.println("New parking place rent end date: " + rentEndDate);
                System.out.println("Tenat " + tenant.getName() + " has " + numOfDelayPay + " delay pays for parking place for " + apartmentNumber + " apartment");
            }else {
                System.out.println("You cant pay for parking place, because you don't have a parking place for apartment " + apartmentNumber);
            }
        }else {
            System.out.println("You cant pay for " + apartmentNumber + " apartment, because there is no tenant here");
        }
    }

    //here i am cheaking for payment
    public void checkForPayment(){
        if(tenant != null){
            if(DateThread.currentDate.after(rentEndDate)){
                if(numOfDelayPay >= 2){
                    if(tenant.getTenatParkingPlace() != null){
                        List<ParkingPlace> allTenantParkingPlaces = tenant.getTenatParkingPlace();
                        for (int i = 0; i < allTenantParkingPlaces.size(); i++) {
                            if(allTenantParkingPlaces.get(i).getUniqueParkingPlaceIdentifier() == this.getParkingPlace().getUniqueParkingPlaceIdentifier()){
                                allTenantParkingPlaces.remove(i);
                                List<Item> items = this.getParkingPlace().getItems();
                                List<Vehicle> vehicles = this.getParkingPlace().getVehicles();
                                if(items!= null){
                                    items.clear();
                                    parkingPlace.setCurrentVolume(0.0);
                                }
                                if(vehicles!= null){
                                    vehicles.clear();
                                    parkingPlace.setCurrentVolume(0.0);
                                }
                                tenant.setTenatParkingPlace(allTenantParkingPlaces);
                                this.getParkingPlace().setItems(items);
                                this.getParkingPlace().setVehicles(vehicles);
                            }
                        }
                        System.out.println("All items and vehicles has been deleted from " + tenant.getName() + " parking place");
                        System.out.println("PARKING PLACE DELETED FROM " + tenant.getName() + " LIST");
                    }
                    if(tenant.getTenatApartments() != null){
                        List<Apartment> allTenantAparts = tenant.getTenatApartments();
                        for (int i = 0; i < allTenantAparts.size(); i++) {
                            if(allTenantAparts.get(i).getUniqueApartmentIdentifier() == this.getUniqueApartmentIdentifier()){
                                allTenantAparts.remove(i);
                                tenant.setTenatApartments(allTenantAparts);
                            }
                        }
                        System.out.println("APARTMENT DELETED FROM " + tenant.getName() + " LIST");
                    }
                    if(people != null){
                        people.clear();
                        System.out.println("All members of apartment " + apartmentNumber + " are no longer live there");
                    }
                    System.out.println("Now "+ tenant.getName() + " is no longer renting " + apartmentNumber + " apartment");
                    sendTenantLetterForKickingOut();
                    tenant.setTenant(false);
                    tenant.setRentingParkingPlace(false);
                    tenant = null;
                    numOfDelayPay = 0;
                }else {
                    sendTenantLetterForNotPaying();
                    numOfDelayPay++;
                    System.out.println("Tenat with name " + tenant.getName() + " got a letter about the apartment payment");
                    System.out.println("Tenat " + tenant.getName() + " has " + numOfDelayPay + " delay pays for " + apartmentNumber + " apartment");
                }
            }else {
                System.out.println("Tenat with name " + tenant.getName() + " paid for the apartment in this month");
            }

            if(tenant != null && tenant.isRentingParkingPlace()){
                if(DateThread.currentDate.after(parkingPlace.getParkingPlaceRentEndDate())){
                    if(numOfDelayPayForParkingPlace >= 2){
                        System.out.println("Now "+ tenant.getName() + " is no longer renting parking place for " + apartmentNumber + " apartment");
                        sendTenantLetterForStopRentingParkingPlace();
                        tenant.setRentingParkingPlace(false);
                    }else {
                        sendTenantLetterForNotPayingForParkingPLace();
                        numOfDelayPayForParkingPlace++;
                        System.out.println("Tenat with name " + tenant.getName() + " got a letter about the parking place payment");
                        System.out.println("Tenat " + tenant.getName() + " has " + numOfDelayPayForParkingPlace + " delay pays for parking place for" + apartmentNumber + " apartment");
                    }
                }else {
                    System.out.println("Tenat with name " + tenant.getName() + " paid for the parking place in this month");
                }
            }
        }else{
            System.out.println("Noone is renting apartment " + apartmentNumber + ". It is free now!");
        }
    }

    public Date getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public Date getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public void sendTenantLetterForNotPaying(){
        TenantLetter tenantLetter = new TenantLetter("You didn't pay in time for " + apartmentNumber + " apartment.(Today's date is " + DateThread.currentDate +")", "NotPayingInTimeRent");
        tenant.setTenantLetter(tenantLetter);
    }

    public void sendTenantLetterForNotPayingForParkingPLace(){
        TenantLetter tenantLetter = new TenantLetter("You didn't pay in time for parking place for" + apartmentNumber + " apartment.(Today's date is " + DateThread.currentDate +")", "NotPayingInTimeParkingPlace");
        tenant.setTenantLetter(tenantLetter);
    }

    public void sendTenantLetterForKickingOut(){
        TenantLetter tenantLetter = new TenantLetter("You were kicked from " + apartmentNumber + " apartment.(Today's date is " + DateThread.currentDate +")", "KickingOut");
        tenant.setTenantLetter(tenantLetter);
    }

    public void sendTenantLetterForStopRentingParkingPlace(){
        TenantLetter tenantLetter = new TenantLetter("You are not longer renting a parking place for " + apartmentNumber + " apartment.(Today's date is " + DateThread.currentDate +")", "NoParkingPlace");
        tenant.setTenantLetter(tenantLetter);
    }

    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(ParkingPlace parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public void checkInPeople(Person person){
        if(people != null){
            if(people.size() == 0){
                try{
                    people.add(person);
                    person.setPersonApartment(this);
                    this.setTenant(person);
                    person.setTenant(true);
                    System.out.println("Added person " + person.getName() + " to apartment " + apartmentNumber + " and this person is a tenant for " + apartmentNumber + " apartment");
                }catch (ProblematicTenantException e){
                    System.out.println(e.getMessage());
                }
            }else {
                people.add(person);
                System.out.println("Added person " + person.getName() + " to apartment " + apartmentNumber);
            }
        }
    }

    public void checkOutPeople(Person person){
        if(this.getTenant().equals(person)){
            try{
                people.remove(person);
                person.getPersonApartments().remove(this);
                tenant=null;
                this.setTenant(people.get(0));
            }catch (ProblematicTenantException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Removed person " + person.getName() + " from apartment " + apartmentNumber + " and this person is no longer a tenant for " + apartmentNumber + " apartment");
            System.out.println("Now person " + people.get(0).getName() + " is a tenant for " + apartmentNumber + " apartment");
        }
        else{
            people.remove(person);
            System.out.println("Removed person " + person.getName() + " from apartment " + apartmentNumber);
        }
    }

    public void startRentingParkingPlace(){
        tenant.setRentingParkingPlace(true);
        long l = DateThread.currentDate.getTime();
        parkingPlace.getParkingPlaceRentStartDate().setTime(l);
        parkingPlace.getParkingPlaceRentEndDate().setTime(l + (2592000L * 1000));
        System.out.println("Start date of renting parking place for " + apartmentNumber + ": " + rentStartDate);
        System.out.println("End date of renting parking place for " + apartmentNumber + ": " + rentEndDate);
        System.out.println("Now " + tenant.getName() + " " + tenant.getSurname() + " is renting a parking place");
        TenantLetter tenantLetter = new TenantLetter("You started renting a parking place in apartment " + apartmentNumber + "(Today's date is " + DateThread.currentDate +")", "StartRentParkingPlace");
        tenant.setTenantLetter(tenantLetter);
    }

}

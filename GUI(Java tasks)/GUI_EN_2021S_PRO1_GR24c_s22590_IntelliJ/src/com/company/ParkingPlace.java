package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingPlace extends Room{
    private static int counter = 0;
    private int uniqueParkingPlaceIdentifier;
    private List<Item> items;
    private List<Vehicle> vehicles;
    private double currentVolume;
    private Date parkingPlaceRentStartDate = new Date();
    private Date parkingPlaceRentEndDate = new Date();

    public ParkingPlace(double length, double width, double height) {
        super(length, width, height);
        uniqueParkingPlaceIdentifier = ++counter;
        currentVolume = 0;
        items = new ArrayList<>();
        vehicles = new ArrayList<>();
    }

    public ParkingPlace(double volume) {
        super(volume);
        uniqueParkingPlaceIdentifier = ++counter;
        currentVolume = 0;
        items = new ArrayList<>();
        vehicles = new ArrayList<>();
    }

    public double getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(double currentVolume) {
        this.currentVolume = currentVolume;
    }



    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        ParkingPlace.counter = counter;
    }

    public int getUniqueParkingPlaceIdentifier() {
        return uniqueParkingPlaceIdentifier;
    }

    public void setUniqueParkingPlaceIdentifier(int uniqueParkingPlaceIdentifier) {
        this.uniqueParkingPlaceIdentifier = uniqueParkingPlaceIdentifier;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "ParkingPlace{" +
                "uniqueParkingPlaceIdentifier=" + uniqueParkingPlaceIdentifier +
                ", items=" + items +
                ", vehicles=" + vehicles +
                ", currentVolume=" + currentVolume +
                '}';
    }


    public void takeOutItemsToParkingPlace(Item item){
        items.remove(item);
        currentVolume-= item.getVolume();
        System.out.println("You take out item " + item.getName() + " from parking place");

    }

    public void takeOutVehiclesToParkingPlace(Vehicle vehicle){
        vehicles.remove(vehicle);
        currentVolume-= vehicle.getVolume();
        System.out.println("You take out vehicle " + vehicle.getName() + " from parking place");
    }

    public void insertItemsToParkingPlace(Item item) throws TooManyThingsException{
        if(item.getVolume() < this.getVolume() - currentVolume){
            items.add(item);
            currentVolume+= item.getVolume();
        } else throw new TooManyThingsException("Remove some old items to insert a new item");
    }

    public void insertVehiclesToParkingPlace(Vehicle vehicle) throws TooManyThingsException{
        if(vehicle.getVolume() < this.getVolume() - currentVolume){
            vehicles.add(vehicle);
            currentVolume+= vehicle.getVolume();
        } else throw new TooManyThingsException("Remove some old vehicles to insert a new vehicle");
    }

    public static List<Item> addAllVehicles(List<Item> oldItems, List<Vehicle> vehicleList){
        List<Item> newItems = oldItems;

        for (Vehicle vehicle : vehicleList) {
            newItems.add(vehicle);
        }

        return newItems;
    }

    public static List<Item> addAllItems(List<Item> oldItems, List<Item> itemList){
        List<Item> newItems = oldItems;

        for (Item item : itemList) {
            newItems.add(item);
        }

        return newItems;
    }

    public Date getParkingPlaceRentStartDate() {
        return parkingPlaceRentStartDate;
    }

    public void setParkingPlaceRentStartDate(Date parkingPlaceRentStartDate) {
        this.parkingPlaceRentStartDate = parkingPlaceRentStartDate;
    }

    public Date getParkingPlaceRentEndDate() {
        return parkingPlaceRentEndDate;
    }

    public void setParkingPlaceRentEndDate(Date parkingPlaceRentEndDate) {
        this.parkingPlaceRentEndDate = parkingPlaceRentEndDate;
    }
}

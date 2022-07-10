package com.company;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.List;


public class Main {
    public static int numOfSavedFilesType1 = 1;
    public static int numOfSavedFilesType2 = 1;

    public static void main(String[] args){
        Menu();
        new Thread(new DateThread()).start();

        ParkingPlace parkingPlace1 = new ParkingPlace(8.8, 5.5, 2);
        ParkingPlace parkingPlace2 = new ParkingPlace(150);
        ParkingPlace parkingPlace3 = new ParkingPlace(8, 4, 1.9);
        ParkingPlace parkingPlace4 = new ParkingPlace(250);
        ParkingPlace parkingPlace5 = new ParkingPlace(10, 4.4, 2.1);
        ParkingPlace parkingPlace6 = new ParkingPlace(144);
        ParkingPlace parkingPlace7 = new ParkingPlace(10.5, 4, 3);
        ParkingPlace parkingPlace8 = new ParkingPlace(300);

        Item item1 = new Item("Svitr", 1);
        Item item2 = new Item("Boshka", 2);
        Item item3 = new Item("Petro", 0.01);
        Item item4 = new Item("Bochka", 0.5);
        Item item5 = new Item("Sinhu Yni", 0.993);
        Item item6 = new Item("Mamus", 1);
        Item item7 = new Item("Papus", 1);
        Item item8 = new Item("Jero", 1);
        Item item9 = new Item("Bas", 2);
        Item item10 = new Item("Galepa", 1);
        Item item11 = new Item("Shatema", 1);
        Item item12 = new Item("Rapreka", 2);
        Item item13 = new Item("Lacesta", 0.01);
        Item item14 = new Item("Deloca", 0.5);
        Item item15 = new Item("Shtrudel", 0.993);
        Item item16 = new Item("Papuas", 1);
        Item item17 = new Item("Magnum", 1);
        Item item18 = new Item("Dimond", 1);
        Item item19 = new Item("Dimooon", 5);
        Item item20 = new Item("Pisof Blad", 1);
        Item item21 = new Item("Sopilka", 1);
        Item item22 = new Item("Lampa", 2);
        Item item23 = new Item("Computer", 0.01);
        Item item24 = new Item("Krichka", 0.5);
        Item item25 = new Item("Fridge", 5);
        Item item26 = new Item("Frazer", 1);

        try{
            parkingPlace1.insertItemsToParkingPlace(item1);
            parkingPlace1.insertItemsToParkingPlace(item2);
            parkingPlace1.insertItemsToParkingPlace(item3);
            parkingPlace2.insertItemsToParkingPlace(item4);
            parkingPlace2.insertItemsToParkingPlace(item5);
            parkingPlace2.insertItemsToParkingPlace(item6);
            parkingPlace3.insertItemsToParkingPlace(item7);
            parkingPlace3.insertItemsToParkingPlace(item8);
            parkingPlace3.insertItemsToParkingPlace(item9);
            parkingPlace4.insertItemsToParkingPlace(item10);
            parkingPlace4.insertItemsToParkingPlace(item11);
            parkingPlace4.insertItemsToParkingPlace(item12);
            parkingPlace5.insertItemsToParkingPlace(item13);
            parkingPlace5.insertItemsToParkingPlace(item14);
            parkingPlace5.insertItemsToParkingPlace(item15);
            parkingPlace6.insertItemsToParkingPlace(item16);
            parkingPlace6.insertItemsToParkingPlace(item17);
            parkingPlace6.insertItemsToParkingPlace(item18);
            parkingPlace7.insertItemsToParkingPlace(item19);
            parkingPlace7.insertItemsToParkingPlace(item20);
            parkingPlace7.insertItemsToParkingPlace(item21);
            parkingPlace8.insertItemsToParkingPlace(item22);
            parkingPlace8.insertItemsToParkingPlace(item23);
            parkingPlace8.insertItemsToParkingPlace(item24);
            parkingPlace8.insertItemsToParkingPlace(item25);
            parkingPlace8.insertItemsToParkingPlace(item26);
        }catch (TooManyThingsException e){
            System.out.println(e.getMessage());
        }

        CityCar cityCar1 = new CityCar("Lamborgini", 15, 2199, "Siniy", 1997, 2323);
        CityCar cityCar2 = new CityCar("Lamborgini", 12, 2399, "Purple", 2005, 2323);
        CityCar cityCar3 = new CityCar("Lamborgini", 13, 2299, "Red", 2009, 2323);
        CityCar cityCar4 = new CityCar("Lamborgini", 14, 1939, "Blue", 2015, 2323);
        CityCar cityCar5 = new CityCar("Lamborgini", 15, 2749, "Rainbow", 2021, 2323);

        Amphibious amphibious1 = new Amphibious("ArsenMobil", 22.1, 555, "Black", 2000, "RofloType");
        Amphibious amphibious2 = new Amphibious("BaduYan", 12.1, 666, "Black", 2002, "RofloType");
        Amphibious amphibious3 = new Amphibious("Je Re Mo", 13.1, 777, "Black", 2005, "RofloType");
        Amphibious amphibious4 = new Amphibious("Laplesa Akasa", 11.1, 888, "Black", 2006, "RofloType");
        Amphibious amphibious5 = new Amphibious("Mariro Vu Bebra", 12.1, 999, "Black", 2007, "RofloType");

        Boat boat1 = new Boat("Sonya", 12, 123, "White", 1992, 888);
        Boat boat2 = new Boat("Danya", 13, 123, "Blue", 1996, 222);
        Boat boat3 = new Boat("Vasya", 12, 123, "White", 1997, 199);
        Boat boat4 = new Boat("Planeta", 11, 123, "Purple", 2002, 500);
        Boat boat5 = new Boat("Krim", 14, 123, "Black", 2009, 400);

        Motorcycle motorcycle1 = new Motorcycle("Jan", 11, 123, "Black", 2012, 888);
        Motorcycle motorcycle2 = new Motorcycle("Amotorasu", 12, 123, "White", 2015, 666);
        Motorcycle motorcycle3 = new Motorcycle("Demon", 15, 123, "Purple", 2016, 1337);
        Motorcycle motorcycle4 = new Motorcycle("SopilkaCycle", 13, 123, "Green", 2017, 488);
        Motorcycle motorcycle5 = new Motorcycle("MarquizyBro", 14, 123, "Yellow", 2018, 1307);

        OffRoadCar offRoadCar1 = new OffRoadCar("OffnikCar", 11, 2882, "White", 2002, 40);
        OffRoadCar offRoadCar2 = new OffRoadCar("OffnikMobile", 21, 1448, "Black", 2006, 50);
        OffRoadCar offRoadCar3 = new OffRoadCar("Je Pressa", 11, 1337, "Red", 2007, 35);
        OffRoadCar offRoadCar4 = new OffRoadCar("LebuChelli", 11, 5000, "Purple", 2012, 33);
        OffRoadCar offRoadCar5 = new OffRoadCar("Sha Ue Bu", 14, 5400, "Black", 2022, 25);

        try {
            parkingPlace1.insertVehiclesToParkingPlace(cityCar1);
            parkingPlace1.insertVehiclesToParkingPlace(boat1);
            parkingPlace1.insertVehiclesToParkingPlace(motorcycle1);
            parkingPlace1.insertVehiclesToParkingPlace(offRoadCar1);
            parkingPlace2.insertVehiclesToParkingPlace(amphibious1);
            parkingPlace2.insertVehiclesToParkingPlace(amphibious2);
            parkingPlace2.insertVehiclesToParkingPlace(amphibious3);
            parkingPlace3.insertVehiclesToParkingPlace(amphibious4);
            parkingPlace3.insertVehiclesToParkingPlace(amphibious5);
            parkingPlace3.insertVehiclesToParkingPlace(offRoadCar2);
            parkingPlace4.insertVehiclesToParkingPlace(motorcycle2);
            parkingPlace4.insertVehiclesToParkingPlace(motorcycle3);
            parkingPlace4.insertVehiclesToParkingPlace(motorcycle4);
            parkingPlace5.insertVehiclesToParkingPlace(offRoadCar3);
            parkingPlace5.insertVehiclesToParkingPlace(offRoadCar4);
            parkingPlace5.insertVehiclesToParkingPlace(offRoadCar5);
            parkingPlace6.insertVehiclesToParkingPlace(boat2);
            parkingPlace6.insertVehiclesToParkingPlace(boat3);
            parkingPlace6.insertVehiclesToParkingPlace(boat4);
            parkingPlace7.insertVehiclesToParkingPlace(cityCar2);
            parkingPlace7.insertVehiclesToParkingPlace(cityCar3);
            parkingPlace7.insertVehiclesToParkingPlace(cityCar4);
            parkingPlace7.insertVehiclesToParkingPlace(cityCar5);
            parkingPlace8.insertVehiclesToParkingPlace(boat5);
            parkingPlace8.insertVehiclesToParkingPlace(motorcycle5);

        }catch (TooManyThingsException e){
            System.out.println(e.getMessage());
        }

        List<ParkingPlace> parkingPlaces1 = new ArrayList<>();
        List<ParkingPlace> parkingPlaces2 = new ArrayList<>();
        List<ParkingPlace> parkingPlaces3 = new ArrayList<>();
        List<ParkingPlace> parkingPlaces4 = new ArrayList<>();
        List<ParkingPlace> parkingPlaces5 = new ArrayList<>();
        List<ParkingPlace> parkingPlaces6 = new ArrayList<>();
        List<ParkingPlace> parkingPlaces7 = new ArrayList<>();
        List<ParkingPlace> parkingPlaces8 = new ArrayList<>();

        parkingPlaces1.add(parkingPlace1);
        parkingPlaces2.add(parkingPlace2);
        parkingPlaces3.add(parkingPlace3);
        parkingPlaces4.add(parkingPlace4);
        parkingPlaces5.add(parkingPlace5);
        parkingPlaces6.add(parkingPlace6);
        parkingPlaces7.add(parkingPlace7);
        parkingPlaces8.add(parkingPlace8);

        Person person1 = new Person("Alex", "Marquizy", "02477849302", "Saint Volodymr street 34A");
        Person person2 = new Person("Vasya", "Bonishuk", "01373859302", "King Piotr street 22B");
        Person person3 = new Person("Maria", "Ivanova", "11384953761", "Volohotskaya street 9A");
        Person person4 = new Person("Olha", "Shuliak", "02341748393", "Pravda street 77D");
        Person person5 = new Person("Mark", "Klim", "21348586679", "Alex Bilinski street 111A");
        Person person6 = new Person("Vasya", "Klochkov", "09347588911", "Nebesov street 12A");
        Person person7 = new Person("Kolia", "Sidorov", "02324457892", "Klochko street 226A");
        Person person8 = new Person("Petya", "Petrov", "00131488953", "Alex Bilinski street 88C");
        Person person9 = new Person("Masha", "Podol", "03647582932", "Borunov street 77A");
        Person person10 = new Person("Sasha", "Herov", "02838485965", "Happy street 443B");
        Person person11 = new Person("Pushkin", "Marquizy", "02477849302", "Saint Volodymr street 34A");
        Person person12 = new Person("Sopilka", "Bonishuk", "01373859302", "King Piotr street 22B");
        Person person13 = new Person("Grioriy", "Grigoryan", "11384953761", "Volohotskaya street 9A");
        Person person14 = new Person("Eldjay", "Boshki", "02341748393", "Pravda street 77D");
        Person person15 = new Person("Vasiliy", "Vodniy", "21348586679", "Alex Bilinski street 111A");
        Person person16 = new Person("Jeka", "Tarakan", "09347588911", "Nebesov street 12A");
        Person person17 = new Person("Natasha", "Gvozd", "02324457892", "Klochko street 226A");
        Person person18 = new Person("Genadiy", "Smirnov", "00131488953", "Alex Bilinski street 88C");
        Person person19 = new Person("Emil", "Tuhliy", "03647582932", "Ulica zalupnosti 77A");
        Person person20 = new Person("Radik", "Uvilichenko", "02838485965", "Happy street 443B");

        List<Person> allPeople = new ArrayList<>();
        allPeople.add(person1);
        allPeople.add(person2);
        allPeople.add(person3);
        allPeople.add(person4);
        allPeople.add(person5);
        allPeople.add(person6);
        allPeople.add(person7);
        allPeople.add(person8);
        allPeople.add(person9);
        allPeople.add(person10);
        allPeople.add(person11);
        allPeople.add(person12);
        allPeople.add(person13);
        allPeople.add(person14);
        allPeople.add(person15);
        allPeople.add(person16);
        allPeople.add(person17);
        allPeople.add(person18);
        allPeople.add(person19);
        allPeople.add(person20);

        Apartment apartment1 = new Apartment("209B",500, parkingPlace1);
        Apartment apartment2 = new Apartment("512A",15, 10.5, 3.2, parkingPlace2);
        Apartment apartment3 = new Apartment("23D",400, parkingPlace3);
        Apartment apartment4 = new Apartment("111C",30, 40, 3.3, parkingPlace4);
        Apartment apartment5 = new Apartment("993A",22, 32.5, 2.9, parkingPlace5);
        Apartment apartment6 = new Apartment("221E",180, parkingPlace6);
        Apartment apartment7 = new Apartment("13A",10, 3, 3, parkingPlace7);
        Apartment apartment8 = new Apartment("4B",320, parkingPlace8);

        apartment1.checkInPeople(person1);
        apartment1.checkInPeople(person2);
        apartment1.checkInPeople(person3);
        apartment2.checkInPeople(person4);
        apartment3.checkInPeople(person5);
        apartment3.checkInPeople(person6);
        apartment4.checkInPeople(person7);
        apartment4.checkInPeople(person8);
        apartment4.checkInPeople(person9);
        apartment4.checkInPeople(person10);
        apartment5.checkInPeople(person11);
        apartment5.checkInPeople(person12);
        apartment6.checkInPeople(person13);
        apartment6.checkInPeople(person14);
        apartment6.checkInPeople(person15);
        apartment7.checkInPeople(person16);
        apartment8.checkInPeople(person17);
        apartment8.checkInPeople(person18);
        apartment8.checkInPeople(person19);
        apartment8.checkInPeople(person20);

        person1.setParkingPlaces(parkingPlaces1);
        person2.setParkingPlaces(parkingPlaces1);
        person3.setParkingPlaces(parkingPlaces1);
        person4.setParkingPlaces(parkingPlaces2);
        person5.setParkingPlaces(parkingPlaces3);
        person6.setParkingPlaces(parkingPlaces3);
        person7.setParkingPlaces(parkingPlaces4);
        person8.setParkingPlaces(parkingPlaces4);
        person9.setParkingPlaces(parkingPlaces4);
        person10.setParkingPlaces(parkingPlaces4);
        person11.setParkingPlaces(parkingPlaces5);
        person12.setParkingPlaces(parkingPlaces5);
        person13.setParkingPlaces(parkingPlaces6);
        person14.setParkingPlaces(parkingPlaces6);
        person15.setParkingPlaces(parkingPlaces6);
        person16.setParkingPlaces(parkingPlaces7);
        person17.setParkingPlaces(parkingPlaces8);
        person18.setParkingPlaces(parkingPlaces8);
        person19.setParkingPlaces(parkingPlaces8);
        person20.setParkingPlaces(parkingPlaces8);

        for (Person person : allPeople) {
            if(person.isTenant()){
                for (int i = 0; i < person.getTenatApartments().size(); i++) {
                    person.getTenatApartments().get(i).startRentingParkingPlace();
                }
            }
        }


        List<Apartment> apartmentList1 = new ArrayList<>();
        apartmentList1.add(apartment1);
        apartmentList1.add(apartment2);
        apartmentList1.add(apartment3);
        apartmentList1.add(apartment4);

        List<Apartment> apartmentList2 = new ArrayList<>();
        apartmentList2.add(apartment5);
        apartmentList2.add(apartment6);
        apartmentList2.add(apartment7);
        apartmentList2.add(apartment8);

        HousingEstate housingEstate1 = new HousingEstate(apartmentList1);
        HousingEstate housingEstate2 = new HousingEstate(apartmentList2);

        List<HousingEstate> housingEstateList = new ArrayList<>();
        housingEstateList.add(housingEstate1);
        housingEstateList.add(housingEstate2);
        Developer developer = new Developer("Jhon", "Will", housingEstateList);


        Thread checkRoomT = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                        for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                            for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                                Apartment currentAppartment = developer.getHousingEstates().get(i).getApartments().get(j);
                                currentAppartment.checkForPayment();
                            }
                        }

                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        checkRoomT.start();

        while (true){
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.next();

            switch (userChoice){
                case "0"->{
                    System.out.println("Good Bye! See you later!");
                    System.exit(0);
                }
                case "1"->{
                    boolean isExist = false;
                    System.out.println("Enter the name of person: ");
                    String name = scanner.next();
                    System.out.println("Enter the surname of person: ");
                    String surname = scanner.next();
                    System.out.println("Enter the pesel number of person: ");
                    String peselNumber = scanner.next();
                    System.out.println("Enter the address of person: ");
                    String address = scanner.next();

                    Person newPerson = new Person(name, surname, peselNumber, address);

                    System.out.println("Enter the number of apartment to where you want to add this person: ");
                    String apartmentNum = scanner.next();

                    for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                        for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                            if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                developer.getHousingEstates().get(i).getApartments().get(j).checkInPeople(newPerson);
                                isExist = true;
                                allPeople.add(newPerson);
                                System.out.println("You successfully added person " + name + " to the " + apartmentNum + " apartment");
                            }
                        }
                    }
                    if(!isExist){
                        System.out.println("You entered non-existent apartment number!");
                    }
                }
                case "2"->{
                    boolean isExistPerson = false;
                    boolean isExistApartment = false;
                    System.out.println("Enter the name of person that you want to delete from apartment: ");
                    String name = scanner.next();
                    System.out.println("Enter the surname of person that you want to delete from apartment: ");
                    String surname = scanner.next();
                    System.out.println("Enter the number of apartment from where you want to delete this person: ");
                    String apartmentNum = scanner.next();

                    for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                        for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                            if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                isExistApartment = true;
                                for (int k = 0; k < developer.getHousingEstates().get(i).getApartments().get(j).getPeople().size(); k++) {
                                    if(developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k).getName().equals(name) && developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k).getSurname().equals(surname)){
                                        developer.getHousingEstates().get(i).getApartments().get(j).checkOutPeople(developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k));
                                        isExistPerson = true;
                                    }
                                }
                            }
                        }
                    }
                    if(!isExistApartment){
                        System.out.println("You entered non-existent apartment number!");
                    }else {
                        if(!isExistPerson){
                            System.out.println("This person doesn't exist in " + apartmentNum + " apartment!");
                        }
                    }
                }
                case "3"->{
                    boolean isExistApartment = false;
                    System.out.println("Enter the apartment number you want to pay for: ");
                    String apartmentNum = scanner.next();
                    for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                        for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                            if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                developer.getHousingEstates().get(i).getApartments().get(j).payForRent();
                                isExistApartment = true;
                            }
                        }
                    }
                    if(!isExistApartment){
                        System.out.println("You entered non-existent apartment number!");
                    }
                }
                case "4"->{
                    boolean isExistApartment = false;
                    System.out.println("Enter the number of the apartment you want to refuse to rent");
                    String apartmentNum = scanner.next();

                    for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                        for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                            if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                developer.getHousingEstates().get(i).getApartments().get(j).cancelRenting();
                                isExistApartment = true;
                            }
                        }
                    }

                    if(!isExistApartment){
                        System.out.println("You entered non-existent apartment number!");
                    }
                }
                case "5"->{
                    boolean isExistApartment = false;
                    boolean isRentingParkingPlace = false;
                    System.out.println("Enter name of your item: ");
                    String itemName = scanner.next();
                    System.out.println("Enter volume of your item: ");
                    double itemVolume = scanner.nextDouble();
                    Item userItem = new Item(itemName, itemVolume);
                    System.out.println("Enter an apartment number to what parking place you want to insert an item: ");
                    String apartmentNum = scanner.next();
                    for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                        for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                            if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                isExistApartment = true;
                                if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().isRentingParkingPlace()){
                                    isRentingParkingPlace = true;
                                    try {
                                        developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().insertItemsToParkingPlace(userItem);
                                        System.out.println("Item successfully inserted!");
                                    }catch (TooManyThingsException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                        }
                    }

                    if(!isExistApartment){
                        System.out.println("You entered non-existent apartment number!");
                    }else {
                        if(!isRentingParkingPlace){
                            System.out.println("You don't rent a parking place for this apartment!");
                        }
                    }

                }
                case "6"->{
                    boolean isExistApartment = false;
                    boolean isRentingParkingPlace = false;
                    System.out.println("What vehicle do you want to create?");
                    System.out.println("1. Amphibious");
                    System.out.println("2. Boat");
                    System.out.println("3. Motorcycle");
                    System.out.println("4. Off-road car");
                    System.out.println("5. City car");
                    int vehicleType = scanner.nextInt();
                    if(vehicleType > 5 || vehicleType < 1){
                        System.out.println("Enter value from 1 to 5!");
                    }else{
                        System.out.println("Enter name of your vehicle: ");
                        String vehicleName = scanner.next();
                        System.out.println("Enter volume of your vehicle: ");
                        double vehicleVolume = scanner.nextDouble();
                        System.out.println("Enter weight of your vehicle: ");
                        int vehicleWeightKilo = scanner.nextInt();
                        System.out.println("Enter color of your vehicle: ");
                        String vehicleColor = scanner.next();
                        System.out.println("Enter year of production of your vehicle: ");
                        int vehicleYearOfProduction = scanner.nextInt();

                        switch (vehicleType){
                            case 1->{
                                System.out.println("Enter engine type: ");
                                String engineType = scanner.next();
                                Amphibious amphibious = new Amphibious(vehicleName, vehicleVolume, vehicleWeightKilo, vehicleColor, vehicleYearOfProduction, engineType);
                                System.out.println("Enter an apartment number to what parking place you want to insert an vehicle: ");
                                String apartmentNum = scanner.next();
                                for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                                    for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                                        if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                            isExistApartment = true;
                                            if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().isRentingParkingPlace()){
                                                isRentingParkingPlace = true;
                                                try {
                                                    developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().insertVehiclesToParkingPlace(amphibious);
                                                    System.out.println("Vehicle successfully inserted!");
                                                }catch (TooManyThingsException e){
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                         }
                                    }
                                }
                            }
                            case 2->{
                                System.out.println("Enter motor power: ");
                                int motorPower = scanner.nextInt();
                                Boat boat = new Boat(vehicleName, vehicleVolume, vehicleWeightKilo, vehicleColor, vehicleYearOfProduction, motorPower);
                                System.out.println("Enter an apartment number to what parking place you want to insert an vehicle: ");
                                String apartmentNum = scanner.next();
                                for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                                    for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                                        if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                            isExistApartment = true;
                                            if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().isRentingParkingPlace()) {
                                                isRentingParkingPlace = true;
                                                try {
                                                    developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().insertVehiclesToParkingPlace(boat);
                                                    System.out.println("Vehicle successfully inserted!");
                                                }catch (TooManyThingsException e){
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            case 3->{
                                System.out.println("Enter max speed: ");
                                int maxSpeed = scanner.nextInt();
                                Motorcycle motorcycle = new Motorcycle(vehicleName, vehicleVolume, vehicleWeightKilo, vehicleColor, vehicleYearOfProduction, maxSpeed);
                                System.out.println("Enter an apartment number to what parking place you want to insert an vehicle: ");
                                String apartmentNum = scanner.next();
                                for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                                    for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                                        if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                            isExistApartment = true;
                                            if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().isRentingParkingPlace()) {
                                                isRentingParkingPlace = true;
                                                try {
                                                    developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().insertVehiclesToParkingPlace(motorcycle);
                                                    System.out.println("Vehicle successfully inserted!");
                                                }catch (TooManyThingsException e){
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            case 4->{
                                System.out.println("Enter trunk volume: ");
                                double trunkVolume = scanner.nextDouble();
                                OffRoadCar offRoadCar = new OffRoadCar(vehicleName, vehicleVolume, vehicleWeightKilo, vehicleColor, vehicleYearOfProduction, trunkVolume);
                                System.out.println("Enter an apartment number to what parking place you want to insert an vehicle: ");
                                String apartmentNum = scanner.next();
                                for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                                    for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                                        if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                            isExistApartment = true;
                                            if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().isRentingParkingPlace()) {
                                                isRentingParkingPlace = true;
                                                try {
                                                    developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().insertVehiclesToParkingPlace(offRoadCar);
                                                    System.out.println("Vehicle successfully inserted!");
                                                }catch (TooManyThingsException e){
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            case 5->{
                                System.out.println("Enter engine volume: ");
                                double engineVolume = scanner.nextDouble();
                                CityCar cityCar = new CityCar(vehicleName, vehicleVolume, vehicleWeightKilo, vehicleColor, vehicleYearOfProduction, engineVolume);
                                System.out.println("Enter an apartment number to what parking place you want to insert an vehicle: ");
                                String apartmentNum = scanner.next();
                                for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                                    for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                                        if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                            isExistApartment = true;
                                            if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().isRentingParkingPlace()) {
                                                isRentingParkingPlace = true;
                                                try {
                                                    developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().insertVehiclesToParkingPlace(cityCar);
                                                    System.out.println("Vehicle successfully inserted!");
                                                }catch (TooManyThingsException e){
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(!isExistApartment){
                        System.out.println("You entered non-existent apartment number!");
                    }else {
                        if(!isRentingParkingPlace){
                            System.out.println("You don't rent a parking place for this apartment!");
                        }
                    }
                }
                case "7"->{
                    boolean isRentingParkingPlace = false;
                    boolean isExistApartment = false;
                    boolean isItem = false;
                    System.out.println("Enter an apartment number from what parking place you want to take out an item: ");
                    String apartmentNum = scanner.next();
                    System.out.println("Enter name of the item: ");
                    String itemName = scanner.next();
                    for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                        for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                            if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                isExistApartment = true;
                                if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().isRentingParkingPlace()) {
                                    isRentingParkingPlace = true;
                                    for (int k = 0; k < developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getItems().size(); k++) {
                                        if(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getItems().get(k).getName().equals(itemName)){
                                            developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().takeOutItemsToParkingPlace(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getItems().get(k));
                                            isItem = true;
                                            System.out.println("Item successfully took out!");
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(!isExistApartment){
                        System.out.println("You entered non-existent apartment number!");
                    }else {
                        if(!isRentingParkingPlace){
                            System.out.println("You don't rent a parking place for this apartment!");
                        }else {
                            if(!isItem){
                                System.out.println("You entered non-existent item number!");
                            }
                        }
                    }
                }
                case "8"->{
                    boolean isRentingParkingPlace = false;
                    boolean isExistApartment = false;
                    boolean isVehicle = false;
                    System.out.println("Enter an apartment number from what parking place you want to take out a vehicle: ");
                    String apartmentNum = scanner.next();
                    System.out.println("Enter name of the vehicle: ");
                    String vehicleName = scanner.next();
                    for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                        for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                            if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                isExistApartment = true;
                                if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().isRentingParkingPlace()) {
                                    isRentingParkingPlace = true;
                                    for (int k = 0; k < developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVehicles().size(); k++) {
                                        if(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVehicles().get(k).getName().equals(vehicleName)){
                                            developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().takeOutVehiclesToParkingPlace(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVehicles().get(k));
                                            isVehicle = true;
                                            System.out.println("Vehicle successfully took out!");
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(!isExistApartment){
                        System.out.println("You entered non-existent apartment number!");
                    }else {
                        if(!isRentingParkingPlace){
                            System.out.println("You don't rent a parking place for this apartment!");
                        }else {
                            if (!isVehicle) {
                                System.out.println("You entered non-existent vehicle number!");
                            }
                        }
                    }
                }
                case "9"->{
                    try {
                        SaveToFile(developer, allPeople, numOfSavedFilesType1);
                        numOfSavedFilesType1++;
                    }catch (FileNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "10"->{
                    try {
                        NewSaveToFile(developer, allPeople, numOfSavedFilesType2);
                        numOfSavedFilesType2++;
                    }catch (FileNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "11"->{
                    boolean isExistPerson = false;
                    boolean isExistApartment = false;
                    System.out.println("Enter the name of person that you want to add to apartment: ");
                    String name = scanner.next();
                    System.out.println("Enter the surname of person that you want to add to apartment: ");
                    String surname = scanner.next();
                    System.out.println("Enter the number of apartment to where you want to add this person: ");
                    String apartmentNum = scanner.next();

                    for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                        for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                            if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                isExistApartment = true;
                                for (int k = 0; k < allPeople.size(); k++) {
                                    if(allPeople.get(k).getName().equals(name) && allPeople.get(k).getSurname().equals(surname)){
                                        developer.getHousingEstates().get(i).getApartments().get(j).checkInPeople(allPeople.get(k));
                                        isExistPerson = true;
                                    }
                                }
                            }
                        }
                    }
                    if(!isExistApartment){
                        System.out.println("You entered non-existent apartment number!");
                    }else {
                        if(!isExistPerson){
                            System.out.println("This person doesn't exist in " + apartmentNum + " apartment!");
                        }
                    }
                }
                case "12"->{
                    boolean isExistApartment = false;
                    System.out.println("Enter the number of apartment you want to start renting parking place: ");
                    String apartmentNum = scanner.next();

                    for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                        for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                            if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                isExistApartment = true;
                                developer.getHousingEstates().get(i).getApartments().get(j).startRentingParkingPlace();
                            }
                        }
                    }
                    if(!isExistApartment){
                        System.out.println("You entered non-existent apartment number!");
                    }
                }
                case "13"->{
                    boolean isExistApartment = false;
                    boolean isExistParkingPlace = false;
                    System.out.println("Enter the apartment number where do you live, to pay for parking place: ");
                    String apartmentNum = scanner.next();
                    for (int i = 0; i < developer.getHousingEstates().size(); i++) {
                        for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                            if(developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber().equals(apartmentNum)){
                                isExistApartment = true;
                                if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().isRentingParkingPlace()){
                                    isExistParkingPlace = true;
                                    developer.getHousingEstates().get(i).getApartments().get(j).payForParkingPlace();
                                }
                            }
                        }
                    }
                    if(!isExistApartment){
                        System.out.println("You entered non-existent apartment number!");
                    }else if(!isExistParkingPlace){
                        System.out.println("You don't have a parking place for this apartment!");
                    }
                }
                default -> {
                    System.out.println("Something went wrong! Try to write 0-9");
                }
            }
        }
    }

    public static void Menu(){
        System.out.println("Menu: ");
        System.out.println("Press 0 to exit:");
        System.out.println("Press 1 to create and add person into the apartment");
        System.out.println("Press 2 to delete person from the apartment");
        System.out.println("Press 3 to pay for the rent");
        System.out.println("Press 4 to cancel the rent");
        System.out.println("Press 5 to create and insert a item");
        System.out.println("Press 6 to create and insert a vehicle");
        System.out.println("Press 7 to take out a item");
        System.out.println("Press 8 to take out a vehicle");
        System.out.println("Press 9 to SAVE DATA TO THE FILE(1Type)");
        System.out.println("Press 10 to SAVE DATA TO THE FILE(2Type)");
        System.out.println("Press 11 to add person into the apartment");
        System.out.println("Press 12 to start renting the parking place");
        System.out.println("Press 13 to pay for the parking place");
    }

    public static void SaveToFile(Developer developer, List<Person> allPeople, int numOfSavedFiles) throws FileNotFoundException {
        String filePath = "SavedInfo(1Type)"+ numOfSavedFiles +".txt";
        File file = new File(filePath);
        PrintWriter pw = new PrintWriter(file);
        for (int i = 0; i < developer.getHousingEstates().size(); i++) {
            pw.println("----------------------------------------------------HOUSING ESTATE "+ i +" ----------------------------------------------------");
            for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                Collections.sort(developer.getHousingEstates().get(i).getApartments(), new Comparator<Apartment>() {
                    public int compare(Apartment apartment1, Apartment apartment2) {
                        return (int)(apartment1.getVolume() - apartment2.getVolume());
                    }
                });

                pw.println("Apartment " + developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber() + " volume: " + developer.getHousingEstates().get(i).getApartments().get(j).getVolume());
                if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant() != null){
                    pw.println("Tenant for " + developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber() + " is "+ developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getName() + " " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getSurname());
                    pw.println("People who are living in " + developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber()+":");
                    for (int k = 0; k < developer.getHousingEstates().get(i).getApartments().get(j).getPeople().size(); k++) {
                        pw.println(developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k).getName() + " " + developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k).getSurname() + " with pesel number: " + developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k).getPeselNumber() + " and address: " + developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k).getAddress());
                    }
                    pw.println("Rent started in: " + developer.getHousingEstates().get(i).getApartments().get(j).getRentStartDate());
                    pw.println("Rent ends in: " + developer.getHousingEstates().get(i).getApartments().get(j).getRentEndDate());
                    pw.println("Parking place volume for " + developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber() + " apartment is: " + Math.ceil(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getCurrentVolume()) + " m3 / " + Math.ceil(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVolume()) + " m3(max)");
                    pw.println("Items and vehicles in this parking place: ");

                    List<Item> itemList = new ArrayList<>();

                    ParkingPlace.addAllItems(itemList, developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getItems());
                    ParkingPlace.addAllVehicles(itemList, developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVehicles());

                    Collections.sort(itemList, new Comparator<Item>() {
                        public int compare(Item item1, Item item2) {
                            if(item2.getVolume() - item1.getVolume() != 0){
                                return (int)(item2.getVolume() - item1.getVolume());
                            }else {
                                return (item2.getName().compareTo(item1.getName()));
                            }
                        }
                    });

                    for (int m = 0; m < itemList.size(); m++) {
                        pw.println(m+1 + ". " + itemList.get(m).getName() + " with volume: " + itemList.get(m).getVolume());
                    }
                    if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getTenantLetters().size() != 0){
                        pw.println("Tenant letters for " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getName() + " " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getSurname() + ": ");
                        for (int k = 0; k < developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getTenantLetters().size(); k++) {
                            pw.println(k+1 + ". " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getTenantLetters().get(k).getLetter());
                        }
                    }else {
                        pw.println("Tenant " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getName() + " " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getSurname() + " doesn't have any tenant letters");
                    }
                }else {
                    pw.println("This apartments doesn't have any tenant");
                    pw.println("Nobody is living in this apartment");
                    pw.println("Now this apartment is not renting");
                    pw.println("Parking place volume for " + developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber() + " apartment is: " + Math.ceil(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getCurrentVolume()) + " m3 / " + Math.ceil(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVolume()) + " m3(max)");
                    pw.println("No items and vehicles in this parking place");
                }
                pw.println("");
            }
        }
        pw.println("----------------------------------------------------ALL TENANT LETTERS INFO----------------------------------------------------");
        for (int k = 0; k < allPeople.size(); k++) {
            if(allPeople.get(k).getTenantLetters().size() != 0){
                pw.println("Tenant letters for " + allPeople.get(k).getName() + " " + allPeople.get(k).getSurname() + ": ");
                for (int z = 0; z < allPeople.get(k).getTenantLetters().size(); z++) {
                    pw.println(z+1 + ". " + allPeople.get(k).getTenantLetters().get(z).getLetter());
                }
            }else {
                pw.println("Tenant " + allPeople.get(k).getName() + " " + allPeople.get(k).getSurname() + " doesn't have any tenant letters");
            }
            pw.println("");
        }
        pw.println("----------------------------------------------------ADDITIONAL INFO----------------------------------------------------");
        pw.println("Number of housing estates: " + developer.getHousingEstates().size());
        int numOfAllPeopleInEstates = 0;
        int numOfAllApartments = 0;
        int numOfAllItems = 0;
        int numOfAllVehicles = 0;
        for (int i = 0; i < developer.getHousingEstates().size() ; i++) {
            numOfAllApartments += developer.getHousingEstates().get(i).getApartments().size();
            for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                numOfAllPeopleInEstates += developer.getHousingEstates().get(i).getApartments().get(j).getPeople().size();
                numOfAllItems += developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getItems().size();
                numOfAllVehicles += developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVehicles().size();
            }
        }
        pw.println("Number of all apartments in housing estates: " + numOfAllApartments);
        pw.println("Number of all people who lives in housing estates: " + numOfAllPeopleInEstates);
        pw.println("Number of all items stored in housing estates: " + numOfAllItems);
        pw.println("Number of all vehicles stored in housing estates: " + numOfAllVehicles);
        pw.println("");
        for (int k = 0; k < developer.getHousingEstates().size(); k++) {
            pw.println("Number of all apartments in housing estate " + (k+1) + ": " + developer.getHousingEstates().get(k).getApartments().size());
            int numOfFreeApp = 0;
            int numOfOccupiedApp = 0;
            int numOfPeopleInEstate = 0;
            int numOfItems = 0;
            int numOfVehicles = 0;
            for (int i = 0; i < developer.getHousingEstates().get(k).getApartments().size(); i++) {
                if(developer.getHousingEstates().get(k).getApartments().get(i).getTenant() == null){
                    numOfFreeApp++;
                }else {
                    numOfOccupiedApp++;
                }
            }
            for (int i = 0; i < developer.getHousingEstates().get(k).getApartments().size(); i++) {
                numOfPeopleInEstate += developer.getHousingEstates().get(k).getApartments().get(i).getPeople().size();
                numOfItems += developer.getHousingEstates().get(k).getApartments().get(i).getParkingPlace().getItems().size();
                numOfVehicles += developer.getHousingEstates().get(k).getApartments().get(i).getParkingPlace().getVehicles().size();
            }
            pw.println("Number of free apartments in housing estate " + (k+1) + ": " + numOfFreeApp);
            pw.println("Number of occupied apartments in housing estate " + (k+1) + ": " + numOfOccupiedApp);
            pw.println("Number of people who lives in housing estate " + (k+1) + ": " + numOfPeopleInEstate);
            pw.println("Number of items stored in housing estate " + (k+1) + ": " + numOfItems);
            pw.println("Number of all vehicles stored in housing estates " + (k+1) + ": " + numOfVehicles);
            pw.println("");
        }
        pw.close();
        System.out.println("File has been successfully written");
    }

    public static void NewSaveToFile(Developer developer, List<Person> allPeople, int numOfSavedFiles)throws FileNotFoundException{
        String filePath = "SavedInfo(2Type)"+ numOfSavedFiles +".txt";
        File file = new File(filePath);
        PrintWriter pw = new PrintWriter(file);
        for (int i = 0; i < developer.getHousingEstates().size(); i++) {
            pw.println("----------------------------------------------------HOUSING ESTATE "+ (i+1) +" ----------------------------------------------------");
            for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                Collections.sort(developer.getHousingEstates().get(i).getApartments(), new Comparator<Apartment>() {
                    public int compare(Apartment apartment1, Apartment apartment2) {
                        return (int)(apartment1.getVolume() - apartment2.getVolume());
                    }
                });

                pw.println("Apartment " + developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber() + " volume: " + developer.getHousingEstates().get(i).getApartments().get(j).getVolume());
                if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant() != null){
                    pw.println("Tenant for " + developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber() + " is "+ developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getName() + " " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getSurname());
                    pw.println("People who are living in " + developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber()+":");
                    for (int k = 0; k < developer.getHousingEstates().get(i).getApartments().get(j).getPeople().size(); k++) {
                        pw.println(developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k).getName() + " " + developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k).getSurname() + " with pesel number: " + developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k).getPeselNumber() + " and address: " + developer.getHousingEstates().get(i).getApartments().get(j).getPeople().get(k).getAddress());
                    }
                    pw.println("Rent started in: " + developer.getHousingEstates().get(i).getApartments().get(j).getRentStartDate());
                    pw.println("Rent ends in: " + developer.getHousingEstates().get(i).getApartments().get(j).getRentEndDate());
                    pw.println("Parking place volume for " + developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber() + " apartment is: " + Math.ceil(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getCurrentVolume()) + " m3 / " + Math.ceil(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVolume()) + " m3(max)");

                    Collections.sort(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVehicles(), new Comparator<Item>() {
                        public int compare(Item vehicle1, Item vehicle2) {
                            if(vehicle1.getVolume() - vehicle2.getVolume() != 0){
                                return (int)(vehicle2.getVolume() - vehicle1.getVolume());
                            }else {
                                return (vehicle2.getName().compareTo(vehicle1.getName()));
                            }
                        }
                    });

                    Collections.sort(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getItems(), new Comparator<Item>() {
                        public int compare(Item item1, Item item2) {
                            if(item1.getVolume() - item2.getVolume() != 0){
                                return (int)(item2.getVolume() - item1.getVolume());
                            }else {
                                return (item2.getName().compareTo(item1.getName()));
                            }
                        }
                    });
                    pw.println("Vehicles in this parking place: ");
                    for (int k = 0; k < developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVehicles().size(); k++) {
                        pw.println(k+1 + ". " + developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVehicles().get(k).toString().replace("[", "").replace("]", ""));
                    }

                    pw.println("Items in this parking place: ");
                    for (int k = 0; k < developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getItems().size(); k++) {
                        pw.println(k+1 + ". " + developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getItems().get(k).toString().replace("[", "").replace("]", ""));
                    }

                    if(developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getTenantLetters().size() != 0){
                        pw.println("Tenant letters for " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getName() + " " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getSurname() + ": ");
                        for (int k = 0; k < developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getTenantLetters().size(); k++) {
                            pw.println(k+1 + ". " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getTenantLetters().get(k).getLetter());
                        }
                    }else {
                        pw.println("Tenant " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getName() + " " + developer.getHousingEstates().get(i).getApartments().get(j).getTenant().getSurname() + " doesn't have any tenant letters");
                    }
                }else {
                    pw.println("This apartments doesn't have any tenant");
                    pw.println("Nobody is living in this apartment");
                    pw.println("Now this apartment is not renting");
                    pw.println("Parking place volume for " + developer.getHousingEstates().get(i).getApartments().get(j).getApartmentNumber() + " apartment is: " + Math.ceil(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getCurrentVolume()) + " m3 / " + Math.ceil(developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVolume()) + " m3(max)");
                    pw.println("No items and vehicles in this parking place");
                }
                pw.println("");
            }
        }
        pw.println("----------------------------------------------------ALL TENANT LETTERS INFO----------------------------------------------------");
        for (int k = 0; k < allPeople.size(); k++) {
            if(allPeople.get(k).getTenantLetters().size() != 0){
                pw.println("Tenant letters for " + allPeople.get(k).getName() + " " + allPeople.get(k).getSurname() + ": ");
                for (int z = 0; z < allPeople.get(k).getTenantLetters().size(); z++) {
                    pw.println(z+1 + ". " + allPeople.get(k).getTenantLetters().get(z).getLetter());
                }
            }else {
                pw.println("Tenant " + allPeople.get(k).getName() + " " + allPeople.get(k).getSurname() + " doesn't have any tenant letters");
            }
            pw.println("");
        }
        pw.println("----------------------------------------------------ADDITIONAL INFO----------------------------------------------------");
        pw.println("Number of housing estates: " + developer.getHousingEstates().size());
        int numOfAllPeopleInEstates = 0;
        int numOfAllApartments = 0;
        int numOfAllItems = 0;
        int numOfAllVehicles = 0;
        for (int i = 0; i < developer.getHousingEstates().size() ; i++) {
            numOfAllApartments += developer.getHousingEstates().get(i).getApartments().size();
            for (int j = 0; j < developer.getHousingEstates().get(i).getApartments().size(); j++) {
                numOfAllPeopleInEstates += developer.getHousingEstates().get(i).getApartments().get(j).getPeople().size();
                numOfAllItems += developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getItems().size();
                numOfAllVehicles += developer.getHousingEstates().get(i).getApartments().get(j).getParkingPlace().getVehicles().size();
            }
        }
        pw.println("Number of all apartments in housing estates: " + numOfAllApartments);
        pw.println("Number of all people who lives in housing estates: " + numOfAllPeopleInEstates);
        pw.println("Number of all items stored in housing estates: " + numOfAllItems);
        pw.println("Number of all vehicles stored in housing estates: " + numOfAllVehicles);
        pw.println("");
        for (int k = 0; k < developer.getHousingEstates().size(); k++) {
            pw.println("Number of all apartments in housing estate " + (k+1) + ": " + developer.getHousingEstates().get(k).getApartments().size());
            int numOfFreeApp = 0;
            int numOfOccupiedApp = 0;
            int numOfPeopleInEstate = 0;
            int numOfItems = 0;
            int numOfVehicles = 0;
            for (int i = 0; i < developer.getHousingEstates().get(k).getApartments().size(); i++) {
                if(developer.getHousingEstates().get(k).getApartments().get(i).getTenant() == null){
                    numOfFreeApp++;
                }else {
                    numOfOccupiedApp++;
                }
            }
            for (int i = 0; i < developer.getHousingEstates().get(k).getApartments().size(); i++) {
                numOfPeopleInEstate += developer.getHousingEstates().get(k).getApartments().get(i).getPeople().size();
                numOfItems += developer.getHousingEstates().get(k).getApartments().get(i).getParkingPlace().getItems().size();
                numOfVehicles += developer.getHousingEstates().get(k).getApartments().get(i).getParkingPlace().getVehicles().size();
            }
            pw.println("Number of free apartments in housing estate " + (k+1) + ": " + numOfFreeApp);
            pw.println("Number of occupied apartments in housing estate " + (k+1) + ": " + numOfOccupiedApp);
            pw.println("Number of people who lives in housing estate " + (k+1) + ": " + numOfPeopleInEstate);
            pw.println("Number of items stored in housing estate " + (k+1) + ": " + numOfItems);
            pw.println("Number of all vehicles stored in housing estates " + (k+1) + ": " + numOfVehicles);
            pw.println("");
        }
        pw.close();
        System.out.println("File has been successfully written");
    }
}



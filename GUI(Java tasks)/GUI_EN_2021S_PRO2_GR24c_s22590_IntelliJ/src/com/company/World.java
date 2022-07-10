package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class World {

    private ArrayList<Country> allCountries = new ArrayList<>();
    private ArrayList<String> listVirusHistoryList = new ArrayList<>();
    private Player player;
    private long percentWorldInfected = 0;
    private long worldCases = 0;
    private long worldCureProgress = 0;
    private long worldCured = 0;
    private long newInfectedPeople = 0;
    long startTime;
    ArrayList<Thread> listThreads = new ArrayList<>();

    public ArrayList<String> getListVirusHistoryList() {
        return listVirusHistoryList;
    }

    public void setListVirusHistoryList(ArrayList<String> listVirusHistoryList) {
        this.listVirusHistoryList = listVirusHistoryList;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public ArrayList<Thread> getListThreads() {
        return listThreads;
    }

    public void setListThreads(ArrayList<Thread> listThreads) {
        this.listThreads = listThreads;
    }

    public World(ArrayList<Country> allCountries) {
        this.allCountries = allCountries;
    }

    public World(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public long getPercentWorldInfected() {
        return percentWorldInfected;
    }

    public void setPercentWorldInfected(long percentWorldInfected) {
        this.percentWorldInfected = percentWorldInfected;
    }

    public long getWorldCases() {
        return worldCases;
    }

    public void setWorldCases(long worldCases) {
        this.worldCases = worldCases;
    }

    public long getWorldCureProgress() {
        return worldCureProgress;
    }

    public void setWorldCureProgress(long worldCureProgress) {
        this.worldCureProgress = worldCureProgress;
    }

    public long getNewInfectedPeople() {
        return newInfectedPeople;
    }

    public void setNewInfectedPeople(long newInfectedPeople) {
        this.newInfectedPeople = newInfectedPeople;
    }

    public long getWorldCured() {
        return worldCured;
    }

    public void setWorldCured(long worldCured) {
        this.worldCured = worldCured;
    }

    public ArrayList<Country> getAllCountries() {
        return allCountries;
    }

    public void setAllCountries(ArrayList<Country> allCountries) {
        this.allCountries = allCountries;
    }

    public void setOneCountry(Country country) {
        allCountries.add(country);
    }

    public void dayInCountryWithVirus(Virus virus, Country country, JLabel background) throws InterruptedException {
        long infectedPeopleInCountry = country.getInfectedPeople();
        Random rn = new Random();
        if(country.getInfectedPeople() > 0 && country.getUnInfectedPeople() < country.getCountryPopulation()){
            newInfectedPeople = (long)((rn.nextInt((25 - 5) + 1) + 5 + infectedPeopleInCountry*0.1) * virus.getSpreadCoefficient() + country.getCountryPopulation()*0.00005 + infectedPeopleInCountry*0.005);
            //newInfectedPeople = (long)((rn.nextInt((25 - 5) + 1) + 5) * virus.getSpreadCoefficient() + country.getCountryPopulation()*0.0005 + infectedPeopleInCountry*0.5);

            if(country.isColdCountry()){
                newInfectedPeople *= 0.95;
            }else if(country.isHotCountry()){
                newInfectedPeople *= 1.05;
            }

            if(country.isLockDown()){
                newInfectedPeople *= 0.5;
            }

            newInfectedPeople = changeInfectedByUpgrades(newInfectedPeople);

            if(newInfectedPeople < 1){
                newInfectedPeople = (long)(rn.nextInt((25 - 5) + 1) + 5 + infectedPeopleInCountry*0.1);
            }


            country.setInfectedPeople(newInfectedPeople + infectedPeopleInCountry);
            if(country.getVirusCases() < country.getCountryPopulation()){
                country.setVirusCases(country.getVirusCases() + newInfectedPeople);
            }else {
                country.setVirusCases(country.getCountryPopulation());
            }
            country.setUnInfectedPeople(country.getUnInfectedPeople() - newInfectedPeople);


            Random rand = new Random();
            int randNum = rand.nextInt(100);
            int randNumForFlight = rand.nextInt(100);
            int randNumForSwim = rand.nextInt(100);

            if(randNum <= 5){
                if(country.getInfectedPeople() > country.getCountryPopulation()*0.15){
                    int randomNum = rand.nextInt(country.getNeighbourCountry().size());
                    for (int i = 0; i < this.getAllCountries().size(); i++) {
                        if(country.getNeighbourCountry().get(randomNum).equals(this.getAllCountries().get(i).getName())){
                            virusCrossBorder(this.allCountries.get(i), 5);
                            boolean isInList = false;
                            for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                if (listVirusHistoryList.get(j).equals("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                    isInList = true;
                                    break;
                                }
                            }
                            if(!isInList){
                                System.out.println("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName());
                                listVirusHistoryList.add("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName());
                            }
                            player.setMoney(player.getMoney() + 500);

                            if(country.getName().equals("Portugal") && this.allCountries.get(i).getName().equals("Spain")){
                                doVirusMigration(background,25, 445, 60,465, "car");
                            }
                            if(country.getName().equals("Spain") && this.allCountries.get(i).getName().equals("Portugal")){
                                doVirusMigration(background,60, 465, 25,445, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Spain")){
                                doVirusMigration(background,125, 375, 125,465, "car");
                            }
                            if(country.getName().equals("Spain") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,125, 465, 125,375, "car");
                            }
                            if(country.getName().equals("Belgium") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,145, 330, 145,360, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Belgium")){
                                doVirusMigration(background,145, 360, 145,330, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Switzerland")){
                                doVirusMigration(background,175, 375, 195,400, "car");
                            }
                            if(country.getName().equals("Switzerland") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,195, 400, 175,375, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,200, 355, 240,355, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,240, 355 , 200,355, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Italy")){
                                doVirusMigration(background,145, 375 , 245,455, "car");
                            }
                            if(country.getName().equals("Italy") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,245, 455 , 145,375, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Turkey")){
                                doVirusMigration(background,445, 475 , 445,525, "car");
                            }
                            if(country.getName().equals("Turkey") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Greece") && this.allCountries.get(i).getName().equals("Turkey")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Turkey") && this.allCountries.get(i).getName().equals("Greece")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Albania") && this.allCountries.get(i).getName().equals("Greece")){
                                doVirusMigration(background,390, 508 , 410,568, "car");
                            }
                            if(country.getName().equals("Greece") && this.allCountries.get(i).getName().equals("Albania")){
                                doVirusMigration(background,410, 568 , 390,508, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Albania")){
                                doVirusMigration(background,398, 475 , 398,495, "car");
                            }
                            if(country.getName().equals("Albania") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,398, 495 , 398,475, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,398, 430 , 398,455, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,398, 455 , 398,430, "car");
                            }
                            if(country.getName().equals("Moldova") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,430, 390 , 430,415, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Moldova")){
                                doVirusMigration(background,430, 415 , 430,390, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Moldova")){
                                doVirusMigration(background,440, 355 , 440,375, "car");
                            }
                            if(country.getName().equals("Moldova") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,440, 375 , 440,355, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,430, 305 , 430,340, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,430, 340 , 430,305, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,410, 270 , 410,290, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,410, 290 , 410,270, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,390, 245 , 390,260, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,390, 260 , 390,245, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,445, 355 , 445,335, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,445, 335 , 445,355, "car");
                            }
                            if(country.getName().equals("Finland") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,420, 150 , 430,210, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Finland")){
                                doVirusMigration(background,430, 210 , 420,150, "car");
                            }
                            if(country.getName().equals("Sweden") && this.allCountries.get(i).getName().equals("Finland")){
                                doVirusMigration(background,355, 123 , 365,153, "car");
                            }
                            if(country.getName().equals("Finland") && this.allCountries.get(i).getName().equals("Sweden")){
                                doVirusMigration(background,365, 153 , 355,123, "car");
                            }
                            if(country.getName().equals("Sweden") && this.allCountries.get(i).getName().equals("Norway")){
                                doVirusMigration(background,295, 123 , 295,168, "car");
                            }
                            if(country.getName().equals("Norway") && this.allCountries.get(i).getName().equals("Sweden")){
                                doVirusMigration(background,295, 168 , 295,123, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Estonia")){
                                doVirusMigration(background,380, 230 , 380,210, "car");
                            }
                            if(country.getName().equals("Estonia") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,380, 210 , 380,230, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Czech Republic")){
                                doVirusMigration(background,360, 323 , 360,350, "car");
                            }
                            if(country.getName().equals("Czech Republic") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,360, 350 , 360,323, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,405, 302 , 430,302, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,430, 302 , 405,302, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,395, 320 , 435,350, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,435, 350 , 395,320, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,365, 265 , 365,310, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,365, 310 , 365,265, "car");
                            }
                            if(country.getName().equals("Czech Republic") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,265, 352 , 265,332, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Czech Republic")){
                                doVirusMigration(background,265, 332 , 265,352, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Denmark")){
                                doVirusMigration(background,265, 318 , 265,260, "car");
                            }
                            if(country.getName().equals("Denmark") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,265, 260 , 265,318, "car");
                            }
                            if(country.getName().equals("Belgium") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,220, 315 , 240,315, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Belgium")){
                                doVirusMigration(background,240, 315 , 220,315, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,310, 310 , 330,310, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,330, 310 , 310,310, "car");
                            }
                            if(country.getName().equals("Estonia") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,425, 210 , 445,210, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Estonia")){
                                doVirusMigration(background,445, 210 , 425,210, "car");
                            }
                            if(country.getName().equals("Hungary") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,380, 380 , 410,405, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Hungary")){
                                doVirusMigration(background,410, 405 , 380,380, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Switzerland")){
                                doVirusMigration(background,240, 333 , 240,383, "car");
                            }
                            if(country.getName().equals("Switzerland") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,240, 383 , 240,333, "car");
                            }
                            if(country.getName().equals("United Kingdom") && this.allCountries.get(i).getName().equals("Ireland")){
                                doVirusMigration(background,130, 260 , 120,235, "car");
                            }
                            if(country.getName().equals("Ireland") && this.allCountries.get(i).getName().equals("United Kingdom")){
                                doVirusMigration(background,120, 235 , 130,260, "car");
                            }
                        }
                    }

                    if(randNumForFlight<=10){
                        if(country.isHasAirport()){
                            randomNum = rand.nextInt(country.getAirportCountry().size());
                            ArrayList<Country> countriesWithAirport = new ArrayList<>();
                            for (Country country1 : this.getAllCountries()){
                                if(country1.isHasAirport()){
                                    if(country.getAirportCountry().get(randomNum).equals(country1.getName())) {
                                        countriesWithAirport.add(country1);
                                    }
                                }
                            }

                            randomNum = rand.nextInt(countriesWithAirport.size());
                            if(!country.getName().equals(countriesWithAirport.get(randomNum).getName())){
                                virusCrossBorder(countriesWithAirport.get(randomNum), 5);
                                boolean isInList = false;
                                for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                    if (listVirusHistoryList.get(j).equals("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                        isInList = true;
                                        break;
                                    }
                                }
                                if(!isInList){
                                    System.out.println("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName());
                                    listVirusHistoryList.add("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName());
                                }
                                player.setMoney(player.getMoney() + 1500);
                                //if(!isInProgress){
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,110, 55, 155,175, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "plane");
                                }
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,110, 55, 155,175, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "plane");
                                }
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,95, 55, 95,435, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,95, 435, 95,55, "plane");
                                }
                                if(country.getName().equals("Ireland") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,80, 265, 80,420, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Ireland")){
                                    doVirusMigration(background,80, 420, 80,265, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Italy")){
                                    doVirusMigration(background,110, 495, 310,495, "plane");
                                }
                                if(country.getName().equals("Italy") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,310, 495, 110,495, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Greece")){
                                    doVirusMigration(background,80, 515, 380,515, "plane");
                                }
                                if(country.getName().equals("Greece") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,380, 515, 80,515, "plane");
                                }
                                if(country.getName().equals("Moldova") && countriesWithAirport.get(randomNum).getName().equals("Turkey")){
                                    doVirusMigration(background,485, 385, 485,485, "plane");
                                }
                                if(country.getName().equals("Turkey") && countriesWithAirport.get(randomNum).getName().equals("Moldova")){
                                    doVirusMigration(background,485, 485, 485,385, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Poland")){
                                    doVirusMigration(background,320, 245, 320,285, "plane");
                                }
                                if(country.getName().equals("Poland") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,320, 285, 320,245, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Latvia")){
                                    doVirusMigration(background,350, 190, 385,235, "plane");
                                }
                                if(country.getName().equals("Latvia") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,385, 235, 350,190, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Estonia")){
                                    doVirusMigration(background,340, 140, 380,190, "plane");
                                }
                                if(country.getName().equals("Estonia") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,380, 190, 340,140, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("Denmark")){
                                    doVirusMigration(background,253, 205, 253,245, "plane");
                                }
                                if(country.getName().equals("Denmark") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,253, 245, 253,205, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Germany")){
                                    doVirusMigration(background,163, 235, 263,275, "plane");
                                }
                                if(country.getName().equals("Germany") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,263, 275, 163,235, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("France")){
                                    doVirusMigration(background,113, 305, 113,345, "plane");
                                }
                                if(country.getName().equals("France") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,113, 345, 113,305, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,163, 195, 248,195, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,248, 195, 163,195, "plane");
                                }

                                //}
                            }
                        }
                    }
                    if(randNumForSwim<=10){
                        if(country.isHasPort()){
                            randomNum = rand.nextInt(country.getPortCountry().size());
                            ArrayList<Country> countriesWithPort = new ArrayList<>();
                            for (Country country1 : this.getAllCountries()){
                                if(country1.isHasPort()){
                                    if(country.getPortCountry().get(randomNum).equals(country1.getName())) {
                                        countriesWithPort.add(country1);
                                    }
                                }
                            }

                            randomNum = rand.nextInt(countriesWithPort.size());
                            if(!country.getName().equals(countriesWithPort.get(randomNum).getName())){
                                virusCrossBorder(countriesWithPort.get(randomNum), 5);
                                boolean isInList = false;
                                for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                    if (listVirusHistoryList.get(j).equals("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                        isInList = true;
                                        break;
                                    }
                                }
                                if(!isInList){
                                    System.out.println("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName());
                                    listVirusHistoryList.add("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName());
                                }
                                player.setMoney(player.getMoney() + 1500);

                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,110, 55, 155,175, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "ship");
                                }
                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,110, 55, 155,175, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "ship");
                                }
                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,95, 55, 95,435, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,95, 435, 95,55, "ship");
                                }
                                if(country.getName().equals("Ireland") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,80, 265, 80,420, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Ireland")){
                                    doVirusMigration(background,80, 420, 80,265, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Italy")){
                                    doVirusMigration(background,110, 495, 310,495, "ship");
                                }
                                if(country.getName().equals("Italy") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,310, 495, 110,495, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Greece")){
                                    doVirusMigration(background,80, 515, 380,515, "ship");
                                }
                                if(country.getName().equals("Greece") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,380, 515, 80,515, "ship");
                                }
                                if(country.getName().equals("Moldova") && countriesWithPort.get(randomNum).getName().equals("Turkey")){
                                    doVirusMigration(background,485, 385, 485,485, "ship");
                                }
                                if(country.getName().equals("Turkey") && countriesWithPort.get(randomNum).getName().equals("Moldova")){
                                    doVirusMigration(background,485, 485, 485,385, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Poland")){
                                    doVirusMigration(background,320, 245, 320,285, "ship");
                                }
                                if(country.getName().equals("Poland") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,320, 285, 320,245, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Latvia")){
                                    doVirusMigration(background,350, 190, 385,235, "ship");
                                }
                                if(country.getName().equals("Latvia") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,385, 235, 350,190, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Estonia")){
                                    doVirusMigration(background,340, 140, 380,190, "ship");
                                }
                                if(country.getName().equals("Estonia") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,380, 190, 340,140, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("Denmark")){
                                    doVirusMigration(background,253, 205, 253,245, "ship");
                                }
                                if(country.getName().equals("Denmark") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,253, 245, 253,205, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Germany")){
                                    doVirusMigration(background,163, 235, 263,275, "ship");
                                }
                                if(country.getName().equals("Germany") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,263, 275, 163,235, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("France")){
                                    doVirusMigration(background,113, 305, 113,345, "ship");
                                }
                                if(country.getName().equals("France") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,113, 345, 113,305, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,163, 195, 248,195, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,248, 195, 163,195, "ship");
                                }
                            }
                        }
                    }
                }else if(country.getInfectedPeople() >= country.getCountryPopulation()*0.15 && country.getInfectedPeople() < country.getCountryPopulation()*0.50){
                    int randomNum = rand.nextInt(country.getNeighbourCountry().size());

                    for (int i = 0; i < this.getAllCountries().size(); i++) {
                        if(country.getNeighbourCountry().get(randomNum).equals(this.getAllCountries().get(i).getName())){
                            virusCrossBorder(this.allCountries.get(i), 50);
                            player.setMoney(player.getMoney() + 500);
                            boolean isInList = false;
                            for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                if (listVirusHistoryList.get(j).equals("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                    isInList = true;
                                    break;
                                }
                            }
                            if(!isInList){
                                System.out.println("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName());
                                listVirusHistoryList.add("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName());
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,405, 302 , 430,302, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,430, 302 , 405,302, "car");
                            }
                            if(country.getName().equals("Portugal") && this.allCountries.get(i).getName().equals("Spain")){
                                doVirusMigration(background,25, 445, 60,465, "car");
                            }
                            if(country.getName().equals("Spain") && this.allCountries.get(i).getName().equals("Portugal")){
                                doVirusMigration(background,60, 465, 25,445, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Spain")){
                                doVirusMigration(background,125, 375, 125,465, "car");
                            }
                            if(country.getName().equals("Spain") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,125, 465, 125,375, "car");
                            }
                            if(country.getName().equals("Belgium") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,145, 330, 145,360, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Belgium")){
                                doVirusMigration(background,145, 360, 145,330, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Switzerland")){
                                doVirusMigration(background,175, 375, 195,400, "car");
                            }
                            if(country.getName().equals("Switzerland") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,195, 400, 175,375, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,200, 355, 240,355, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,240, 355 , 200,355, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Italy")){
                                doVirusMigration(background,145, 375 , 245,455, "car");
                            }
                            if(country.getName().equals("Italy") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,245, 455 , 145,375, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Turkey")){
                                doVirusMigration(background,445, 475 , 445,525, "car");
                            }
                            if(country.getName().equals("Turkey") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Greece") && this.allCountries.get(i).getName().equals("Turkey")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Turkey") && this.allCountries.get(i).getName().equals("Greece")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Albania") && this.allCountries.get(i).getName().equals("Greece")){
                                doVirusMigration(background,390, 508 , 410,568, "car");
                            }
                            if(country.getName().equals("Greece") && this.allCountries.get(i).getName().equals("Albania")){
                                doVirusMigration(background,410, 568 , 390,508, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Albania")){
                                doVirusMigration(background,398, 475 , 398,495, "car");
                            }
                            if(country.getName().equals("Albania") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,398, 495 , 398,475, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,398, 430 , 398,455, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,398, 455 , 398,430, "car");
                            }
                            if(country.getName().equals("Moldova") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,430, 390 , 430,415, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Moldova")){
                                doVirusMigration(background,430, 415 , 430,390, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Moldova")){
                                doVirusMigration(background,440, 355 , 440,375, "car");
                            }
                            if(country.getName().equals("Moldova") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,440, 375 , 440,355, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,430, 305 , 430,340, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,430, 340 , 430,305, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,410, 270 , 410,290, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,410, 290 , 410,270, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,390, 245 , 390,260, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,390, 260 , 390,245, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,445, 355 , 445,335, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,445, 335 , 445,355, "car");
                            }
                            if(country.getName().equals("Finland") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,420, 150 , 430,210, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Finland")){
                                doVirusMigration(background,430, 210 , 420,150, "car");
                            }
                            if(country.getName().equals("Sweden") && this.allCountries.get(i).getName().equals("Finland")){
                                doVirusMigration(background,355, 123 , 365,153, "car");
                            }
                            if(country.getName().equals("Finland") && this.allCountries.get(i).getName().equals("Sweden")){
                                doVirusMigration(background,365, 153 , 355,123, "car");
                            }
                            if(country.getName().equals("Sweden") && this.allCountries.get(i).getName().equals("Norway")){
                                doVirusMigration(background,295, 123 , 295,168, "car");
                            }
                            if(country.getName().equals("Norway") && this.allCountries.get(i).getName().equals("Sweden")){
                                doVirusMigration(background,295, 168 , 295,123, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Estonia")){
                                doVirusMigration(background,380, 230 , 380,210, "car");
                            }
                            if(country.getName().equals("Estonia") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,380, 210 , 380,230, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Czech Republic")){
                                doVirusMigration(background,360, 323 , 360,350, "car");
                            }
                            if(country.getName().equals("Czech Republic") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,360, 350 , 360,323, "car");
                            }
                            if(country.getName().equals("Czech Republic") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,265, 352 , 265,332, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Czech Republic")){
                                doVirusMigration(background,265, 332 , 265,352, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Denmark")){
                                doVirusMigration(background,265, 318 , 265,260, "car");
                            }
                            if(country.getName().equals("Denmark") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,265, 260 , 265,318, "car");
                            }
                            if(country.getName().equals("Belgium") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,220, 315 , 240,315, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Belgium")){
                                doVirusMigration(background,240, 315 , 220,315, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,310, 310 , 330,310, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,330, 310 , 310,310, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Switzerland")){
                                doVirusMigration(background,240, 333 , 240,383, "car");
                            }
                            if(country.getName().equals("Switzerland") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,240, 383 , 240,333, "car");
                            }
                            if(country.getName().equals("United Kingdom") && this.allCountries.get(i).getName().equals("Ireland")){
                                doVirusMigration(background,130, 260 , 120,235, "car");
                            }
                            if(country.getName().equals("Ireland") && this.allCountries.get(i).getName().equals("United Kingdom")){
                                doVirusMigration(background,120, 235 , 130,260, "car");
                            }
                        }
                    }

                    if(randNumForFlight<=10){
                        if(country.isHasAirport()){
                            randomNum = rand.nextInt(country.getAirportCountry().size());
                            ArrayList<Country> countriesWithAirport = new ArrayList<>();
                            for (Country country1 : this.getAllCountries()){
                                if(country1.isHasAirport()){
                                    if(country.getAirportCountry().get(randomNum).equals(country1.getName())) {
                                        countriesWithAirport.add(country1);
                                    }
                                }
                            }
                            randomNum = rand.nextInt(countriesWithAirport.size());
                            if(!country.getName().equals(countriesWithAirport.get(randomNum).getName())){
                                virusCrossBorder(countriesWithAirport.get(randomNum), 50);
                                boolean isInList = false;
                                for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                    if (listVirusHistoryList.get(j).equals("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                        isInList = true;
                                        break;
                                    }
                                }
                                if(!isInList){
                                    System.out.println("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName());
                                    listVirusHistoryList.add("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName());
                                }
                                player.setMoney(player.getMoney() + 1500);
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,110, 55, 155,175, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "plane");
                                }
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,110, 55, 155,175, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "plane");
                                }
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,95, 55, 95,435, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,95, 435, 95,55, "plane");
                                }
                                if(country.getName().equals("Ireland") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,80, 265, 80,420, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Ireland")){
                                    doVirusMigration(background,80, 420, 80,265, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Italy")){
                                    doVirusMigration(background,110, 495, 310,495, "plane");
                                }
                                if(country.getName().equals("Italy") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,310, 495, 110,495, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Greece")){
                                    doVirusMigration(background,80, 515, 380,515, "plane");
                                }
                                if(country.getName().equals("Greece") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,380, 515, 80,515, "plane");
                                }
                                if(country.getName().equals("Moldova") && countriesWithAirport.get(randomNum).getName().equals("Turkey")){
                                    doVirusMigration(background,485, 385, 485,485, "plane");
                                }
                                if(country.getName().equals("Turkey") && countriesWithAirport.get(randomNum).getName().equals("Moldova")){
                                    doVirusMigration(background,485, 485, 485,385, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Poland")){
                                    doVirusMigration(background,320, 245, 320,285, "plane");
                                }
                                if(country.getName().equals("Poland") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,320, 285, 320,245, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Latvia")){
                                    doVirusMigration(background,350, 190, 385,235, "plane");
                                }
                                if(country.getName().equals("Latvia") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,385, 235, 350,190, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Estonia")){
                                    doVirusMigration(background,340, 140, 380,190, "plane");
                                }
                                if(country.getName().equals("Estonia") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,380, 190, 340,140, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("Denmark")){
                                    doVirusMigration(background,253, 205, 253,245, "plane");
                                }
                                if(country.getName().equals("Denmark") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,253, 245, 253,205, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Germany")){
                                    doVirusMigration(background,163, 235, 263,275, "plane");
                                }
                                if(country.getName().equals("Germany") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,263, 275, 163,235, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("France")){
                                    doVirusMigration(background,113, 305, 113,345, "plane");
                                }
                                if(country.getName().equals("France") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,113, 345, 113,305, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,163, 195, 248,195, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,248, 195, 163,195, "plane");
                                }
                            }
                        }
                    }
                    if(randNumForSwim<=10){
                        if(country.isHasPort()){
                            randomNum = rand.nextInt(country.getPortCountry().size());
                            ArrayList<Country> countriesWithPort = new ArrayList<>();
                            for (Country country1 : this.getAllCountries()){
                                if(country1.isHasPort()){
                                    if(country.getPortCountry().get(randomNum).equals(country1.getName())) {
                                        countriesWithPort.add(country1);
                                    }
                                }
                            }
                            randomNum = rand.nextInt(countriesWithPort.size());
                            if(!country.getName().equals(countriesWithPort.get(randomNum).getName())){
                                virusCrossBorder(countriesWithPort.get(randomNum), 50);
                                boolean isInList = false;
                                for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                    if (listVirusHistoryList.get(j).equals("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                        isInList = true;
                                        break;
                                    }
                                }
                                if(!isInList){
                                    System.out.println("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName());
                                    listVirusHistoryList.add("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName());
                                }
                                player.setMoney(player.getMoney() + 1500);

                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,110, 55, 155,175, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "ship");
                                }
                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,110, 55, 155,175, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "ship");
                                }
                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,95, 55, 95,435, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,95, 435, 95,55, "ship");
                                }
                                if(country.getName().equals("Ireland") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,80, 265, 80,420, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Ireland")){
                                    doVirusMigration(background,80, 420, 80,265, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Italy")){
                                    doVirusMigration(background,110, 495, 310,495, "ship");
                                }
                                if(country.getName().equals("Italy") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,310, 495, 110,495, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Greece")){
                                    doVirusMigration(background,80, 515, 380,515, "ship");
                                }
                                if(country.getName().equals("Greece") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,380, 515, 80,515, "ship");
                                }
                                if(country.getName().equals("Moldova") && countriesWithPort.get(randomNum).getName().equals("Turkey")){
                                    doVirusMigration(background,485, 385, 485,485, "ship");
                                }
                                if(country.getName().equals("Turkey") && countriesWithPort.get(randomNum).getName().equals("Moldova")){
                                    doVirusMigration(background,485, 485, 485,385, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Poland")){
                                    doVirusMigration(background,320, 245, 320,285, "ship");
                                }
                                if(country.getName().equals("Poland") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,320, 285, 320,245, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Latvia")){
                                    doVirusMigration(background,350, 190, 385,235, "ship");
                                }
                                if(country.getName().equals("Latvia") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,385, 235, 350,190, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Estonia")){
                                    doVirusMigration(background,340, 140, 380,190, "ship");
                                }
                                if(country.getName().equals("Estonia") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,380, 190, 340,140, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("Denmark")){
                                    doVirusMigration(background,253, 205, 253,245, "ship");
                                }
                                if(country.getName().equals("Denmark") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,253, 245, 253,205, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Germany")){
                                    doVirusMigration(background,163, 235, 263,275, "ship");
                                }
                                if(country.getName().equals("Germany") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,263, 275, 163,235, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("France")){
                                    doVirusMigration(background,113, 305, 113,345, "ship");
                                }
                                if(country.getName().equals("France") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,113, 345, 113,305, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,163, 195, 248,195, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,248, 195, 163,195, "ship");
                                }
                            }
                        }
                    }
                }else if(country.getInfectedPeople() >= country.getCountryPopulation()*0.50 && country.getInfectedPeople() < country.getCountryPopulation()*0.75){
                    int randomNum = rand.nextInt(country.getNeighbourCountry().size());

                    for (int i = 0; i < this.getAllCountries().size(); i++) {
                        if(country.getNeighbourCountry().get(randomNum).equals(this.getAllCountries().get(i).getName())){
                            virusCrossBorder(this.allCountries.get(i), 500);
                            player.setMoney(player.getMoney() + 500);
                            boolean isInList = false;
                            for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                if (listVirusHistoryList.get(j).equals("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                    isInList = true;
                                    break;
                                }
                            }
                            if(!isInList){
                                System.out.println("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName());
                                listVirusHistoryList.add("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName());
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,405, 302 , 430,302, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,430, 302 , 405,302, "car");
                            }
                            if(country.getName().equals("Portugal") && this.allCountries.get(i).getName().equals("Spain")){
                                doVirusMigration(background,25, 445, 60,465, "car");
                            }
                            if(country.getName().equals("Spain") && this.allCountries.get(i).getName().equals("Portugal")){
                                doVirusMigration(background,60, 465, 25,445, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Spain")){
                                doVirusMigration(background,125, 375, 125,465, "car");
                            }
                            if(country.getName().equals("Spain") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,125, 465, 125,375, "car");
                            }
                            if(country.getName().equals("Belgium") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,145, 330, 145,360, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Belgium")){
                                doVirusMigration(background,145, 360, 145,330, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Switzerland")){
                                doVirusMigration(background,175, 375, 195,400, "car");
                            }
                            if(country.getName().equals("Switzerland") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,195, 400, 175,375, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,200, 355, 240,355, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,240, 355 , 200,355, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Italy")){
                                doVirusMigration(background,145, 375 , 245,455, "car");
                            }
                            if(country.getName().equals("Italy") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,245, 455 , 145,375, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Turkey")){
                                doVirusMigration(background,445, 475 , 445,525, "car");
                            }
                            if(country.getName().equals("Turkey") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Greece") && this.allCountries.get(i).getName().equals("Turkey")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Turkey") && this.allCountries.get(i).getName().equals("Greece")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Albania") && this.allCountries.get(i).getName().equals("Greece")){
                                doVirusMigration(background,390, 508 , 410,568, "car");
                            }
                            if(country.getName().equals("Greece") && this.allCountries.get(i).getName().equals("Albania")){
                                doVirusMigration(background,410, 568 , 390,508, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Albania")){
                                doVirusMigration(background,398, 475 , 398,495, "car");
                            }
                            if(country.getName().equals("Albania") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,398, 495 , 398,475, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,398, 430 , 398,455, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,398, 455 , 398,430, "car");
                            }
                            if(country.getName().equals("Moldova") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,430, 390 , 430,415, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Moldova")){
                                doVirusMigration(background,430, 415 , 430,390, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Moldova")){
                                doVirusMigration(background,440, 355 , 440,375, "car");
                            }
                            if(country.getName().equals("Moldova") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,440, 375 , 440,355, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,430, 305 , 430,340, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,430, 340 , 430,305, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,410, 270 , 410,290, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,410, 290 , 410,270, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,390, 245 , 390,260, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,390, 260 , 390,245, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,445, 355 , 445,335, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,445, 335 , 445,355, "car");
                            }
                            if(country.getName().equals("Finland") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,420, 150 , 430,210, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Finland")){
                                doVirusMigration(background,430, 210 , 420,150, "car");
                            }
                            if(country.getName().equals("Sweden") && this.allCountries.get(i).getName().equals("Finland")){
                                doVirusMigration(background,355, 123 , 365,153, "car");
                            }
                            if(country.getName().equals("Finland") && this.allCountries.get(i).getName().equals("Sweden")){
                                doVirusMigration(background,365, 153 , 355,123, "car");
                            }
                            if(country.getName().equals("Sweden") && this.allCountries.get(i).getName().equals("Norway")){
                                doVirusMigration(background,295, 123 , 295,168, "car");
                            }
                            if(country.getName().equals("Norway") && this.allCountries.get(i).getName().equals("Sweden")){
                                doVirusMigration(background,295, 168 , 295,123, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Estonia")){
                                doVirusMigration(background,380, 230 , 380,210, "car");
                            }
                            if(country.getName().equals("Estonia") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,380, 210 , 380,230, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Czech Republic")){
                                doVirusMigration(background,360, 323 , 360,350, "car");
                            }
                            if(country.getName().equals("Czech Republic") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,360, 350 , 360,323, "car");
                            }
                            if(country.getName().equals("Czech Republic") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,265, 352 , 265,332, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Czech Republic")){
                                doVirusMigration(background,265, 332 , 265,352, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Denmark")){
                                doVirusMigration(background,265, 318 , 265,260, "car");
                            }
                            if(country.getName().equals("Denmark") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,265, 260 , 265,318, "car");
                            }
                            if(country.getName().equals("Belgium") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,220, 315 , 240,315, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Belgium")){
                                doVirusMigration(background,240, 315 , 220,315, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,310, 310 , 330,310, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,330, 310 , 310,310, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Switzerland")){
                                doVirusMigration(background,240, 333 , 240,383, "car");
                            }
                            if(country.getName().equals("Switzerland") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,240, 383 , 240,333, "car");
                            }
                            if(country.getName().equals("United Kingdom") && this.allCountries.get(i).getName().equals("Ireland")){
                                doVirusMigration(background,130, 260 , 120,235, "car");
                            }
                            if(country.getName().equals("Ireland") && this.allCountries.get(i).getName().equals("United Kingdom")){
                                doVirusMigration(background,120, 235 , 130,260, "car");
                            }
                        }
                    }

                    if(randNumForFlight<=10){
                        if(country.isHasAirport()){
                            randomNum = rand.nextInt(country.getAirportCountry().size());
                            ArrayList<Country> countriesWithAirport = new ArrayList<>();
                            for (Country country1 : this.getAllCountries()){
                                if(country1.isHasAirport()){
                                    if(country.getAirportCountry().get(randomNum).equals(country1.getName())) {
                                        countriesWithAirport.add(country1);
                                    }
                                }
                            }

                            randomNum = rand.nextInt(countriesWithAirport.size());
                            if(!country.getName().equals(countriesWithAirport.get(randomNum).getName())){
                                virusCrossBorder(countriesWithAirport.get(randomNum), 150);
                                boolean isInList = false;
                                for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                    if (listVirusHistoryList.get(j).equals("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                        isInList = true;
                                        break;
                                    }
                                }
                                if(!isInList){
                                    System.out.println("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName());
                                    listVirusHistoryList.add("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName());
                                }
                                player.setMoney(player.getMoney() + 1500);
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,110, 55, 155,175, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "plane");
                                }
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,110, 55, 155,175, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "plane");
                                }
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,95, 55, 95,435, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,95, 435, 95,55, "plane");
                                }
                                if(country.getName().equals("Ireland") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,80, 265, 80,420, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Ireland")){
                                    doVirusMigration(background,80, 420, 80,265, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Italy")){
                                    doVirusMigration(background,110, 495, 310,495, "plane");
                                }
                                if(country.getName().equals("Italy") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,310, 495, 110,495, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Greece")){
                                    doVirusMigration(background,80, 515, 380,515, "plane");
                                }
                                if(country.getName().equals("Greece") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,380, 515, 80,515, "plane");
                                }
                                if(country.getName().equals("Moldova") && countriesWithAirport.get(randomNum).getName().equals("Turkey")){
                                    doVirusMigration(background,485, 385, 485,485, "plane");
                                }
                                if(country.getName().equals("Turkey") && countriesWithAirport.get(randomNum).getName().equals("Moldova")){
                                    doVirusMigration(background,485, 485, 485,385, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Poland")){
                                    doVirusMigration(background,320, 245, 320,285, "plane");
                                }
                                if(country.getName().equals("Poland") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,320, 285, 320,245, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Latvia")){
                                    doVirusMigration(background,350, 190, 385,235, "plane");
                                }
                                if(country.getName().equals("Latvia") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,385, 235, 350,190, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Estonia")){
                                    doVirusMigration(background,340, 140, 380,190, "plane");
                                }
                                if(country.getName().equals("Estonia") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,380, 190, 340,140, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("Denmark")){
                                    doVirusMigration(background,253, 205, 253,245, "plane");
                                }
                                if(country.getName().equals("Denmark") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,253, 245, 253,205, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Germany")){
                                    doVirusMigration(background,163, 235, 263,275, "plane");
                                }
                                if(country.getName().equals("Germany") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,263, 275, 163,235, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("France")){
                                    doVirusMigration(background,113, 305, 113,345, "plane");
                                }
                                if(country.getName().equals("France") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,113, 345, 113,305, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,163, 195, 248,195, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,248, 195, 163,195, "plane");
                                }
                            }
                        }
                    }
                    if(randNumForSwim<=10){
                        if(country.isHasPort()){
                            randomNum = rand.nextInt(country.getPortCountry().size());
                            ArrayList<Country> countriesWithPort = new ArrayList<>();
                            for (Country country1 : this.getAllCountries()){
                                if(country1.isHasPort()){
                                    if(country.getPortCountry().get(randomNum).equals(country1.getName())) {
                                        countriesWithPort.add(country1);
                                    }
                                }
                            }

                            randomNum = rand.nextInt(countriesWithPort.size());
                            if(!country.getName().equals(countriesWithPort.get(randomNum).getName())){
                                virusCrossBorder(countriesWithPort.get(randomNum), 150);
                                boolean isInList = false;
                                for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                    if (listVirusHistoryList.get(j).equals("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                        isInList = true;
                                        break;
                                    }
                                }
                                if(!isInList){
                                    System.out.println("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName());
                                    listVirusHistoryList.add("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName());
                                }
                                player.setMoney(player.getMoney() + 1500);

                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,110, 55, 155,175, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "ship");
                                }
                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,110, 55, 155,175, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "ship");
                                }
                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,95, 55, 95,435, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,95, 435, 95,55, "ship");
                                }
                                if(country.getName().equals("Ireland") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,80, 265, 80,420, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Ireland")){
                                    doVirusMigration(background,80, 420, 80,265, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Italy")){
                                    doVirusMigration(background,110, 495, 310,495, "ship");
                                }
                                if(country.getName().equals("Italy") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,310, 495, 110,495, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Greece")){
                                    doVirusMigration(background,80, 515, 380,515, "ship");
                                }
                                if(country.getName().equals("Greece") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,380, 515, 80,515, "ship");
                                }
                                if(country.getName().equals("Moldova") && countriesWithPort.get(randomNum).getName().equals("Turkey")){
                                    doVirusMigration(background,485, 385, 485,485, "ship");
                                }
                                if(country.getName().equals("Turkey") && countriesWithPort.get(randomNum).getName().equals("Moldova")){
                                    doVirusMigration(background,485, 485, 485,385, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Poland")){
                                    doVirusMigration(background,320, 245, 320,285, "ship");
                                }
                                if(country.getName().equals("Poland") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,320, 285, 320,245, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Latvia")){
                                    doVirusMigration(background,350, 190, 385,235, "ship");
                                }
                                if(country.getName().equals("Latvia") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,385, 235, 350,190, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Estonia")){
                                    doVirusMigration(background,340, 140, 380,190, "ship");
                                }
                                if(country.getName().equals("Estonia") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,380, 190, 340,140, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("Denmark")){
                                    doVirusMigration(background,253, 205, 253,245, "ship");
                                }
                                if(country.getName().equals("Denmark") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,253, 245, 253,205, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Germany")){
                                    doVirusMigration(background,163, 235, 263,275, "ship");
                                }
                                if(country.getName().equals("Germany") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,263, 275, 163,235, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("France")){
                                    doVirusMigration(background,113, 305, 113,345, "ship");
                                }
                                if(country.getName().equals("France") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,113, 345, 113,305, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,163, 195, 248,195, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,248, 195, 163,195, "ship");
                                }
                            }
                        }
                    }
                }else if(country.getInfectedPeople() >= country.getCountryPopulation()*0.75 && country.getInfectedPeople() < country.getCountryPopulation()*0.99){
                    int randomNum = rand.nextInt(country.getNeighbourCountry().size());

                    for (int i = 0; i < this.getAllCountries().size(); i++) {
                        if(country.getNeighbourCountry().get(randomNum).equals(this.getAllCountries().get(i).getName())){
                            virusCrossBorder(this.allCountries.get(i), 5000);
                            player.setMoney(player.getMoney() + 500);
                            boolean isInList = false;
                            for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                if (listVirusHistoryList.get(j).equals("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                    isInList = true;
                                    break;
                                }
                            }
                            if(!isInList){
                                System.out.println("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName());
                                listVirusHistoryList.add("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName());
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,405, 302 , 430,302, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,430, 302 , 405,302, "car");
                            }
                            if(country.getName().equals("Portugal") && this.allCountries.get(i).getName().equals("Spain")){
                                doVirusMigration(background,25, 445, 60,465, "car");
                            }
                            if(country.getName().equals("Spain") && this.allCountries.get(i).getName().equals("Portugal")){
                                doVirusMigration(background,60, 465, 25,445, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Spain")){
                                doVirusMigration(background,125, 375, 125,465, "car");
                            }
                            if(country.getName().equals("Spain") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,125, 465, 125,375, "car");
                            }
                            if(country.getName().equals("Belgium") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,145, 330, 145,360, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Belgium")){
                                doVirusMigration(background,145, 360, 145,330, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Switzerland")){
                                doVirusMigration(background,175, 375, 195,400, "car");
                            }
                            if(country.getName().equals("Switzerland") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,195, 400, 175,375, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,200, 355, 240,355, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,240, 355 , 200,355, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Italy")){
                                doVirusMigration(background,145, 375 , 245,455, "car");
                            }
                            if(country.getName().equals("Italy") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,245, 455 , 145,375, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Turkey")){
                                doVirusMigration(background,445, 475 , 445,525, "car");
                            }
                            if(country.getName().equals("Turkey") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Greece") && this.allCountries.get(i).getName().equals("Turkey")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Turkey") && this.allCountries.get(i).getName().equals("Greece")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Albania") && this.allCountries.get(i).getName().equals("Greece")){
                                doVirusMigration(background,390, 508 , 410,568, "car");
                            }
                            if(country.getName().equals("Greece") && this.allCountries.get(i).getName().equals("Albania")){
                                doVirusMigration(background,410, 568 , 390,508, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Albania")){
                                doVirusMigration(background,398, 475 , 398,495, "car");
                            }
                            if(country.getName().equals("Albania") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,398, 495 , 398,475, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,398, 430 , 398,455, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,398, 455 , 398,430, "car");
                            }
                            if(country.getName().equals("Moldova") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,430, 390 , 430,415, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Moldova")){
                                doVirusMigration(background,430, 415 , 430,390, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Moldova")){
                                doVirusMigration(background,440, 355 , 440,375, "car");
                            }
                            if(country.getName().equals("Moldova") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,440, 375 , 440,355, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,430, 305 , 430,340, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,430, 340 , 430,305, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,410, 270 , 410,290, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,410, 290 , 410,270, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,390, 245 , 390,260, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,390, 260 , 390,245, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,445, 355 , 445,335, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,445, 335 , 445,355, "car");
                            }
                            if(country.getName().equals("Finland") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,420, 150 , 430,210, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Finland")){
                                doVirusMigration(background,430, 210 , 420,150, "car");
                            }
                            if(country.getName().equals("Sweden") && this.allCountries.get(i).getName().equals("Finland")){
                                doVirusMigration(background,355, 123 , 365,153, "car");
                            }
                            if(country.getName().equals("Finland") && this.allCountries.get(i).getName().equals("Sweden")){
                                doVirusMigration(background,365, 153 , 355,123, "car");
                            }
                            if(country.getName().equals("Sweden") && this.allCountries.get(i).getName().equals("Norway")){
                                doVirusMigration(background,295, 123 , 295,168, "car");
                            }
                            if(country.getName().equals("Norway") && this.allCountries.get(i).getName().equals("Sweden")){
                                doVirusMigration(background,295, 168 , 295,123, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Estonia")){
                                doVirusMigration(background,380, 230 , 380,210, "car");
                            }
                            if(country.getName().equals("Estonia") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,380, 210 , 380,230, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Czech Republic")){
                                doVirusMigration(background,360, 323 , 360,350, "car");
                            }
                            if(country.getName().equals("Czech Republic") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,360, 350 , 360,323, "car");
                            }
                            if(country.getName().equals("Czech Republic") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,265, 352 , 265,332, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Czech Republic")){
                                doVirusMigration(background,265, 332 , 265,352, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Denmark")){
                                doVirusMigration(background,265, 318 , 265,260, "car");
                            }
                            if(country.getName().equals("Denmark") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,265, 260 , 265,318, "car");
                            }
                            if(country.getName().equals("Belgium") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,220, 315 , 240,315, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Belgium")){
                                doVirusMigration(background,240, 315 , 220,315, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,310, 310 , 330,310, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,330, 310 , 310,310, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Switzerland")){
                                doVirusMigration(background,240, 333 , 240,383, "car");
                            }
                            if(country.getName().equals("Switzerland") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,240, 383 , 240,333, "car");
                            }
                            if(country.getName().equals("United Kingdom") && this.allCountries.get(i).getName().equals("Ireland")){
                                doVirusMigration(background,130, 260 , 120,235, "car");
                            }
                            if(country.getName().equals("Ireland") && this.allCountries.get(i).getName().equals("United Kingdom")){
                                doVirusMigration(background,120, 235 , 130,260, "car");
                            }
                        }
                    }

                    if(randNumForFlight<=10){
                        if(country.isHasAirport()){
                            randomNum = rand.nextInt(country.getAirportCountry().size());
                            ArrayList<Country> countriesWithAirport = new ArrayList<>();
                            for (Country country1 : this.getAllCountries()){
                                if(country1.isHasAirport()){
                                    if(country.getAirportCountry().get(randomNum).equals(country1.getName())) {
                                        countriesWithAirport.add(country1);
                                    }
                                }
                            }

                            randomNum = rand.nextInt(countriesWithAirport.size());
                            if(!country.getName().equals(countriesWithAirport.get(randomNum).getName())){
                                virusCrossBorder(countriesWithAirport.get(randomNum), 500);
                                boolean isInList = false;
                                for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                    if (listVirusHistoryList.get(j).equals("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                        isInList = true;
                                        break;
                                    }
                                }
                                if(!isInList){
                                    System.out.println("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName());
                                    listVirusHistoryList.add("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName());
                                }
                                player.setMoney(player.getMoney() + 1500);
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,110, 55, 155,175, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "plane");
                                }
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,110, 55, 155,175, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "plane");
                                }
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,95, 55, 95,435, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,95, 435, 95,55, "plane");
                                }
                                if(country.getName().equals("Ireland") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,80, 265, 80,420, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Ireland")){
                                    doVirusMigration(background,80, 420, 80,265, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Italy")){
                                    doVirusMigration(background,110, 495, 310,495, "plane");
                                }
                                if(country.getName().equals("Italy") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,310, 495, 110,495, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Greece")){
                                    doVirusMigration(background,80, 515, 380,515, "plane");
                                }
                                if(country.getName().equals("Greece") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,380, 515, 80,515, "plane");
                                }
                                if(country.getName().equals("Moldova") && countriesWithAirport.get(randomNum).getName().equals("Turkey")){
                                    doVirusMigration(background,485, 385, 485,485, "plane");
                                }
                                if(country.getName().equals("Turkey") && countriesWithAirport.get(randomNum).getName().equals("Moldova")){
                                    doVirusMigration(background,485, 485, 485,385, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Poland")){
                                    doVirusMigration(background,320, 245, 320,285, "plane");
                                }
                                if(country.getName().equals("Poland") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,320, 285, 320,245, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Latvia")){
                                    doVirusMigration(background,350, 190, 385,235, "plane");
                                }
                                if(country.getName().equals("Latvia") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,385, 235, 350,190, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Estonia")){
                                    doVirusMigration(background,340, 140, 380,190, "plane");
                                }
                                if(country.getName().equals("Estonia") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,380, 190, 340,140, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("Denmark")){
                                    doVirusMigration(background,253, 205, 253,245, "plane");
                                }
                                if(country.getName().equals("Denmark") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,253, 245, 253,205, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Germany")){
                                    doVirusMigration(background,163, 235, 263,275, "plane");
                                }
                                if(country.getName().equals("Germany") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,263, 275, 163,235, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("France")){
                                    doVirusMigration(background,113, 305, 113,345, "plane");
                                }
                                if(country.getName().equals("France") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,113, 345, 113,305, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,163, 195, 248,195, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,248, 195, 163,195, "plane");
                                }
                            }
                        }
                    }
                    if(randNumForSwim<=10){
                        if(country.isHasPort()){
                            randomNum = rand.nextInt(country.getPortCountry().size());
                            ArrayList<Country> countriesWithPort = new ArrayList<>();
                            for (Country country1 : this.getAllCountries()){
                                if(country1.isHasPort()){
                                    if(country.getPortCountry().get(randomNum).equals(country1.getName())) {
                                        countriesWithPort.add(country1);
                                    }
                                }
                            }

                            randomNum = rand.nextInt(countriesWithPort.size());
                            if(!country.getName().equals(countriesWithPort.get(randomNum).getName())){
                                virusCrossBorder(countriesWithPort.get(randomNum), 500);
                                boolean isInList = false;
                                for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                    if (listVirusHistoryList.get(j).equals("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                        isInList = true;
                                        break;
                                    }
                                }
                                if(!isInList){
                                    System.out.println("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName());
                                    listVirusHistoryList.add("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName());
                                }
                                player.setMoney(player.getMoney() + 1500);

                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,110, 55, 155,175, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "ship");
                                }
                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,110, 55, 155,175, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "ship");
                                }
                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,95, 55, 95,435, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,95, 435, 95,55, "ship");
                                }
                                if(country.getName().equals("Ireland") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,80, 265, 80,420, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Ireland")){
                                    doVirusMigration(background,80, 420, 80,265, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Italy")){
                                    doVirusMigration(background,110, 495, 310,495, "ship");
                                }
                                if(country.getName().equals("Italy") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,310, 495, 110,495, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Greece")){
                                    doVirusMigration(background,80, 515, 380,515, "ship");
                                }
                                if(country.getName().equals("Greece") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,380, 515, 80,515, "ship");
                                }
                                if(country.getName().equals("Moldova") && countriesWithPort.get(randomNum).getName().equals("Turkey")){
                                    doVirusMigration(background,485, 385, 485,485, "ship");
                                }
                                if(country.getName().equals("Turkey") && countriesWithPort.get(randomNum).getName().equals("Moldova")){
                                    doVirusMigration(background,485, 485, 485,385, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Poland")){
                                    doVirusMigration(background,320, 245, 320,285, "ship");
                                }
                                if(country.getName().equals("Poland") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,320, 285, 320,245, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Latvia")){
                                    doVirusMigration(background,350, 190, 385,235, "ship");
                                }
                                if(country.getName().equals("Latvia") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,385, 235, 350,190, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Estonia")){
                                    doVirusMigration(background,340, 140, 380,190, "ship");
                                }
                                if(country.getName().equals("Estonia") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,380, 190, 340,140, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("Denmark")){
                                    doVirusMigration(background,253, 205, 253,245, "ship");
                                }
                                if(country.getName().equals("Denmark") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,253, 245, 253,205, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Germany")){
                                    doVirusMigration(background,163, 235, 263,275, "ship");
                                }
                                if(country.getName().equals("Germany") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,263, 275, 163,235, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("France")){
                                    doVirusMigration(background,113, 305, 113,345, "ship");
                                }
                                if(country.getName().equals("France") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,113, 345, 113,305, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,163, 195, 248,195, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,248, 195, 163,195, "ship");
                                }
                            }
                        }
                    }
                }else if(country.getInfectedPeople() >= country.getCountryPopulation()*0.99){
                    int randomNum = rand.nextInt(country.getNeighbourCountry().size());

                    for (int i = 0; i < this.getAllCountries().size(); i++) {
                        if(country.getNeighbourCountry().get(randomNum).equals(this.getAllCountries().get(i).getName())){
                            virusCrossBorder(this.allCountries.get(i), 10000);
                            player.setMoney(player.getMoney() + 500);
                            boolean isInList = false;
                            for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                if (listVirusHistoryList.get(j).equals("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                    isInList = true;
                                    break;
                                }
                            }
                            if(!isInList){
                                System.out.println("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName());
                                listVirusHistoryList.add("Virus moved to " + this.allCountries.get(i).getName() + " from " + country.getName());
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,405, 302 , 430,302, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,430, 302 , 405,302, "car");
                            }
                            if(country.getName().equals("Portugal") && this.allCountries.get(i).getName().equals("Spain")){
                                doVirusMigration(background,25, 445, 60,465, "car");
                            }
                            if(country.getName().equals("Spain") && this.allCountries.get(i).getName().equals("Portugal")){
                                doVirusMigration(background,60, 465, 25,445, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Spain")){
                                doVirusMigration(background,125, 375, 125,465, "car");
                            }
                            if(country.getName().equals("Spain") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,125, 465, 125,375, "car");
                            }
                            if(country.getName().equals("Belgium") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,145, 330, 145,360, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Belgium")){
                                doVirusMigration(background,145, 360, 145,330, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Switzerland")){
                                doVirusMigration(background,175, 375, 195,400, "car");
                            }
                            if(country.getName().equals("Switzerland") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,195, 400, 175,375, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,200, 355, 240,355, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,240, 355 , 200,355, "car");
                            }
                            if(country.getName().equals("France") && this.allCountries.get(i).getName().equals("Italy")){
                                doVirusMigration(background,145, 375 , 245,455, "car");
                            }
                            if(country.getName().equals("Italy") && this.allCountries.get(i).getName().equals("France")){
                                doVirusMigration(background,245, 455 , 145,375, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Turkey")){
                                doVirusMigration(background,445, 475 , 445,525, "car");
                            }
                            if(country.getName().equals("Turkey") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Greece") && this.allCountries.get(i).getName().equals("Turkey")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Turkey") && this.allCountries.get(i).getName().equals("Greece")){
                                doVirusMigration(background,445, 525 , 445,475, "car");
                            }
                            if(country.getName().equals("Albania") && this.allCountries.get(i).getName().equals("Greece")){
                                doVirusMigration(background,390, 508 , 410,568, "car");
                            }
                            if(country.getName().equals("Greece") && this.allCountries.get(i).getName().equals("Albania")){
                                doVirusMigration(background,410, 568 , 390,508, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Albania")){
                                doVirusMigration(background,398, 475 , 398,495, "car");
                            }
                            if(country.getName().equals("Albania") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,398, 495 , 398,475, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Bulgaria")){
                                doVirusMigration(background,398, 430 , 398,455, "car");
                            }
                            if(country.getName().equals("Bulgaria") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,398, 455 , 398,430, "car");
                            }
                            if(country.getName().equals("Moldova") && this.allCountries.get(i).getName().equals("Romania")){
                                doVirusMigration(background,430, 390 , 430,415, "car");
                            }
                            if(country.getName().equals("Romania") && this.allCountries.get(i).getName().equals("Moldova")){
                                doVirusMigration(background,430, 415 , 430,390, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Moldova")){
                                doVirusMigration(background,440, 355 , 440,375, "car");
                            }
                            if(country.getName().equals("Moldova") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,440, 375 , 440,355, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Ukraine")){
                                doVirusMigration(background,430, 305 , 430,340, "car");
                            }
                            if(country.getName().equals("Ukraine") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,430, 340 , 430,305, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Bielorussia")){
                                doVirusMigration(background,410, 270 , 410,290, "car");
                            }
                            if(country.getName().equals("Bielorussia") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,410, 290 , 410,270, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Lithuania")){
                                doVirusMigration(background,390, 245 , 390,260, "car");
                            }
                            if(country.getName().equals("Lithuania") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,390, 260 , 390,245, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,445, 355 , 445,335, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,445, 335 , 445,355, "car");
                            }
                            if(country.getName().equals("Finland") && this.allCountries.get(i).getName().equals("Russia")){
                                doVirusMigration(background,420, 150 , 430,210, "car");
                            }
                            if(country.getName().equals("Russia") && this.allCountries.get(i).getName().equals("Finland")){
                                doVirusMigration(background,430, 210 , 420,150, "car");
                            }
                            if(country.getName().equals("Sweden") && this.allCountries.get(i).getName().equals("Finland")){
                                doVirusMigration(background,355, 123 , 365,153, "car");
                            }
                            if(country.getName().equals("Finland") && this.allCountries.get(i).getName().equals("Sweden")){
                                doVirusMigration(background,365, 153 , 355,123, "car");
                            }
                            if(country.getName().equals("Sweden") && this.allCountries.get(i).getName().equals("Norway")){
                                doVirusMigration(background,295, 123 , 295,168, "car");
                            }
                            if(country.getName().equals("Norway") && this.allCountries.get(i).getName().equals("Sweden")){
                                doVirusMigration(background,295, 168 , 295,123, "car");
                            }
                            if(country.getName().equals("Latvia") && this.allCountries.get(i).getName().equals("Estonia")){
                                doVirusMigration(background,380, 230 , 380,210, "car");
                            }
                            if(country.getName().equals("Estonia") && this.allCountries.get(i).getName().equals("Latvia")){
                                doVirusMigration(background,380, 210 , 380,230, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Czech Republic")){
                                doVirusMigration(background,360, 323 , 360,350, "car");
                            }
                            if(country.getName().equals("Czech Republic") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,360, 350 , 360,323, "car");
                            }
                            if(country.getName().equals("Czech Republic") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,265, 352 , 265,332, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Czech Republic")){
                                doVirusMigration(background,265, 332 , 265,352, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Denmark")){
                                doVirusMigration(background,265, 318 , 265,260, "car");
                            }
                            if(country.getName().equals("Denmark") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,265, 260 , 265,318, "car");
                            }
                            if(country.getName().equals("Belgium") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,220, 315 , 240,315, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Belgium")){
                                doVirusMigration(background,240, 315 , 220,315, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Poland")){
                                doVirusMigration(background,310, 310 , 330,310, "car");
                            }
                            if(country.getName().equals("Poland") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,330, 310 , 310,310, "car");
                            }
                            if(country.getName().equals("Germany") && this.allCountries.get(i).getName().equals("Switzerland")){
                                doVirusMigration(background,240, 333 , 240,383, "car");
                            }
                            if(country.getName().equals("Switzerland") && this.allCountries.get(i).getName().equals("Germany")){
                                doVirusMigration(background,240, 383 , 240,333, "car");
                            }
                            if(country.getName().equals("United Kingdom") && this.allCountries.get(i).getName().equals("Ireland")){
                                doVirusMigration(background,130, 260 , 120,235, "car");
                            }
                            if(country.getName().equals("Ireland") && this.allCountries.get(i).getName().equals("United Kingdom")){
                                doVirusMigration(background,120, 235 , 130,260, "car");
                            }
                        }
                    }

                    if(randNumForFlight<=10){
                        if(country.isHasAirport()){
                            randomNum = rand.nextInt(country.getAirportCountry().size());
                            ArrayList<Country> countriesWithAirport = new ArrayList<>();
                            for (Country country1 : this.getAllCountries()){
                                if(country1.isHasAirport()){
                                    if(country.getAirportCountry().get(randomNum).equals(country1.getName())) {
                                        countriesWithAirport.add(country1);
                                    }
                                }
                            }

                            randomNum = rand.nextInt(countriesWithAirport.size());
                            if(!country.getName().equals(countriesWithAirport.get(randomNum).getName())){
                                virusCrossBorder(countriesWithAirport.get(randomNum), 1000);
                                boolean isInList = false;
                                for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                    if (listVirusHistoryList.get(j).equals("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                        isInList = true;
                                        break;
                                    }
                                }
                                if(!isInList){
                                    System.out.println("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName());
                                    listVirusHistoryList.add("Virus flew to " + countriesWithAirport.get(randomNum).getName() + " from " + country.getName());
                                }
                                player.setMoney(player.getMoney() + 1500);
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,110, 55, 155,175, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "plane");
                                }
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,110, 55, 155,175, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "plane");
                                }
                                if(country.getName().equals("Iceland") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,95, 55, 95,435, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,95, 435, 95,55, "plane");
                                }
                                if(country.getName().equals("Ireland") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,80, 265, 80,420, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Ireland")){
                                    doVirusMigration(background,80, 420, 80,265, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Italy")){
                                    doVirusMigration(background,110, 495, 310,495, "plane");
                                }
                                if(country.getName().equals("Italy") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,310, 495, 110,495, "plane");
                                }
                                if(country.getName().equals("Spain") && countriesWithAirport.get(randomNum).getName().equals("Greece")){
                                    doVirusMigration(background,80, 515, 380,515, "plane");
                                }
                                if(country.getName().equals("Greece") && countriesWithAirport.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,380, 515, 80,515, "plane");
                                }
                                if(country.getName().equals("Moldova") && countriesWithAirport.get(randomNum).getName().equals("Turkey")){
                                    doVirusMigration(background,485, 385, 485,485, "plane");
                                }
                                if(country.getName().equals("Turkey") && countriesWithAirport.get(randomNum).getName().equals("Moldova")){
                                    doVirusMigration(background,485, 485, 485,385, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Poland")){
                                    doVirusMigration(background,320, 245, 320,285, "plane");
                                }
                                if(country.getName().equals("Poland") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,320, 285, 320,245, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Latvia")){
                                    doVirusMigration(background,350, 190, 385,235, "plane");
                                }
                                if(country.getName().equals("Latvia") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,385, 235, 350,190, "plane");
                                }
                                if(country.getName().equals("Sweden") && countriesWithAirport.get(randomNum).getName().equals("Estonia")){
                                    doVirusMigration(background,340, 140, 380,190, "plane");
                                }
                                if(country.getName().equals("Estonia") && countriesWithAirport.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,380, 190, 340,140, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("Denmark")){
                                    doVirusMigration(background,253, 205, 253,245, "plane");
                                }
                                if(country.getName().equals("Denmark") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,253, 245, 253,205, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Germany")){
                                    doVirusMigration(background,163, 235, 263,275, "plane");
                                }
                                if(country.getName().equals("Germany") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,263, 275, 163,235, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("France")){
                                    doVirusMigration(background,113, 305, 113,345, "plane");
                                }
                                if(country.getName().equals("France") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,113, 345, 113,305, "plane");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithAirport.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,163, 195, 248,195, "plane");
                                }
                                if(country.getName().equals("Norway") && countriesWithAirport.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,248, 195, 163,195, "plane");
                                }
                            }
                        }
                    }
                    if(randNumForSwim<=10){
                        if(country.isHasPort()){
                            randomNum = rand.nextInt(country.getPortCountry().size());
                            ArrayList<Country> countriesWithPort = new ArrayList<>();
                            for (Country country1 : this.getAllCountries()){
                                if(country1.isHasPort()){
                                    if(country.getPortCountry().get(randomNum).equals(country1.getName())) {
                                        countriesWithPort.add(country1);
                                    }
                                }
                            }

                            randomNum = rand.nextInt(countriesWithPort.size());
                            if(!country.getName().equals(countriesWithPort.get(randomNum).getName())){
                                virusCrossBorder(countriesWithPort.get(randomNum), 1000);
                                boolean isInList = false;
                                for (int j = 0; j < listVirusHistoryList.size(); j++) {
                                    if (listVirusHistoryList.get(j).equals("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName()) || listVirusHistoryList.size() == 0) {
                                        isInList = true;
                                        break;
                                    }
                                }
                                if(!isInList){
                                    System.out.println("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName());
                                    listVirusHistoryList.add("Virus swam to " + countriesWithPort.get(randomNum).getName() + " from " + country.getName());
                                }
                                player.setMoney(player.getMoney() + 1500);

                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,110, 55, 155,175, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "ship");
                                }
                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,110, 55, 155,175, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,155, 175, 110,55, "ship");
                                }
                                if(country.getName().equals("Iceland") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,95, 55, 95,435, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Iceland")){
                                    doVirusMigration(background,95, 435, 95,55, "ship");
                                }
                                if(country.getName().equals("Ireland") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,80, 265, 80,420, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Ireland")){
                                    doVirusMigration(background,80, 420, 80,265, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Italy")){
                                    doVirusMigration(background,110, 495, 310,495, "ship");
                                }
                                if(country.getName().equals("Italy") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,310, 495, 110,495, "ship");
                                }
                                if(country.getName().equals("Spain") && countriesWithPort.get(randomNum).getName().equals("Greece")){
                                    doVirusMigration(background,80, 515, 380,515, "ship");
                                }
                                if(country.getName().equals("Greece") && countriesWithPort.get(randomNum).getName().equals("Spain")){
                                    doVirusMigration(background,380, 515, 80,515, "ship");
                                }
                                if(country.getName().equals("Moldova") && countriesWithPort.get(randomNum).getName().equals("Turkey")){
                                    doVirusMigration(background,485, 385, 485,485, "ship");
                                }
                                if(country.getName().equals("Turkey") && countriesWithPort.get(randomNum).getName().equals("Moldova")){
                                    doVirusMigration(background,485, 485, 485,385, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Poland")){
                                    doVirusMigration(background,320, 245, 320,285, "ship");
                                }
                                if(country.getName().equals("Poland") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,320, 285, 320,245, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Latvia")){
                                    doVirusMigration(background,350, 190, 385,235, "ship");
                                }
                                if(country.getName().equals("Latvia") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,385, 235, 350,190, "ship");
                                }
                                if(country.getName().equals("Sweden") && countriesWithPort.get(randomNum).getName().equals("Estonia")){
                                    doVirusMigration(background,340, 140, 380,190, "ship");
                                }
                                if(country.getName().equals("Estonia") && countriesWithPort.get(randomNum).getName().equals("Sweden")){
                                    doVirusMigration(background,380, 190, 340,140, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("Denmark")){
                                    doVirusMigration(background,253, 205, 253,245, "ship");
                                }
                                if(country.getName().equals("Denmark") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,253, 245, 253,205, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Germany")){
                                    doVirusMigration(background,163, 235, 263,275, "ship");
                                }
                                if(country.getName().equals("Germany") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,263, 275, 163,235, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("France")){
                                    doVirusMigration(background,113, 305, 113,345, "ship");
                                }
                                if(country.getName().equals("France") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,113, 345, 113,305, "ship");
                                }
                                if(country.getName().equals("United Kingdom") && countriesWithPort.get(randomNum).getName().equals("Norway")){
                                    doVirusMigration(background,163, 195, 248,195, "ship");
                                }
                                if(country.getName().equals("Norway") && countriesWithPort.get(randomNum).getName().equals("United Kingdom")){
                                    doVirusMigration(background,248, 195, 163,195, "ship");
                                }
                            }
                        }
                    }
                }
            }

            if(country.getInfectedPeople() >= country.getCountryPopulation()){
                country.setInfectedPeople(country.getCountryPopulation());
                country.setVirusCases(country.getCountryPopulation());
                country.setUnInfectedPeople(0);
            }

        }
    }

    public void virusCrossBorder(Country country, int people){
        if(country.getInfectedPeople() > 0 && country.getInfectedPeople() < country.getCountryPopulation()){
            country.setInfectedPeople(country.getInfectedPeople() + people);
            country.setVirusCases(country.getVirusCases() + people);
            country.setUnInfectedPeople(country.getUnInfectedPeople() - country.getInfectedPeople());
        }else if(country.getInfectedPeople() == 0){
            country.setInfectedPeople(people);
            country.setVirusCases(people);
            country.setUnInfectedPeople(country.getUnInfectedPeople() - country.getInfectedPeople());
        }
    }

    public long changeInfectedByUpgrades(long infectedPeople){
        long infectedPeopleAfterUpgrades = infectedPeople;
        for (Upgrade upgrade : player.getUpgrades()){
            if(upgrade.getName().equals("upgradeBirdMaskOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 10;
            }else if(upgrade.getName().equals("upgradeBirdMaskTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 20;
            }else if(upgrade.getName().equals("upgradeBirdMaskThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 30;
            }
            if(upgrade.getName().equals("upgradeBatMaskOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 10;
            }else if(upgrade.getName().equals("upgradeBatMaskTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 20;
            }else if(upgrade.getName().equals("upgradeBatMaskThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 30;
            }
            if(upgrade.getName().equals("upgradeInsectMaskOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 50;
            }else if(upgrade.getName().equals("upgradeInsectMaskTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 150;
            }else if(upgrade.getName().equals("upgradeInsectMaskThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 250;
            }
            if(upgrade.getName().equals("upgradeMaskWearingOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 10250;
            }else if(upgrade.getName().equals("upgradeMaskWearingTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 25250;
            }else if(upgrade.getName().equals("upgradeMaskWearingThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 50250;
            }
            if(upgrade.getName().equals("upgradePureWaterOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 1250;
            }else if(upgrade.getName().equals("upgradePureWaterTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 5250;
            }else if(upgrade.getName().equals("upgradePureWaterThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 10250;
            }
            if(upgrade.getName().equals("upgradePureBloodOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 1250;
            }else if(upgrade.getName().equals("upgradePureBloodTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 2250;
            }else if(upgrade.getName().equals("upgradePureBloodThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 3250;
            }
            if(upgrade.getName().equals("upgradePureAirOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 10250;
            }else if(upgrade.getName().equals("upgradePureAirTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 30250;
            }else if(upgrade.getName().equals("upgradePureAirThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 50250;
            }
            if(upgrade.getName().equals("upgradeRodentMaskOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 1050;
            }else if(upgrade.getName().equals("upgradeRodentMaskTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 2250;
            }else if(upgrade.getName().equals("upgradeRodentMaskThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 3250;
            }
            if(upgrade.getName().equals("upgradeDisinfectionBombOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 1020;
            }else if(upgrade.getName().equals("upgradeDisinfectionBombTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 2020;
            }else if(upgrade.getName().equals("upgradeDisinfectionBombThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 3020;
            }
            if(upgrade.getName().equals("upgradeWorldQuarantineOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 20250;
            }else if(upgrade.getName().equals("upgradeWorldQuarantineTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 50250;
            }else if(upgrade.getName().equals("upgradeWorldQuarantineThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 120250;
            }
            if(upgrade.getName().equals("upgradeWorldQuarantineOne") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 10250;
            }else if(upgrade.getName().equals("upgradeWorldQuarantineTwo") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 20250;
            }else if(upgrade.getName().equals("upgradeWorldQuarantineThree") && upgrade.isStatus()){
                infectedPeopleAfterUpgrades -= 30250;
            }
        }
        return infectedPeopleAfterUpgrades;
    }

    public void doVirusMigration(JLabel background,int startX, int startY, int targetX, int targetY, String transport) throws InterruptedException {
        TransportAnimation myRunnable = new TransportAnimation(background, startX, startY, targetX, targetY, transport);
        Thread thread = new Thread(myRunnable);
        thread.start();
    }




}

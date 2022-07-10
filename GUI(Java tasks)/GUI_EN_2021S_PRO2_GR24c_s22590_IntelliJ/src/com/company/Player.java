package com.company;

import java.util.ArrayList;

public class Player {
    private int points;
    private int money;
    private String playerName;
    private ArrayList<Upgrade> upgrades = new ArrayList<>();;

    public Player() {
        points = 0;
        money = 50000;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(ArrayList<Upgrade> upgrades) {
        this.upgrades = upgrades;
    }

    public void setOneUpgrade(Upgrade upgrade) {
        upgrades.add(upgrade);
    }

    public static Player init(){
        Player player = new Player();
        Upgrade upgrade1 = new Upgrade("upgradeBirdMaskOne", "30% of birds wear masks", 3000);
        Upgrade upgrade2 = new Upgrade("upgradeBirdMaskTwo", "60% of birds wear masks", 15000);
        Upgrade upgrade3 = new Upgrade("upgradeBirdMaskThree", "90% of birds wear masks", 300000);
        Upgrade upgrade4 = new Upgrade("upgradeBatMaskOne", "30% of bats wear masks", 2000);
        Upgrade upgrade5 = new Upgrade("upgradeBatMaskTwo", "60% of bats wear masks", 40000);
        Upgrade upgrade6 = new Upgrade("upgradeBatMaskThree", "90% of bats wear masks", 200000);
        Upgrade upgrade7 = new Upgrade("upgradeInsectMaskOne", "30% of insects wear masks", 5000);
        Upgrade upgrade8 = new Upgrade("upgradeInsectMaskTwo", "60% of insects wear masks", 50000);
        Upgrade upgrade9 = new Upgrade("upgradeInsectMaskThree", "90% of insects wear masks", 450000);
        Upgrade upgrade10 = new Upgrade("upgradeMaskWearingOne", "30% of people start wearing masks", 100000);
        Upgrade upgrade11 = new Upgrade("upgradeMaskWearingTwo", "60% of people start wearing masks", 500000);
        Upgrade upgrade12 = new Upgrade("upgradeMaskWearingThree", "90% of people start wearing masks", 800000);
        Upgrade upgrade13 = new Upgrade("upgradePureWaterOne", "30% of infected water doesn't infect people", 30000);
        Upgrade upgrade14 = new Upgrade("upgradePureWaterTwo", "60% of infected water doesn't infect people", 120000);
        Upgrade upgrade15 = new Upgrade("upgradePureWaterThree", "90% of infected water doesn't infect people", 560000);
        Upgrade upgrade16 = new Upgrade("upgradePureBloodOne", "30% of infected blood doesn't infect people", 200000);
        Upgrade upgrade17 = new Upgrade("upgradePureBloodTwo", "60% of infected blood doesn't infect people", 400000);
        Upgrade upgrade18 = new Upgrade("upgradePureBloodThree", "90% of infected blood doesn't infect people", 1200000);
        Upgrade upgrade19 = new Upgrade("upgradePureAirOne", "The virus is less airborne", 500000);
        Upgrade upgrade20 = new Upgrade("upgradePureAirTwo", "The virus is much less airborne", 1000000);
        Upgrade upgrade21 = new Upgrade("upgradePureAirThree", "The virus is hardly transmitted by air", 1500000);
        Upgrade upgrade22 = new Upgrade("upgradeRodentMaskOne", "30% of rodents start wearing masks", 25000);
        Upgrade upgrade23 = new Upgrade("upgradeRodentMaskTwo", "60% of rodents start wearing masks", 150000);
        Upgrade upgrade24 = new Upgrade("upgradeRodentMaskThree", "90% of rodents start wearing masks", 460000);
        Upgrade upgrade25 = new Upgrade("upgradeDisinfectionBombOne", "30% of people now have clean hands", 120000);
        Upgrade upgrade26 = new Upgrade("upgradeDisinfectionBombTwo", "60% of people now have clean hands", 240000);
        Upgrade upgrade27 = new Upgrade("upgradeDisinfectionBombThree", "90% of people now have clean hands", 360000);
        Upgrade upgrade28 = new Upgrade("upgradeWorldQuarantineOne", "First stage of world quarantine", 100000);
        Upgrade upgrade29 = new Upgrade("upgradeWorldQuarantineTwo", "Second stage of world quarantine", 500000);
        Upgrade upgrade30 = new Upgrade("upgradeWorldQuarantineThree", "Third stage of world quarantine", 1500000);
        Upgrade upgrade31 = new Upgrade("upgradeSocialDistanceOne", "In cities, the social distance is 1 meter", 50000);
        Upgrade upgrade32 = new Upgrade("upgradeSocialDistanceTwo", "In cities, the social distance is 2 meters", 100000);
        Upgrade upgrade33 = new Upgrade("upgradeSocialDistanceThree", "In cities, the social distance is 3 meters", 150000);
        Upgrade upgrade34 = new Upgrade("startVaccine", "Start work on a vaccine", 50000);
        Upgrade upgrade35 = new Upgrade("upgradeSpeedVaccineOne", "Up speed of vaccine by 0.25", 500000);
        Upgrade upgrade36 = new Upgrade("upgradeSpeedVaccineTwo", "Up speed of vaccine by 0.5", 1000000);
        Upgrade upgrade37 = new Upgrade("upgradeSpeedVaccineThree", "Up speed of vaccine by 0.75", 1500000);

        player.setOneUpgrade(upgrade1);
        player.setOneUpgrade(upgrade2);
        player.setOneUpgrade(upgrade3);
        player.setOneUpgrade(upgrade4);
        player.setOneUpgrade(upgrade5);
        player.setOneUpgrade(upgrade6);
        player.setOneUpgrade(upgrade7);
        player.setOneUpgrade(upgrade8);
        player.setOneUpgrade(upgrade9);
        player.setOneUpgrade(upgrade10);
        player.setOneUpgrade(upgrade11);
        player.setOneUpgrade(upgrade12);
        player.setOneUpgrade(upgrade13);
        player.setOneUpgrade(upgrade14);
        player.setOneUpgrade(upgrade15);
        player.setOneUpgrade(upgrade16);
        player.setOneUpgrade(upgrade17);
        player.setOneUpgrade(upgrade18);
        player.setOneUpgrade(upgrade19);
        player.setOneUpgrade(upgrade20);
        player.setOneUpgrade(upgrade21);
        player.setOneUpgrade(upgrade22);
        player.setOneUpgrade(upgrade23);
        player.setOneUpgrade(upgrade24);
        player.setOneUpgrade(upgrade25);
        player.setOneUpgrade(upgrade26);
        player.setOneUpgrade(upgrade27);
        player.setOneUpgrade(upgrade28);
        player.setOneUpgrade(upgrade29);
        player.setOneUpgrade(upgrade30);
        player.setOneUpgrade(upgrade31);
        player.setOneUpgrade(upgrade32);
        player.setOneUpgrade(upgrade33);
        player.setOneUpgrade(upgrade34);
        player.setOneUpgrade(upgrade35);
        player.setOneUpgrade(upgrade36);
        player.setOneUpgrade(upgrade37);

        return player;
    }

}

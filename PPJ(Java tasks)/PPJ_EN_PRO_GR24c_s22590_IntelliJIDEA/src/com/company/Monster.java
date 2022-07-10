package com.company;

public class Monster extends Object {
    public int monsterDamage;
    public int monsterHealth;
    public int monsterMaxHealth;

    public Monster() {
        objectName = "monster";
        objectCoordinateX = 0;
        objectCoordinateY = 0;
        monsterDamage = 15;
        monsterMaxHealth = 55;
        monsterHealth = monsterMaxHealth;
    }

    public Monster(String objectName, int objectCoordinateX, int objectCoordinateY) {
        super(objectName, objectCoordinateX, objectCoordinateY);
    }

    public void attackPlayer(Player player) {
        int playerHealth = player.getPlayerHealth();
        int dealtDMG = (int) (15 + Math.random() * (int) (monsterDamage));
        playerHealth -= dealtDMG;
        System.out.println("The monster has dealt " + dealtDMG + " damage to you");
        player.setPlayerHealth(playerHealth);
        player.checkPlayerHealth();
    }

    public void checkMonsterHealth(Player player) {
        if (monsterHealth <= 0) {
            int givenPoints = 1;
            int givenCoins = (int) (1 + Math.random() * 2);
            System.out.println("Monster dead, you recieved " + givenPoints + " points and " + givenCoins + " coins");
            int playerPoints = player.getPlayerPoints();
            int playerCoins = player.getPlayerCoins();
            int numOfDiedMonsters = player.getNumOfDiedMonsters();
            playerPoints += givenPoints;
            playerCoins += givenCoins;
            numOfDiedMonsters += 1;
            player.setPlayerPoints(playerPoints);
            player.setPlayerCoins(playerCoins);
            player.setNumOfDiedMonsters(numOfDiedMonsters);
            System.out.println("Points: " + playerPoints + "\nCoins: " + playerCoins);
        } else {
            attackPlayer(player);
        }
    }

    public int getMonsterDamage() {
        return monsterDamage;
    }

    public void setMonsterDamage(int monsterDamage) {
        this.monsterDamage = monsterDamage;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    public void setMonsterHealth(int monsterHealth) {
        this.monsterHealth = monsterHealth;
    }
}

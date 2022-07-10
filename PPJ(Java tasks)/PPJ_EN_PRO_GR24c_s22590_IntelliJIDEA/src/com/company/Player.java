package com.company;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.Math;

import static com.company.Main.*;

public class Player {
    private String playerName;
    private int playerHealth;
    private int playerMaxHealth;
    private int playerMana;
    private int playerMaxMana;
    private int playerPoints;
    private int playerCoins;
    private String playerWeapon;
    private String[] playerArmor;
    private int playerDamage;
    private int playerNumOfShurikens;
    private int playerCoordinateX;
    private int playerCoordinateY;
    private int numOfPointsToSpawnBoss;
    private int numOfDiedMonsters;

    public Player(String playerName, int playerMaxHealth, int playerMaxMana, int playerCoins, int playerDamage) {
        this.playerName = playerName;
        this.playerMaxHealth = playerMaxHealth;
        this.playerMaxMana = playerMaxMana;
        playerHealth = playerMaxHealth;
        playerMana = playerMaxMana;
        playerPoints = 0;
        playerWeapon = "nothing";
        playerArmor = new String[]{"nothing", "nothing", "nothing", "nothing"};
        this.playerCoins = playerCoins;
        this.playerCoordinateX = 1;
        this.playerCoordinateY = 1;
        this.playerDamage = playerDamage;
        playerNumOfShurikens = 0;
        numOfPointsToSpawnBoss = 0;
    }

    public Player() {
        playerName = "player1";
        playerMaxHealth = 100;
        playerHealth = 100;
        playerMaxMana = 100;
        playerMana = 100;
        playerPoints = 0;
        playerWeapon = "nothing";
        playerArmor = new String[]{"nothing", "nothing", "nothing", "nothing"};
        playerCoins = 0;
        playerCoordinateX = 1;
        playerCoordinateY = 1;
        playerDamage = 1;
        playerNumOfShurikens = 0;
        numOfPointsToSpawnBoss = 0;
        numOfDiedMonsters = 0;
    }

    public String toString() {
        return "Username: " + playerName + "\nHealth: " + playerHealth + "/" + playerMaxHealth + "\nMana: " + playerMana + "/" + playerMaxMana + "\nCoins: " + playerCoins + "\nDamage: " + playerDamage + "\nNumber of shurikens: " + playerNumOfShurikens + "\nPoints to spawn boss: " + numOfPointsToSpawnBoss + ", You have " + playerPoints + " points \n" + "Monster killed: " + numOfDiedMonsters + "\nWeapon: " + playerWeapon + "\n" + showPlayerArmor() + checkCoordinates();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public int getPlayerMaxHealth() {
        return playerMaxHealth;
    }

    public void setPlayerMaxHealth(int playerMaxHealth) {
        this.playerMaxHealth = playerMaxHealth;
    }

    public int getPlayerMana() {
        return playerMana;
    }

    public void setPlayerMana(int playerMana) {
        this.playerMana = playerMana;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    public int getPlayerCoins() {
        return playerCoins;
    }

    public void setPlayerCoins(int playerCoins) {
        this.playerCoins = playerCoins;
    }

    public int getPlayerCoordinateX() {
        return playerCoordinateX;
    }

    public void setPlayerCoordinateX(int playerCoordinateX) {
        this.playerCoordinateX = playerCoordinateX;
    }

    public int getPlayerCoordinateY() {
        return playerCoordinateY;
    }

    public void setPlayerCoordinateY(int playerCoordinateY) {
        this.playerCoordinateY = playerCoordinateY;
    }

    public int getPlayerDamage() {
        return playerDamage;
    }

    public void setPlayerDamage(int playerDamage) {
        this.playerDamage = playerDamage;
    }

    public int getPlayerNumOfShurikens() {
        return playerNumOfShurikens;
    }

    public void setPlayerNumOfShurikens(int playerNumOfShurikens) {
        this.playerNumOfShurikens = playerNumOfShurikens;
    }

    public int getPlayerMaxMana() {
        return playerMaxMana;
    }

    public void setPlayerMaxMana(int playerMaxMana) {
        this.playerMaxMana = playerMaxMana;
    }

    public int getNumOfPointsToSpawnBoss() {
        return numOfPointsToSpawnBoss;
    }

    public void setNumOfPointsToSpawnBoss(int numOfPointsToSpawnBoss) {
        this.numOfPointsToSpawnBoss = numOfPointsToSpawnBoss;
    }

    public int getNumOfDiedMonsters() {
        return numOfDiedMonsters;
    }

    public void setNumOfDiedMonsters(int numOfDiedMonsters) {
        this.numOfDiedMonsters = numOfDiedMonsters;
    }

    public String getPlayerWeapon() {
        return playerWeapon;
    }

    public void setPlayerWeapon(String playerWeapon) {
        this.playerWeapon = playerWeapon;
    }

    public String[] getPlayerArmor() {
        return playerArmor;
    }

    public void setPlayerArmor(String[] playerArmor) {
        this.playerArmor = playerArmor;
    }

    public void placePlayerOnMap(Map map) {
        playerCoordinateX = (int) Math.ceil((float) map.getMapSize() / 2);
        playerCoordinateY = (int) Math.ceil((float) map.getMapSize() / 2);
        System.out.println(checkCoordinates());
    }

    public String checkCoordinates() {
        return "Your coordinates are: " + "(X: " + playerCoordinateX + ", Y: " + playerCoordinateY + ")";
    }

    public String showPlayerArmor() {
        return "Your armor: \nHelmet: " + playerArmor[0] + "\nChestplate: " + playerArmor[1] + "\nLeggings: " + playerArmor[2] + "\nBoots: " + playerArmor[3] + "\n";
    }

    public void playerMoves(Map map) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Where do you want to move?");
        System.out.print("Options:\nNorth\nWest\nEast\nSouth\nor write Stats for your statistic\nYour choice: ");
        String userReply = scanner.next().toLowerCase();

        int mapSize = map.getMapSize();

        switch (userReply) {
            case "north" -> {
                if (playerCoordinateY < mapSize) {
                    playerCoordinateY += 1;
                    System.out.println("You made one step to the north side!");
                    System.out.println(checkCoordinates());
                } else {
                    System.out.println("You are standing on the edge of the map, action is impossible");
                }

            }
            case "west" -> {
                if (playerCoordinateX > 1) {
                    playerCoordinateX -= 1;
                    System.out.println("You made one step to the west side!");
                    System.out.println(checkCoordinates());
                } else {
                    System.out.println("You are standing on the edge of the map, action is impossible");
                }
            }
            case "east" -> {
                if (playerCoordinateX < mapSize) {
                    playerCoordinateX += 1;
                    System.out.println("You made one step to the east side!");
                    System.out.println(checkCoordinates());
                } else {
                    System.out.println("You are standing on the edge of the map, action is impossible");
                }
            }
            case "south" -> {
                if (playerCoordinateY > 1) {
                    playerCoordinateY -= 1;
                    System.out.println("You made one step to the south side!");
                    System.out.println(checkCoordinates());
                } else {
                    System.out.println("You are standing on the edge of the map, action is impossible");
                }
            }

            case "stats" -> System.out.println(toString());
        }
    }

    public void checkPlayerHealth() {
        if (playerHealth <= 0) {
            playerHealth = 0;
            System.out.println("YOU DIED!\nYour statistic " + toString());
            menu();
        }
    }

    public void fightVsMonster(Player player, Monster monster, Map map, boolean fightStatus) {
        Scanner scanner = new Scanner(System.in);
        while (playerHealth > 0 && monster.monsterHealth > 0 && fightStatus) {
            System.out.println("Your health: " + getPlayerHealth() + "/" + getPlayerMaxHealth() + "\nYour mana: " + getPlayerMana() + "/" + getPlayerMaxMana() + "\n1. Quick hit(70% of your DMG, 5% to miss)\n2. Medium hit(100% of your DMG, 15% to miss)\n3. Powerful hit(140% of your DMG, 30% to miss)\n" +
                    "4. Fireball(costs 30 mana, deal 35-55 DMG)\n5. Chidori (costs 50 mana, deal 50-70 DMG) \n6. Millennium of pain(costs 100 mana, deal 100-135 DMG)\n7. Throw shuriken(costs 1 shuriken, deal 30-40 DMG)\n8. Heal skill(costs 45 mana, heal yourself by 40-60 HP)\n" +
                    "9. Leave fight(costs 10 mana, deal you 50% of monster DMG)\nYour action: ");
            try {
                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1 -> quickHit(player, monster);
                    case 2 -> mediumHit(player, monster);
                    case 3 -> powerfulHit(player, monster);
                    case 4 -> throwFireball(player, monster, map, true);
                    case 5 -> useChidoriSkill(player, monster, map, true);
                    case 6 -> useMillenniumOfPainSkill(player, monster, map, true);
                    case 7 -> throwShuriken(player, monster, map, true);
                    case 8 -> useHealSkill(player, monster, map, true);
                    case 9 -> {
                        fightStatus = leaveFight(player, monster, map, fightStatus);
                        fightVsMonster(player, monster, map, fightStatus);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.print("Your selection can only be an integer!\n");
                fightVsMonster(player, monster, map, true);
            }

        }


    }

    public void quickHit(Player player, Monster monster) {
        int missChance = (int) (1 + Math.random() * 100);
        if (missChance > 0 && missChance <= 5) {
            System.out.println("You missed :(");
            monster.checkMonsterHealth(player);
        } else {
            int dealtDMG = (int) (playerDamage * 0.7);
            monster.monsterHealth -= dealtDMG;
            if (monster.monsterHealth <= 0) {
                System.out.println("You dealt " + dealtDMG + " DMG to monster");
                System.out.println("Monster health: " + 0 + "/" + monster.monsterMaxHealth);
            } else {
                System.out.println("You dealt " + dealtDMG + " to monster");
                System.out.println("Monster health: " + monster.monsterHealth + "/" + monster.monsterMaxHealth);
            }
            monster.checkMonsterHealth(player);
        }
    }

    public void mediumHit(Player player, Monster monster) {
        int missChance = (int) (1 + Math.random() * 100);
        if (missChance > 0 && missChance <= 15) {
            System.out.println("You missed :(");
            monster.checkMonsterHealth(player);
        } else {
            int dealtDMG = playerDamage;
            monster.monsterHealth -= dealtDMG;
            if (monster.monsterHealth <= 0) {
                System.out.println("You dealt " + dealtDMG + " DMG to monster");
                System.out.println("Monster health: " + 0 + "/" + monster.monsterMaxHealth);
            } else {
                System.out.println("You dealt " + dealtDMG + " to monster");
                System.out.println("Monster health: " + monster.monsterHealth + "/" + monster.monsterMaxHealth);
            }
            monster.checkMonsterHealth(player);
        }
    }

    public void powerfulHit(Player player, Monster monster) {
        int missChance = (int) (1 + Math.random() * 100);
        if (missChance > 0 && missChance <= 30) {
            System.out.println("You missed :(");
            monster.checkMonsterHealth(player);
        } else {
            int dealtDMG = (int) (playerDamage * 1.4);
            monster.monsterHealth -= dealtDMG;
            if (monster.monsterHealth <= 0) {
                System.out.println("You dealt " + dealtDMG + " DMG to monster");
                System.out.println("Monster health: " + 0 + "/" + monster.monsterMaxHealth);
            } else {
                System.out.println("You dealt " + dealtDMG + " to monster");
                System.out.println("Monster health: " + monster.monsterHealth + "/" + monster.monsterMaxHealth);
            }
            monster.checkMonsterHealth(player);
        }
    }

    public void throwFireball(Player player, Monster monster, Map map, Boolean fightStatus) {
        if (playerMana >= 30) {
            int dealtDMG = 35 + (int) (Math.random() * 20);
            playerMana -= 30;
            monster.monsterHealth -= dealtDMG;
            if (monster.monsterHealth <= 0) {
                System.out.println("You dealt " + dealtDMG + " DMG to monster");
                System.out.println("Monster health: " + 0 + "/" + monster.monsterMaxHealth);
            } else {
                System.out.println("You dealt " + dealtDMG + " to monster");
                System.out.println("Monster health: " + monster.monsterHealth + "/" + monster.monsterMaxHealth);
            }
            monster.checkMonsterHealth(player);
        } else {
            System.out.println("Not enough mana!");
            fightVsMonster(player, monster, map, fightStatus);
        }
    }

    public void useChidoriSkill(Player player, Monster monster, Map map, Boolean fightStatus) {
        if (playerMana >= 50) {
            int dealtDMG = 50 + (int) (Math.random() * 20);
            playerMana -= 50;
            monster.monsterHealth -= dealtDMG;
            if (monster.monsterHealth <= 0) {
                System.out.println("You dealt " + dealtDMG + " DMG to monster");
                System.out.println("Monster health: " + 0 + "/" + monster.monsterMaxHealth);
            } else {
                System.out.println("You dealt " + dealtDMG + " to monster");
                System.out.println("Monster health: " + monster.monsterHealth + "/" + monster.monsterMaxHealth);
            }
            monster.checkMonsterHealth(player);
        } else {
            System.out.println("Not enough mana!");
            fightVsMonster(player, monster, map, fightStatus);
        }
    }

    public void useMillenniumOfPainSkill(Player player, Monster monster, Map map, Boolean fightStatus) {
        if (playerMana >= 100) {
            int dealtDMG = 100 + (int) (Math.random() * 35);
            playerMana -= 100;
            monster.monsterHealth -= dealtDMG;
            if (monster.monsterHealth <= 0) {
                System.out.println("You dealt " + dealtDMG + " DMG to monster");
                System.out.println("Monster health: " + 0 + "/" + monster.monsterMaxHealth);
            } else {
                System.out.println("You dealt " + dealtDMG + " to monster");
                System.out.println("Monster health: " + monster.monsterHealth + "/" + monster.monsterMaxHealth);
            }
            monster.checkMonsterHealth(player);
        } else {
            System.out.println("Not enough mana!");
            fightVsMonster(player, monster, map, fightStatus);
        }
    }

    public void throwShuriken(Player player, Monster monster, Map map, Boolean fightStatus) {
        if (playerNumOfShurikens >= 1) {
            int dealtDMG = 30 + (int) (Math.random() * 10);
            playerNumOfShurikens -= 1;
            monster.monsterHealth -= dealtDMG;
            if (monster.monsterHealth <= 0) {
                System.out.println("You dealt " + dealtDMG + " DMG to monster");
                System.out.println("Monster health: " + 0 + "/" + monster.monsterMaxHealth);
            } else {
                System.out.println("You dealt " + dealtDMG + " to monster");
                System.out.println("Monster health: " + monster.monsterHealth + "/" + monster.monsterMaxHealth);
            }
            monster.checkMonsterHealth(player);
        } else {
            System.out.println("Not enough shurikens!");
            fightVsMonster(player, monster, map, fightStatus);
        }
    }

    public void useHealSkill(Player player, Monster monster, Map map, Boolean fightStatus) {
        if (playerMana >= 45) {
            if (playerHealth <= playerMaxHealth) {
                int numOfHeal = 40 + (int) (Math.random() * 20);
                playerMana -= 45;
                playerHealth += numOfHeal;
                if (playerHealth > playerMaxHealth) {
                    player.setPlayerHealth(playerMaxHealth);
                }
                System.out.println("Monster health: " + monster.monsterHealth + "/" + monster.monsterMaxHealth);

            } else {
                System.out.println("Monster health: " + monster.monsterHealth + "/" + monster.monsterMaxHealth);
                System.out.println("Your health is full");
                fightVsMonster(player, monster, map, fightStatus);
            }
        } else {
            System.out.println("Not enough mana!");
            fightVsMonster(player, monster, map, fightStatus);
        }
    }

    public boolean leaveFight(Player player, Monster monster, Map map, Boolean fightStatus) {
        if (playerMana >= 10) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Where do you want to run? ");
            System.out.print("Options:\nNorth\nWest\nEast\nSouth\nor write Stats for your statistic\nYour choice: ");
            String userReply = scanner.next().toLowerCase();
            int mapSize = map.getMapSize();
            switch (userReply) {
                case "north" -> {
                    if (playerCoordinateY < mapSize) {
                        playerCoordinateY += 1;
                        System.out.println("You made one step to the north side!");
                        System.out.println(checkCoordinates());
                    } else {
                        System.out.println("You are standing on the edge of the map, action is impossible");
                    }

                }
                case "west" -> {
                    if (playerCoordinateX > 1) {
                        playerCoordinateX -= 1;
                        System.out.println("You made one step to the west side!");
                        System.out.println(checkCoordinates());
                    } else {
                        System.out.println("You are standing on the edge of the map, action is impossible");
                    }
                }
                case "east" -> {
                    if (playerCoordinateX < mapSize) {
                        playerCoordinateX += 1;
                        System.out.println("You made one step to the east side!");
                        System.out.println(checkCoordinates());
                    } else {
                        System.out.println("You are standing on the edge of the map, action is impossible");
                    }
                }
                case "south" -> {
                    if (playerCoordinateY > 1) {
                        playerCoordinateY -= 1;
                        System.out.println("You made one step to the south side!");
                        System.out.println(checkCoordinates());
                    } else {
                        System.out.println("You are standing on the edge of the map, action is impossible");
                    }
                }
            }
            playerHealth -= (int) (monster.monsterDamage * 0.5);
            setPlayerHealth(playerHealth);
            System.out.println("Your health: " + playerHealth + "/" + playerMaxHealth);
            return false;
        } else {
            System.out.println("Not enough mana!");
            fightVsMonster(player, monster, map, fightStatus);
            return true;
        }
    }

    public void buyItemFromNPC(NPC trader, Player player, HealthPotion[] healthPotions, ManaPotion[] manaPotions, Trap[] traps, Shuriken[] shurikens, Coin[] coins, Monster[] monsters, NPC[] traders, Boss boss, Map map) {
        System.out.println("What item do you want to buy?");
        Scanner scanner = new Scanner(System.in);
        switch (trader.getNpcName()) {
            case "Health Master" -> {
                int userChoice = scanner.nextInt();
                Item[] items = trader.getItems();
                switch (userChoice) {
                    case 1 -> {
                        if (!player.getPlayerArmor()[0].equals(items[0].itemName)) {
                            if (player.getPlayerCoins() >= items[0].itemPrice) {
                                String playerHelmet = items[0].getItemName();
                                String playerChestplate = player.getPlayerArmor()[1];
                                String playerLeggins = player.getPlayerArmor()[2];
                                String playerBoots = player.getPlayerArmor()[3];
                                player.setPlayerMaxHealth(playerMaxHealth + 40);
                                player.setPlayerCoins(playerCoins - items[0].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[0].itemPrice + " coins for " + items[0].itemName);
                                System.out.println("Good choice, stranger! " + items[0].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 2 -> {
                        if (!player.getPlayerArmor()[1].equals(items[1].itemName)) {
                            if (player.getPlayerCoins() >= items[1].itemPrice) {
                                String playerHelmet = player.getPlayerArmor()[0];
                                String playerChestplate = items[1].getItemName();
                                String playerLeggins = player.getPlayerArmor()[2];
                                String playerBoots = player.getPlayerArmor()[3];
                                player.setPlayerMaxHealth(playerMaxHealth + 80);
                                player.setPlayerCoins(playerCoins - items[1].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[1].itemPrice + " coins for " + items[1].itemName);
                                System.out.println("Good choice, stranger! " + items[1].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 3 -> {
                        if (!player.getPlayerArmor()[2].equals(items[2].itemName)) {
                            if (player.getPlayerCoins() >= items[2].itemPrice) {
                                String playerHelmet = player.getPlayerArmor()[0];
                                String playerChestplate = player.getPlayerArmor()[1];
                                String playerLeggins = items[2].getItemName();
                                String playerBoots = player.getPlayerArmor()[3];
                                player.setPlayerMaxHealth(playerMaxHealth + 65);
                                player.setPlayerCoins(playerCoins - items[2].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[2].itemPrice + " coins for " + items[2].itemName);
                                System.out.println("Good choice, stranger! " + items[2].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 4 -> {
                        if (!player.getPlayerArmor()[3].equals(items[3].itemName)) {
                            if (player.getPlayerCoins() >= items[3].itemPrice) {
                                String playerHelmet = player.getPlayerArmor()[0];
                                String playerChestplate = player.getPlayerArmor()[1];
                                String playerLeggins = player.getPlayerArmor()[2];
                                String playerBoots = items[3].getItemName();
                                player.setPlayerMaxHealth(playerMaxHealth + 35);
                                player.setPlayerCoins(playerCoins - items[3].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[3].itemPrice + " coins for " + items[3].itemName);
                                System.out.println("Good choice, stranger! " + items[3].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 5 -> {
                        if (player.playerHealth == playerMaxHealth) {
                            System.out.println("Your health is full now! You don't need to buy " + items[4].itemName + ". Your health: " + player.getPlayerHealth() + "/" + playerMaxHealth);
                        } else {
                            if (player.getPlayerCoins() >= items[4].itemPrice) {
                                player.setPlayerCoins(playerCoins - items[4].itemPrice);
                                System.out.println("You spend " + items[4].itemPrice + " coins for " + items[4].itemName);
                                System.out.println("Good choice, stranger! " + items[4].itemName + " will help you during your travels!");
                                if (player.getPlayerMaxHealth() - playerHealth < 40) {
                                    player.setPlayerHealth(player.getPlayerMaxHealth());
                                    System.out.println("Your health is full now! Your health: " + playerHealth + "/" + playerMaxHealth);
                                } else {
                                    player.setPlayerHealth(playerHealth + 40);
                                    System.out.println("Your health increased by 40. Your health: " + playerHealth + "/" + playerMaxHealth);
                                }
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        }
                    }
                }
            }

            case "Mana Master" -> {
                int userChoice = scanner.nextInt();
                Item[] items = trader.getItems();
                switch (userChoice) {
                    case 1 -> {
                        if (!player.getPlayerArmor()[0].equals(items[0].itemName)) {
                            if (player.getPlayerCoins() >= items[0].itemPrice) {
                                String playerHelmet = items[0].getItemName();
                                String playerChestplate = player.getPlayerArmor()[1];
                                String playerLeggins = player.getPlayerArmor()[2];
                                String playerBoots = player.getPlayerArmor()[3];
                                player.setPlayerMaxMana(playerMaxMana + 40);
                                player.setPlayerCoins(playerCoins - items[0].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[0].itemPrice + " coins for " + items[0].itemName);
                                System.out.println("Good choice, stranger! " + items[0].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 2 -> {
                        if (!player.getPlayerArmor()[1].equals(items[1].itemName)) {
                            if (player.getPlayerCoins() >= items[1].itemPrice) {
                                String playerHelmet = player.getPlayerArmor()[0];
                                String playerChestplate = items[1].getItemName();
                                String playerLeggins = player.getPlayerArmor()[2];
                                String playerBoots = player.getPlayerArmor()[3];
                                player.setPlayerMaxMana(playerMaxMana + 80);
                                player.setPlayerCoins(playerCoins - items[1].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[1].itemPrice + " coins for " + items[1].itemName);
                                System.out.println("Good choice, stranger! " + items[1].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 3 -> {
                        if (!player.getPlayerArmor()[2].equals(items[2].itemName)) {
                            if (player.getPlayerCoins() >= items[2].itemPrice) {
                                String playerHelmet = player.getPlayerArmor()[0];
                                String playerChestplate = player.getPlayerArmor()[1];
                                String playerLeggins = items[2].getItemName();
                                String playerBoots = player.getPlayerArmor()[3];
                                player.setPlayerMaxMana(playerMaxMana + 65);
                                player.setPlayerCoins(playerCoins - items[2].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[2].itemPrice + " coins for " + items[2].itemName);
                                System.out.println("Good choice, stranger! " + items[2].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 4 -> {
                        if (!player.getPlayerArmor()[3].equals(items[3].itemName)) {
                            if (player.getPlayerCoins() >= items[3].itemPrice) {
                                String playerHelmet = player.getPlayerArmor()[0];
                                String playerChestplate = player.getPlayerArmor()[1];
                                String playerLeggins = player.getPlayerArmor()[2];
                                String playerBoots = items[3].getItemName();
                                player.setPlayerMaxMana(playerMaxMana + 35);
                                player.setPlayerCoins(playerCoins - items[3].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[3].itemPrice + " coins for " + items[3].itemName);
                                System.out.println("Good choice, stranger! " + items[3].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 5 -> {
                        if (player.playerMana == playerMaxMana) {
                            System.out.println("Your mana is full now! You don't need to buy " + items[4].itemName + ". Your mana: " + player.getPlayerMana() + "/" + playerMaxMana);
                        } else {
                            if (player.getPlayerCoins() >= items[4].itemPrice) {
                                player.setPlayerCoins(playerCoins - items[4].itemPrice);
                                System.out.println("You spend " + items[4].itemPrice + " coins for " + items[4].itemName);
                                System.out.println("Good choice, stranger! " + items[4].itemName + " will help you during your travels!");
                                if (player.getPlayerMaxMana() - playerMana < 40) {
                                    player.setPlayerMana(player.getPlayerMaxMana());
                                    System.out.println("Your mana is full now! Your mana: " + playerMana + "/" + playerMaxMana);
                                } else {
                                    player.setPlayerHealth(playerHealth + 40);
                                    System.out.println("Your mana increased by 40. Your mana: " + playerMana + "/" + playerMaxMana);
                                }
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        }
                    }
                }
            }

            case "Damage Master" -> {
                int userChoice = scanner.nextInt();
                Item[] items = trader.getItems();
                switch (userChoice) {
                    case 1 -> {
                        if (!player.getPlayerArmor()[0].equals(items[0].itemName)) {
                            if (player.getPlayerCoins() >= items[0].itemPrice) {
                                String playerHelmet = items[0].getItemName();
                                String playerChestplate = player.getPlayerArmor()[1];
                                String playerLeggins = player.getPlayerArmor()[2];
                                String playerBoots = player.getPlayerArmor()[3];
                                player.setPlayerDamage(playerDamage + 10);
                                player.setPlayerCoins(playerCoins - items[0].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[0].itemPrice + " coins for " + items[0].itemName);
                                System.out.println("Good choice, stranger! " + items[0].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 2 -> {
                        if (!player.getPlayerArmor()[1].equals(items[1].itemName)) {
                            if (player.getPlayerCoins() >= items[1].itemPrice) {
                                String playerHelmet = player.getPlayerArmor()[0];
                                String playerChestplate = items[1].getItemName();
                                String playerLeggins = player.getPlayerArmor()[2];
                                String playerBoots = player.getPlayerArmor()[3];
                                player.setPlayerDamage(playerDamage + 30);
                                player.setPlayerCoins(playerCoins - items[1].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[1].itemPrice + " coins for " + items[1].itemName);
                                System.out.println("Good choice, stranger! " + items[1].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 3 -> {
                        if (!player.getPlayerArmor()[2].equals(items[2].itemName)) {
                            if (player.getPlayerCoins() >= items[2].itemPrice) {
                                String playerHelmet = player.getPlayerArmor()[0];
                                String playerChestplate = player.getPlayerArmor()[1];
                                String playerLeggins = items[2].getItemName();
                                String playerBoots = player.getPlayerArmor()[3];
                                player.setPlayerDamage(playerDamage + 20);
                                player.setPlayerCoins(playerCoins - items[2].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[2].itemPrice + " coins for " + items[2].itemName);
                                System.out.println("Good choice, stranger! " + items[2].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 4 -> {
                        if (!player.getPlayerArmor()[3].equals(items[3].itemName)) {
                            if (player.getPlayerCoins() >= items[3].itemPrice) {
                                String playerHelmet = player.getPlayerArmor()[0];
                                String playerChestplate = player.getPlayerArmor()[1];
                                String playerLeggins = player.getPlayerArmor()[2];
                                String playerBoots = items[3].getItemName();
                                player.setPlayerDamage(playerDamage + 10);
                                player.setPlayerCoins(playerCoins - items[3].itemPrice);
                                String[] playerNewArmor = {playerHelmet, playerChestplate, playerLeggins, playerBoots};
                                player.setPlayerArmor(playerNewArmor);
                                System.out.println("You spend " + items[3].itemPrice + " coins for " + items[3].itemName);
                                System.out.println("Good choice, stranger! " + items[3].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 5 -> {
                        if (player.getPlayerCoins() >= items[4].itemPrice) {
                            player.setPlayerCoins(playerCoins - items[4].itemPrice);
                            System.out.println("You spend " + items[4].itemPrice + " coins for " + items[4].itemName);
                            System.out.println("Good choice, stranger! " + items[4].itemName + " will help you during your travels!");
                            player.setPlayerNumOfShurikens(player.getPlayerNumOfShurikens() + 5);
                            System.out.println("Your shurikens: " + playerNumOfShurikens);
                        } else {
                            System.out.println("Not enough coins!");
                        }
                    }
                }
            }

            case "Weapon Master" -> {
                int userChoice = scanner.nextInt();
                Item[] items = trader.getItems();
                switch (userChoice) {
                    case 1 -> {
                        if (!player.getPlayerWeapon().equals(items[0].itemName)) {
                            if (player.getPlayerCoins() >= items[0].itemPrice) {
                                player.setPlayerDamage(playerDamage + 25);
                                player.setPlayerCoins(playerCoins - items[0].itemPrice);
                                player.setPlayerWeapon(items[0].getItemName());
                                System.out.println("You spend " + items[0].itemPrice + " coins for " + items[0].itemName);
                                System.out.println("Good choice, stranger! " + items[0].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 2 -> {
                        if (!player.getPlayerWeapon().equals(items[1].itemName)) {
                            if (player.getPlayerCoins() >= items[1].itemPrice) {
                                player.setPlayerMana(playerMaxMana + 125);
                                player.setPlayerCoins(playerCoins - items[1].itemPrice);
                                player.setPlayerWeapon(items[1].itemName);
                                player.setPlayerMana(playerMaxMana);
                                System.out.println("You spend " + items[1].itemPrice + " coins for " + items[1].itemName + " and your mana was refilled!");
                                System.out.println("Good choice, stranger! " + items[1].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 3 -> {
                        if (!player.getPlayerWeapon().equals(items[2].itemName)) {
                            if (player.getPlayerCoins() >= items[2].itemPrice) {
                                player.setPlayerDamage(playerDamage + 45);
                                player.setPlayerCoins(playerCoins - items[2].itemPrice);
                                player.setPlayerWeapon(items[2].itemName);
                                System.out.println("You spend " + items[2].itemPrice + " coins for " + items[2].itemName);
                                System.out.println("Good choice, stranger! " + items[2].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                    case 4 -> {
                        if (!player.getPlayerArmor()[3].equals(items[3].itemName)) {
                            if (player.getPlayerCoins() >= items[3].itemPrice) {
                                player.setPlayerMaxMana(playerMaxMana + 250);
                                player.setPlayerCoins(playerCoins - items[3].itemPrice);
                                player.setPlayerWeapon(items[3].itemName);
                                player.setPlayerMana(getPlayerMaxMana());
                                System.out.println("You spend " + items[3].itemPrice + " coins for " + items[3].itemName + " and your mana was refilled!");
                                System.out.println("Good choice, stranger! " + items[3].itemName + " will help you during your travels!");
                            } else {
                                System.out.println("Not enough coins!");
                            }
                        } else {
                            System.out.println("Sorry, but you already have this item.");
                        }
                    }
                }
            }

            case "All-seeing Master" -> {
                int userChoice = scanner.nextInt();
                Item[] items = trader.getItems();
                switch (userChoice) {
                    case 1 -> {
                        if (playerCoins >= items[0].itemPrice) {
                            player.setPlayerCoins(playerCoins - items[0].itemPrice);
                            System.out.println("Coordinates of health potions: ");
                            for (HealthPotion healthPotion : healthPotions) {
                                if (healthPotion != null) {
                                    System.out.println(healthPotion.checkCoordinates());
                                }
                            }
                        } else {
                            System.out.println("Not enough coins!");
                        }
                    }
                    case 2 -> {
                        if (playerCoins >= items[1].itemPrice) {
                            player.setPlayerCoins(playerCoins - items[1].itemPrice);
                            System.out.println("Coordinates of mana potions: ");
                            for (ManaPotion manaPotion : manaPotions) {
                                if (manaPotion != null) {
                                    System.out.println(manaPotion.checkCoordinates());
                                }
                            }
                        } else {
                            System.out.println("Not enough coins!");
                        }
                    }
                    case 3 -> {
                        if (playerCoins >= items[2].itemPrice) {
                            player.setPlayerCoins(playerCoins - items[2].itemPrice);
                            System.out.println("Coordinates of traps: ");
                            for (Trap trap : traps) {
                                if (trap != null) {
                                    System.out.println(trap.checkCoordinates());
                                }
                            }
                        } else {
                            System.out.println("Not enough coins!");
                        }
                    }
                    case 4 -> {
                        if (playerCoins >= items[3].itemPrice) {
                            player.setPlayerCoins(playerCoins - items[3].itemPrice);
                            System.out.println("Coordinates of shuriken bags: ");
                            for (Shuriken shuriken : shurikens) {
                                if (shuriken != null) {
                                    System.out.println(shuriken.checkCoordinates());
                                }
                            }
                        } else {
                            System.out.println("Not enough coins!");
                        }
                    }
                    case 5 -> {
                        if (playerCoins >= items[4].itemPrice) {
                            player.setPlayerCoins(playerCoins - items[4].itemPrice);
                            System.out.println("Coordinates of coins: ");
                            for (Coin coin : coins) {
                                if (coin != null) {
                                    System.out.println(coin.checkCoordinates());
                                }
                            }
                        } else {
                            System.out.println("Not enough coins!");
                        }
                    }
                    case 6 -> {
                        if (playerCoins >= items[5].itemPrice) {
                            player.setPlayerCoins(playerCoins - items[5].itemPrice);
                            System.out.println("Coordinates of monsters: ");
                            for (Monster monster : monsters) {
                                if (monster != null) {
                                    System.out.println(monster.checkCoordinates());
                                }
                            }
                        } else {
                            System.out.println("Not enough coins!");
                        }
                    }
                    case 7 -> {
                        if (playerCoins >= items[6].itemPrice) {
                            player.setPlayerCoins(playerCoins - items[6].itemPrice);
                            System.out.println("Coordinates of everything: ");
                            for (HealthPotion healthPotion : healthPotions) {
                                if (healthPotion != null) {
                                    System.out.println(healthPotion.checkCoordinates());
                                }
                            }
                            for (ManaPotion manaPotion : manaPotions) {
                                if (manaPotion != null) {
                                    System.out.println(manaPotion.checkCoordinates());
                                }
                            }
                            for (Trap trap : traps) {
                                if (trap != null) {
                                    System.out.println(trap.checkCoordinates());
                                }
                            }
                            for (Shuriken shuriken : shurikens) {
                                if (shuriken != null) {
                                    System.out.println(shuriken.checkCoordinates());
                                }
                            }
                            for (Coin coin : coins) {
                                if (coin != null) {
                                    System.out.println(coin.checkCoordinates());
                                }
                            }
                            for (Monster monster : monsters) {
                                if (monster != null) {
                                    System.out.println(monster.checkCoordinates());
                                }
                            }
                        } else {
                            System.out.println("Not enough coins!");
                        }
                    }
                    case 8 -> {
                        if (playerCoins >= items[7].itemPrice) {
                            System.out.println("Where do you want to teleport?");
                            System.out.print("Enter X:");
                            int chosenXCoordinate = scanner.nextInt();
                            System.out.print("Enter Y:");
                            int chosenYCoordinate = scanner.nextInt();
                            if (chosenXCoordinate <= map.getMapSize() && chosenXCoordinate >= 1 && chosenYCoordinate <= map.getMapSize() && chosenYCoordinate >= 1) {
                                player.setPlayerCoins(playerCoins - items[7].itemPrice);
                                player.setPlayerCoordinateX(chosenXCoordinate);
                                player.setPlayerCoordinateY(chosenYCoordinate);
                                System.out.println("Congratulations! You were teleported to coordinates X: " + chosenXCoordinate + ", Y: " + chosenYCoordinate);
                                checkObjectsCoordinates(player, coins, traps, healthPotions, manaPotions, monsters, map, shurikens, traders, boss);
                            } else {
                                System.out.println("Coordinates are out of the map! Try next time!");
                            }
                        } else {
                            System.out.println("Not enough coins!");
                        }
                    }
                }
            }

        }
    }

    public void checkPointsForBossSpawn(Map map, Boss boss, NPC[] traders) {
        if (playerPoints >= numOfPointsToSpawnBoss) {
            System.out.println("Congratulations, " + playerName + "! You collected required number of points!\nNow you can find the boss and defeat him to win the game!");
            boolean isSpawnedOnPlayer;
            boolean isSpawnedOnTrader;
            boolean isSpawnedOnObject;
            do {
                isSpawnedOnObject = false;
                boss.placeObjectOnMap(map);
                isSpawnedOnPlayer = boss.objectCoordinateX == playerCoordinateX && boss.objectCoordinateY == playerCoordinateY;
                for (int i = 0, j = 1; i < traders.length; i++, j++) {
                    isSpawnedOnTrader = boss.objectCoordinateX == traders[i].objectCoordinateX && boss.objectCoordinateY == traders[i].objectCoordinateY;
                    if (isSpawnedOnTrader) {
                        isSpawnedOnObject = true;
                    }
                }
            } while (isSpawnedOnObject || isSpawnedOnPlayer);
            System.out.println("The Boss appeared on the map");
            System.out.println("UNKNOWN: Ha-ha-ha, " + playerName + ", you killed all of my monsters, but you can't beat me!!!\n" + playerName + ": I don't know who you are, but I will find and destroy you!\nUNKNOWN: Heh... We will see...");
        }
    }
}


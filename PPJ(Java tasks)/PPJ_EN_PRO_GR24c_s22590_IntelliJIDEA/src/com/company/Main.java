package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, you are playing in Alex Podolich game");
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu:\n1. Start game\n2. Exit game");
        System.out.print("Your choice: ");
        try {
            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1 -> startGame();
                case 2 -> {
                    System.out.println("Thanks for playing! Come again!");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Chose 1 or 2!");
                    menu();
                }
            }
        } catch (InputMismatchException e) {
            System.out.print("Your selection can only be an integer!\n");
            menu();
        }
    }

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Greetings wanderer! You came to play my game, but I don't know your name ...\nEnter name: ");
        String playerName = scanner.next();
        System.out.println("Hello, " + playerName + ", my name is Marquizy and I will be with you throughout your adventure!\nNow I want to ask you about what difficulty of adventure do you want and please select size of the map");
        Map map = new Map(choseDifficulty(), choseSizeOfMap());
        new Player();
        Player player = switch (map.getMapDifficulty()) {
            case "Easy" -> new Player(playerName, 200, 200, 20, 30);
            case "Normal" -> new Player(playerName, 150, 150, 15, 35);
            case "Hard" -> new Player(playerName, 125, 125, 10, 40);
            case "Impossible" -> new Player(playerName, 100, 100, 5, 45);
            default -> new Player();
        };
        System.out.println("You choose " + map.getMapDifficulty() + " difficulty and " + map.getMapSize() + "x" + map.getMapSize() + " size map, now are ready to play. Good Luck and Have Fun, " + player.getPlayerName() + "!");
        int numOfCoins = 0, numOfShurikens = 0, numOfTraps = 0, numOfHealthPotions = 0, numOfMonsters = 0, numOfManaPotions = 0;
        switch (map.getMapSize()) {
            case 5 -> {
                switch (map.getMapDifficulty()) {
                    case "Easy" -> {
                        numOfCoins = 10;
                        numOfTraps = 3;
                        numOfShurikens = 5;
                        numOfHealthPotions = 9;
                        numOfManaPotions = 7;
                        numOfMonsters = 5;
                    }

                    case "Normal" -> {
                        numOfCoins = 7;
                        numOfTraps = 4;
                        numOfShurikens = 4;
                        numOfHealthPotions = 6;
                        numOfManaPotions = 6;
                        numOfMonsters = 6;
                    }

                    case "Hard" -> {
                        numOfCoins = 5;
                        numOfTraps = 5;
                        numOfShurikens = 3;
                        numOfHealthPotions = 4;
                        numOfManaPotions = 5;
                        numOfMonsters = 7;
                    }

                    case "Impossible" -> {
                        numOfCoins = 4;
                        numOfTraps = 6;
                        numOfShurikens = 2;
                        numOfHealthPotions = 3;
                        numOfManaPotions = 4;
                        numOfMonsters = 9;
                    }
                }

            }
            case 7 -> {
                switch (map.getMapDifficulty()) {
                    case "Easy" -> {
                        numOfCoins = 13;
                        numOfTraps = 8;
                        numOfShurikens = 10;
                        numOfHealthPotions = 13;
                        numOfManaPotions = 11;
                        numOfMonsters = 8;
                    }

                    case "Normal" -> {
                        numOfCoins = 10;
                        numOfTraps = 10;
                        numOfShurikens = 9;
                        numOfHealthPotions = 10;
                        numOfManaPotions = 10;
                        numOfMonsters = 10;
                    }

                    case "Hard" -> {
                        numOfCoins = 8;
                        numOfTraps = 12;
                        numOfShurikens = 7;
                        numOfHealthPotions = 8;
                        numOfManaPotions = 7;
                        numOfMonsters = 11;
                    }

                    case "Impossible" -> {
                        numOfCoins = 7;
                        numOfTraps = 13;
                        numOfShurikens = 5;
                        numOfHealthPotions = 6;
                        numOfManaPotions = 6;
                        numOfMonsters = 13;
                    }
                }
            }
            case 9 -> {
                switch (map.getMapDifficulty()) {
                    case "Easy" -> {
                        numOfCoins = 15;
                        numOfTraps = 12;
                        numOfShurikens = 13;
                        numOfHealthPotions = 15;
                        numOfManaPotions = 13;
                        numOfMonsters = 13;
                    }

                    case "Normal" -> {
                        numOfCoins = 13;
                        numOfTraps = 13;
                        numOfShurikens = 12;
                        numOfHealthPotions = 13;
                        numOfManaPotions = 12;
                        numOfMonsters = 15;
                    }

                    case "Hard" -> {
                        numOfCoins = 11;
                        numOfTraps = 14;
                        numOfShurikens = 10;
                        numOfHealthPotions = 11;
                        numOfManaPotions = 10;
                        numOfMonsters = 16;
                    }

                    case "Impossible" -> {
                        numOfCoins = 10;
                        numOfTraps = 15;
                        numOfShurikens = 8;
                        numOfHealthPotions = 8;
                        numOfManaPotions = 8;
                        numOfMonsters = 17;
                    }
                }
            }
            case 15 -> {
                switch (map.getMapDifficulty()) {
                    case "Easy" -> {
                        numOfCoins = 65;
                        numOfTraps = 20;
                        numOfShurikens = 35;
                        numOfHealthPotions = 35;
                        numOfManaPotions = 30;
                        numOfMonsters = 25;
                    }

                    case "Normal" -> {
                        numOfCoins = 50;
                        numOfTraps = 30;
                        numOfShurikens = 30;
                        numOfHealthPotions = 30;
                        numOfManaPotions = 28;
                        numOfMonsters = 30;
                    }

                    case "Hard" -> {
                        numOfCoins = 45;
                        numOfTraps = 40;
                        numOfShurikens = 25;
                        numOfHealthPotions = 25;
                        numOfManaPotions = 23;
                        numOfMonsters = 40;
                    }

                    case "Impossible" -> {
                        numOfCoins = 40;
                        numOfTraps = 50;
                        numOfShurikens = 20;
                        numOfHealthPotions = 20;
                        numOfManaPotions = 17;
                        numOfMonsters = 50;
                    }
                }
            }
        }
        Coin[] coins = new Coin[numOfCoins];
        Shuriken[] shurikens = new Shuriken[numOfShurikens];
        Trap[] traps = new Trap[numOfTraps];
        HealthPotion[] healthPotions = new HealthPotion[numOfHealthPotions];
        ManaPotion[] manaPotions = new ManaPotion[numOfManaPotions];
        Monster[] monsters = new Monster[numOfMonsters];
        NPC[] traders = new NPC[5];
        Boss boss = new Boss();
        gameplay(player, map, coins, shurikens, traps, healthPotions, manaPotions, monsters, traders, boss);
    }

    public static String choseDifficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Difficulty: ");
        System.out.println("1. Easy(200 HP, 200 MP, 15 gold by start, player DMG 40)");
        System.out.println("2. Normal(125 HP, 150 MP, 5 gold by start, player DMG 30)");
        System.out.println("3. Hard(100 HP, 125 MP, 0 gold by start, player DMG 20)");
        System.out.println("4. Impossible(75 HP, 100 MP, 0 gold by start, player DMG 10)");
        System.out.println("5. Back to Menu");
        System.out.print("Your choice: ");
        String userChoiceDif = scanner.next();
        switch (userChoiceDif) {
            case "1" -> {
                return "Easy";
            }

            case "2" -> {
                return "Normal";
            }

            case "3" -> {
                return "Hard";
            }

            case "4" -> {
                return "Impossible";
            }

            case "5" -> menu();
            default -> System.out.println("Choose 1-5");
        }
        return "Easy";
    }

    public static int choseSizeOfMap() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Size of map: ");
        System.out.println("1. 5x5 blocks");
        System.out.println("2. 7x7 blocks");
        System.out.println("3. 9x9 blocks");
        System.out.println("4. 15x15 blocks");
        System.out.println("5. Back to Menu");
        System.out.print("Your choice: ");
        String userChoiceMapSize = scanner.next();
        switch (userChoiceMapSize) {
            case "1" -> {
                return 5;
            }

            case "2" -> {
                return 7;
            }

            case "3" -> {
                return 9;
            }

            case "4" -> {
                return 15;
            }

            case "5" -> menu();
            default -> System.out.println("Choose 1-5");
        }
        return 5;
    }

    public static void gameplay(Player player, Map map, Coin[] coins, Shuriken[] shurikens, Trap[] traps, HealthPotion[] healthPotions, ManaPotion[] manaPotions, Monster[] monsters, NPC[] traders, Boss boss) {
        player.placePlayerOnMap(map);
        player.setNumOfPointsToSpawnBoss(coins.length + monsters.length);
        createAndPlaceObjects(map, coins, shurikens, traps, healthPotions, manaPotions, monsters, traders);
        System.out.println("Your stats: ");
        System.out.println(player.toString());
        while (player.getPlayerHealth() > 0) {
            if (boss.objectCoordinateX == 0 && boss.objectCoordinateY == 0) {
                player.checkPointsForBossSpawn(map, boss, traders);
            }
            player.playerMoves(map);
            checkObjectsCoordinates(player, coins, traps, healthPotions, manaPotions, monsters, map, shurikens, traders, boss);
            checkCoordinatesForBoss(player, boss, map);
        }
        player.setPlayerHealth(0);
        System.out.println("Thanks for playing!!!\nYour stats: ");
        System.out.println(player.toString());
    }

    public static void checkObjectsCoordinates(Player player, Coin[] coins, Trap[] traps, HealthPotion[] healthPotions, ManaPotion[] manaPotions, Monster[] monsters, Map map, Shuriken[] shurikens, NPC[] traders, Boss boss) {
        checkCoordinatesForCoins(player, coins);
        checkCoordinatesForTraps(player, traps);
        checkCoordinatesForHealthPotions(player, healthPotions);
        checkCoordinatesForManaPotions(player, manaPotions);
        checkCoordinatesForMonsters(player, monsters, map);
        checkCoordinatesForShurikens(player, shurikens);
        checkCoordinatesForTraders(player, coins, traps, healthPotions, manaPotions, monsters, shurikens, traders, boss, map);
    }

    public static void createAndPlaceObjects(Map map, Coin[] coins, Shuriken[] shurikens, Trap[] traps, HealthPotion[] healthPotions, ManaPotion[] manaPotions, Monster[] monsters, NPC[] traders) {
        for (int i = 0; i < coins.length; i++) {
            coins[i] = new Coin();
            coins[i].placeObjectOnMap(map);
        }

        for (int i = 0; i < shurikens.length; i++) {
            shurikens[i] = new Shuriken();
            shurikens[i].placeObjectOnMap(map);
        }

        for (int i = 0; i < traps.length; i++) {
            traps[i] = new Trap();
            traps[i].placeObjectOnMap(map);
        }

        for (int i = 0; i < healthPotions.length; i++) {
            healthPotions[i] = new HealthPotion();
            healthPotions[i].placeObjectOnMap(map);
        }

        for (int i = 0; i < manaPotions.length; i++) {
            manaPotions[i] = new ManaPotion();
            manaPotions[i].placeObjectOnMap(map);
        }

        for (int i = 0; i < monsters.length; i++) {
            monsters[i] = new Monster();
            monsters[i].placeObjectOnMap(map);
        }

        createAndPlaceNPC(traders, map);

    }

    public static void createAndPlaceNPC(NPC[] traders, Map map) {
        Item[] itemsTrader1 = new Item[]{new Item("Dragon Helmet", 7, "+40 MAX HP"), new Item("Dragon Chestplate", 12, "+80 MAX HP"), new Item("Dragon Leggins", 10, "+65 MAX HP"), new Item("Dragon Boots", 5, "+35 MAX HP"), new Item("Health Potion", 3, "Heal 40 HP")};
        Item[] itemsTrader2 = new Item[]{new Item("Magic Helmet", 7, "+40 MAX MP"), new Item("Magic Chestplate", 12, "+80 MAX MP"), new Item("Magic Leggins", 10, "+65 MAX MP"), new Item("Magic Boots", 5, "+35 MAX MP"), new Item("Mana Potion", 3, "Refill 40 MP")};
        Item[] itemsTrader3 = new Item[]{new Item("Knight's Helmet", 8, "+10 DMG"), new Item("Knight's Chestplate", 13, "+30 DMG"), new Item("Knight's Leggins", 11, "+20 DMG"), new Item("Knight's Boots", 6, "+10 DMG"), new Item("5 Shurikens", 8, "+5 Shurikens")};
        Item[] itemsTrader4 = new Item[]{new Item("Knight's Sword", 12, "+25 DMG"), new Item("Magic Wand", 10, "+125 MAX MP and refill all mana"), new Item("Dragon Sword", 24, "+45 DMG"), new Item("Universe Magic Wand", 20, "+250 MAX MP and refill all mana")};
        Item[] itemsTrader5 = new Item[]{new Item("Coordinates of Health Potions", 12, "Coordinates of Health Potions"), new Item("Coordinates of Mana Potions", 12, "Coordinates of Mana Potions"), new Item("Coordinates of Traps", 15, "Coordinates of Traps"), new Item("Coordinates of ShurikenBags", 15, "Coordinates of ShurikenBags"),
                new Item("Coordinates of Coins", 25, "Coordinates of Coins"), new Item("Coordinates of Monsters", 25, "Coordinates of Monsters"), new Item("Coordinates of Everything", 65, "Coordinates of Everything"), new Item("Teleport", 10, "Teleport")};
        for (int i = 0, j = 1; i < traders.length; i++, j++) {
            traders[i] = new NPC(j);
            traders[i].placeObjectOnMap(map);
        }

        traders[0].setNpcName("Health Master");
        traders[0].setItems(itemsTrader1);
        traders[1].setNpcName("Mana Master");
        traders[1].setItems(itemsTrader2);
        traders[2].setNpcName("Damage Master");
        traders[2].setItems(itemsTrader3);
        traders[3].setNpcName("Weapon Master");
        traders[3].setItems(itemsTrader4);
        traders[4].setNpcName("All-seeing Master");
        traders[4].setItems(itemsTrader5);
    }

    public static void checkCoordinatesForCoins(Player player, Coin[] coins) {
        int playerCoins = player.getPlayerCoins();
        int playerPoints = player.getPlayerPoints();
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] != null) {
                if (coins[i].objectCoordinateX == player.getPlayerCoordinateX() && coins[i].objectCoordinateY == player.getPlayerCoordinateY()) {
                    int numOfFoundCoins = (int) (1 + Math.random() * 3);
                    playerCoins += numOfFoundCoins;
                    playerPoints += 1;
                    player.setPlayerCoins(playerCoins);
                    player.setPlayerPoints(playerPoints);
                    coins[i] = null;
                    System.out.println("You found coin(s), your money increased by " + numOfFoundCoins + " and points by 1!\nYour coins: " + player.getPlayerCoins() + "\nYour points: " + player.getPlayerPoints());
                }
            }
        }
    }

    public static void checkCoordinatesForShurikens(Player player, Shuriken[] shurikens) {
        int playerShurikens = player.getPlayerNumOfShurikens();
        for (int i = 0; i < shurikens.length; i++) {
            if (shurikens[i] != null) {
                if (shurikens[i].objectCoordinateX == player.getPlayerCoordinateX() && shurikens[i].objectCoordinateY == player.getPlayerCoordinateY()) {
                    int shurikensInsideBad = (int) (0 + Math.random() * 4);
                    playerShurikens += shurikensInsideBad;
                    player.setPlayerNumOfShurikens(playerShurikens);
                    shurikens[i] = null;
                    System.out.println("You found someone's backpack, and you found " + shurikensInsideBad + " shurikens inside! Your number of shurikens: " + player.getPlayerNumOfShurikens());
                }
            }
        }
    }

    public static void checkCoordinatesForTraps(Player player, Trap[] traps) {
        int playerHealth = player.getPlayerHealth();
        for (int i = 0; i < traps.length; i++) {
            if (traps[i] != null) {
                if (traps[i].objectCoordinateX == player.getPlayerCoordinateX() && traps[i].objectCoordinateY == player.getPlayerCoordinateY()) {
                    playerHealth -= 25;
                    player.setPlayerHealth(playerHealth);
                    traps[i] = null;
                    if (playerHealth < 0) {
                        player.setPlayerHealth(0);
                    }
                    System.out.println("You are trapped, your health are reduces by 25! Your health: " + player.getPlayerHealth());
                }
            }
        }
    }

    public static void checkCoordinatesForHealthPotions(Player player, HealthPotion[] healthPotions) {
        int playerHealth = player.getPlayerHealth();
        for (int i = 0; i < healthPotions.length; i++) {
            if (healthPotions[i] != null) {
                if (healthPotions[i].objectCoordinateX == player.getPlayerCoordinateX() && healthPotions[i].objectCoordinateY == player.getPlayerCoordinateY()) {
                    if (player.getPlayerMaxHealth() == playerHealth) {
                        System.out.println("You found health potion, but your health is full now! Your health: " + player.getPlayerHealth() + "/" + player.getPlayerMaxHealth());
                        healthPotions[i] = null;
                    } else if (player.getPlayerMaxHealth() - playerHealth < 25) {
                        playerHealth = player.getPlayerMaxHealth();
                        player.setPlayerHealth(playerHealth);
                        healthPotions[i] = null;
                        System.out.println("You found health potion, your health is full now! Your health: " + player.getPlayerHealth() + "/" + player.getPlayerMaxHealth());
                    } else {
                        playerHealth += 25;
                        player.setPlayerHealth(playerHealth);
                        healthPotions[i] = null;
                        System.out.println("You found health potion, your health are increased by 25! Your health: " + player.getPlayerHealth() + "/" + player.getPlayerMaxHealth());
                    }
                }
            }
        }
    }

    public static void checkCoordinatesForManaPotions(Player player, ManaPotion[] manaPotions) {
        int playerMana = player.getPlayerMana();
        for (int i = 0; i < manaPotions.length; i++) {
            if (manaPotions[i] != null) {
                if (manaPotions[i].objectCoordinateX == player.getPlayerCoordinateX() && manaPotions[i].objectCoordinateY == player.getPlayerCoordinateY()) {
                    if (player.getPlayerMaxMana() == playerMana) {
                        System.out.println("You found mana potion, but your mana is full now! Your mana: " + player.getPlayerMana());
                        manaPotions[i] = null;
                    } else if (player.getPlayerMaxMana() - playerMana < 25) {
                        playerMana = player.getPlayerMaxMana();
                        player.setPlayerMana(playerMana);
                        manaPotions[i] = null;
                        System.out.println("You found mana potion, your mana is full now! Your mana: " + player.getPlayerMana());
                    } else {
                        playerMana += 25;
                        player.setPlayerMana(playerMana);
                        manaPotions[i] = null;
                        System.out.println("You found mana potion, your mana are increased by 25! Your mana: " + player.getPlayerMana());
                    }

                }
            }
        }
    }

    public static void checkCoordinatesForMonsters(Player player, Monster[] monsters, Map map) {
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i] != null) {
                if (monsters[i].objectCoordinateX == player.getPlayerCoordinateX() && monsters[i].objectCoordinateY == player.getPlayerCoordinateY()) {
                    System.out.println("You met a monster!\nFight started:");
                    player.fightVsMonster(player, monsters[i], map, true);
                    if (monsters[i].monsterHealth <= 0) {
                        monsters[i] = null;
                    }
                }
            }
        }
    }

    public static void checkCoordinatesForTraders(Player player, Coin[] coins, Trap[] traps, HealthPotion[] healthPotions, ManaPotion[] manaPotions, Monster[] monsters, Shuriken[] shurikens, NPC[] traders, Boss boss, Map map) {
        Scanner scanner = new Scanner(System.in);
        for (NPC trader : traders) {
            if (trader.objectCoordinateX == player.getPlayerCoordinateX() && trader.objectCoordinateY == player.getPlayerCoordinateY()) {
                System.out.println("Your coins: " + player.getPlayerCoins());
                trader.showItemsOfTrader(trader);
                System.out.println("Options: \n1. Buy items from trader\n2. Move further\nYour choice: ");
                try {
                    int userChoice = scanner.nextInt();
                    switch (userChoice) {
                        case 1 -> {
                            player.buyItemFromNPC(trader, player, healthPotions, manaPotions, traps, shurikens, coins, monsters, traders, boss, map);
                            checkCoordinatesForTraders(player, coins, traps, healthPotions, manaPotions, monsters, shurikens, traders, boss, map);
                        }

                        case 2 -> {
                            if (boss.objectCoordinateX == 0 && boss.objectCoordinateY == 0) {
                                player.checkPointsForBossSpawn(map, boss, traders);
                            }
                            player.playerMoves(map);
                            checkObjectsCoordinates(player, coins, traps, healthPotions, manaPotions, monsters, map, shurikens, traders, boss);
                        }

                        default -> {
                            System.out.println("Chose 1 or 2!");
                            checkCoordinatesForTraders(player, coins, traps, healthPotions, manaPotions, monsters, shurikens, traders, boss, map);
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.print("Your selection can only be an integer!\n");
                    checkCoordinatesForTraders(player, coins, traps, healthPotions, manaPotions, monsters, shurikens, traders, boss, map);
                }

            }
        }
    }

    public static void checkCoordinatesForBoss(Player player, Boss boss, Map map) {
        if (boss.objectCoordinateX == player.getPlayerCoordinateX() && boss.objectCoordinateY == player.getPlayerCoordinateY()) {
            System.out.println("You found a BOSS!\nFinal fight started:");
            player.fightVsMonster(player, boss, map, true);
            if (boss.monsterHealth <= 0) {
                System.out.println("CONGRATULATIONS, " + player.getPlayerName() + "! You killed the boss!\nYOU WIN!");
                System.out.println("Your statistic: \n" + player.toString());
                menu();
            }
        }
    }
}

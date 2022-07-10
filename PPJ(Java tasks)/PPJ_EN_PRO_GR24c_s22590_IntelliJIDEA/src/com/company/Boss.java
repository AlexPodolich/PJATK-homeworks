package com.company;

import static com.company.Main.menu;

public class Boss extends Monster {

    public Boss() {
        objectName = "Boss";
        objectCoordinateX = 0;
        objectCoordinateY = 0;
        monsterDamage = 30;
        monsterMaxHealth = 500;
        monsterHealth = monsterMaxHealth;
    }

    public Boss(String objectName, int objectCoordinateX, int objectCoordinateY) {
        super(objectName, objectCoordinateX, objectCoordinateY);
    }

    @Override
    public void attackPlayer(Player player) {
        int missChance = (int) (1 + Math.random() * 100);
        int bossRandomAttack = (int) (1 + Math.random() * 100);
        int playerHealth = player.getPlayerHealth();
        if (missChance > 0 && missChance <= 10) {
            System.out.println("Boss missed :)");
        } else {
            int dealtDMG;
            if (bossRandomAttack > 0 && bossRandomAttack <= 25) {
                dealtDMG = bossWeakAttack();
            } else if (bossRandomAttack > 25 && bossRandomAttack <= 45) {
                dealtDMG = bossMediumAttack();
            } else if (bossRandomAttack > 45 && bossRandomAttack <= 60) {
                dealtDMG = bossStrongAttack();
            } else if (bossRandomAttack > 60 && bossRandomAttack <= 70) {
                dealtDMG = bossKamuiSkill();
            } else if (bossRandomAttack > 70 && bossRandomAttack <= 75) {
                dealtDMG = bossInfiniteTsukuyomiSkill();
            } else if (bossRandomAttack > 75 && bossRandomAttack <= 89) {
                dealtDMG = bossFreezeSkill();
                attackPlayer(player);
            } else if (bossRandomAttack > 89 && bossRandomAttack <= 94) {
                dealtDMG = bossUltraGiantRasenShuriken();
            } else if (bossRandomAttack > 94 && bossRandomAttack <= 99) {
                dealtDMG = bossHealSkill();
                if (monsterMaxHealth - monsterHealth < 100) {
                    monsterHealth = monsterMaxHealth;
                    System.out.println("Monster health refilled by 100/nMonster health: " + monsterHealth + "/" + monsterMaxHealth);
                } else {
                    monsterHealth += 100;
                    monsterHealth = monsterMaxHealth;
                    System.out.println("Monster health refilled by 100/nMonster health: " + monsterHealth + "/" + monsterMaxHealth);
                }
            } else {
                dealtDMG = bossInstantKill();
            }
            playerHealth -= dealtDMG;
            System.out.println("The boss has dealt " + dealtDMG + " damage to you");
            player.setPlayerHealth(playerHealth);
            player.checkPlayerHealth();
        }
    }

    @Override
    public void checkMonsterHealth(Player player) {
        if (monsterHealth <= 0) {
            System.out.println("CONGRATULATIONS, " + player.getPlayerName() + "! You killed the boss!\nYOU WIN!");
            System.out.println("Your statistic: \n" + player.toString());
            menu();
        } else {
            attackPlayer(player);
        }
    }

    public int bossWeakAttack() {
        int dealtDMG = (int) (10 + Math.random() * monsterDamage);
        System.out.println("Boss used his weak attack!");
        return dealtDMG;
    }

    public int bossMediumAttack() {
        int dealtDMG = (int) (25 + Math.random() * monsterDamage);
        System.out.println("Boss used his medium attack!");
        return dealtDMG;
    }

    public int bossStrongAttack() {
        int dealtDMG = (int) (40 + Math.random() * monsterDamage);
        System.out.println("Boss used his strong attack!");
        return dealtDMG;
    }

    public int bossKamuiSkill() {
        int dealtDMG = (int) (60 + Math.random() * 20);
        System.out.println("Boss used Kamui ninjutsu! Be careful!");
        return dealtDMG;
    }

    public int bossInfiniteTsukuyomiSkill() {
        int dealtDMG = (int) (5 + Math.random() * 100);
        System.out.println("Boss used Infinite Tsukuyomi genjutsu! Be careful!");
        return dealtDMG;
    }

    public int bossInstantKill() {
        int dealtDMG = 999;
        System.out.println("Boss was very lucky and instant killed you! So unlucky day:( ");
        return dealtDMG;
    }

    public int bossFreezeSkill() {
        int dealtDMG = 5;
        System.out.println("Boss used freezing magic! Now you can't attack him!");
        return dealtDMG;
    }

    public int bossUltraGiantRasenShuriken() {
        int dealtDMG = 75;
        System.out.println("Boss threw Ultra-Giant Rasen Shuriken into you! It must hurt...");
        return dealtDMG;
    }

    public int bossHealSkill() {
        int dealtDMG = 0;
        System.out.println("Boss used Heal skill! You have time to prepare powerful attack!");
        return dealtDMG;
    }

}

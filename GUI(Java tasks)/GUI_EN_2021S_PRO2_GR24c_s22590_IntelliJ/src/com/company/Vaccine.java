package com.company;

import javax.swing.*;

public class Vaccine implements Runnable{
    public static String vaccineDifficulty;
    public static double vaccineReadyPercent = 0;
    public static boolean isGameGoing = true;
    private Player player;

    public Vaccine(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        while (vaccineReadyPercent < 100 && isGameGoing){
            try{
                Thread.sleep(300);
                if(vaccineDifficulty.equals("Easy")){
                    vaccineReadyPercent += 0.5;
                }else if(vaccineDifficulty.equals("Normal")){
                    vaccineReadyPercent += 0.25;
                }else if(vaccineDifficulty.equals("Hard")){
                    vaccineReadyPercent += 0.1;
                }
                for (Upgrade upgrade : player.getUpgrades() ){
                    if(upgrade.getName().equals("upgradeSpeedVaccineOne") && upgrade.isStatus()){
                        Vaccine.vaccineReadyPercent += 0.25;
                    }else if(upgrade.getName().equals("upgradeSpeedVaccineTwo") && upgrade.isStatus()){
                        Vaccine.vaccineReadyPercent += 0.5;
                    }else if(upgrade.getName().equals("upgradeSpeedVaccineThree") && upgrade.isStatus()){
                        Vaccine.vaccineReadyPercent += 0.75; }
                }
            }catch (InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }
        if(vaccineReadyPercent >= 100){
            vaccineReadyPercent = 100;
            JOptionPane.showMessageDialog(null, "The vaccine is ready, wait a little while and the whole world will be cured of the virus!", "Inform Message", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}

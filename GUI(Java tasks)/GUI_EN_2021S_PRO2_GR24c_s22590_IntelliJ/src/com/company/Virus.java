package com.company;

public class Virus {
    private String virusDifficulty;
    private double spreadCoefficient;

    public Virus(String virusDifficulty) {
        if(virusDifficulty.equals("Easy")){
            spreadCoefficient = 1.1;
        }else if(virusDifficulty.equals("Normal")) {
            spreadCoefficient = 1.2;
        }else if(virusDifficulty.equals("Hard")) {
            spreadCoefficient = 1.3;
        }
    }

    public String getVirusDifficulty() {
        return virusDifficulty;
    }

    public void setVirusDifficulty(String virusDifficulty) {
        this.virusDifficulty = virusDifficulty;
    }

    public double getSpreadCoefficient() {
        return spreadCoefficient;
    }

    public void setSpreadCoefficient(double spreadCoefficient) {
        this.spreadCoefficient = spreadCoefficient;
    }
}

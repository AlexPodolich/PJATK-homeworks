package com.company;

public class TenantLetter {
    private String letter;
    private String type;

    public TenantLetter(String letter, String type) {
        this.letter = letter;
        this.type = type;
    }

    @Override
    public String toString() {
        return "TenantLetter{" +
                "letter='" + letter + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.company;

public class Map {
    private String mapDifficulty;
    private int mapSize;

    public Map(String mapDifficulty, int mapSize) {
        this.mapDifficulty = mapDifficulty;
        this.mapSize = mapSize;
    }

    public Map() {

    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public String getMapDifficulty() {
        return mapDifficulty;
    }

    public void setMapDifficulty(String mapDifficulty) {
        this.mapDifficulty = mapDifficulty;
    }
}

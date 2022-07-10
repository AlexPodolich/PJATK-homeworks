package com.company;

public class Person implements Comparable<Person>{
    private static int index = 1;
    private int place;
    private String name;
    private int points;

    public Person(String name, int points) {
        this.name = name;
        this.points = points;
        place = index++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return name + " with " + points + " points";
    }

    public int compareTo(Person person){
        if(this.points > person.getPoints()){
            return -1;
        }else if(this.points < person.getPoints()){
            return 1;
        }else{
            return 0;
        }
    }
}

package sample;

public class Person implements Comparable<Person>{
    private static int index = 1;
    private int place;
    private String name;
    private int points;

    public int getPoints() {
        return points;
    }

    public Person(String name, int points) {
        this.name = name;
        this.points = points;
        place = index++;
    }

    @Override
    public String toString() {
        return name + " with " + points + " points";
    }

    @Override
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

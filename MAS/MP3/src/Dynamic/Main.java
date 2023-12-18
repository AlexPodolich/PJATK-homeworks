package Dynamic;

import Project.ObjectPlus;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Administrator administrator = new Administrator(1, "String name", "String surname", "+380675329690");
        System.out.println(administrator);

        for(Map.Entry mp : ObjectPlus.getExtent().entrySet()){
            System.out.println(mp.getKey()+ " " +mp.getValue());
        }

        Security security = new Security(administrator, "patrolArea");
        System.out.println(security);

        for(Map.Entry mp : ObjectPlus.getExtent().entrySet()){
            System.out.println(mp.getKey()+ " " +mp.getValue());
        }

        CleaningStuff cleaningStuff = new CleaningStuff(security, "cleaningArea");
        System.out.println(cleaningStuff);

        for(Map.Entry mp : ObjectPlus.getExtent().entrySet()){
            System.out.println(mp.getKey()+ " " +mp.getValue());
        }

    }
}

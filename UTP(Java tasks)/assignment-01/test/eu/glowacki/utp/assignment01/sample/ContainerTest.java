package eu.glowacki.utp.assignment01.sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

public class ContainerTest {
    private MyContainer<Car, Integer> myContainer;
    private Car car1, car2, car3;
    private ArrayList<Car>arr;

    @Before
    public void before(){
        arr = new ArrayList<>();
        myContainer = new MyContainer<>();

        car1 = new Car(260);
        car2 = new Car(300);
        car3 = new Car(120);

        arr.add(car1);
        arr.add(car2);
        arr.add(car3);

        myContainer.addElement(car1);
        myContainer.addElement(car2);
        myContainer.addElement(car3);

        Assert.assertEquals(car1, myContainer.getElement(0));
        Assert.assertEquals(car2, myContainer.getElement(1));
        Assert.assertEquals(car3, myContainer.getElement(2));
    }
    @Test
    public void aggregateAllElements(){
        int a = myContainer.aggregateAllElements();
        int a1 = car1.aggregate(car2.aggregate(car3.aggregate(null)));
        Assert.assertEquals(a,a1);
    }

    @Test
    public void cloneElementAtIndex() {
        Car clone = myContainer.cloneElementAtIndex(1);
        Car origin = myContainer.cloneElementAtIndex(1);
        Assert.assertEquals(origin.getCarSpeed(), clone.getCarSpeed());
        Assert.assertNotEquals(origin, clone);
    }
}

package eu.glowacki.utp.assignment01.sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTest {
    private static final int carSpeed = 270;

    private Car thisCar;

    @Before
    public void before() {
        thisCar = new Car(carSpeed);
        Assert.assertEquals(carSpeed, thisCar.getCarSpeed());
    }

    @Test
    public void aggregate() {
        int aggregate = thisCar.aggregate(null);
        Assert.assertEquals(carSpeed, aggregate);
        final int test = 5;
        Assert.assertEquals((int) (carSpeed + test), (int) (thisCar.aggregate(test)));

    }


    @Test
    public void deepClone() {
        Car carClone = thisCar.deepClone();
        Assert.assertEquals(thisCar.getCarSpeed(), carClone.getCarSpeed());
        Assert.assertNotEquals(thisCar, carClone);
    }


}

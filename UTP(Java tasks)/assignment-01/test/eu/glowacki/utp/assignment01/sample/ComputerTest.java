package eu.glowacki.utp.assignment01.sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComputerTest {

    private static final int computerID = 88;

    private Computer thisComp;

    @Before
    public void before(){
        thisComp = new Computer(computerID);
        Assert.assertEquals(computerID, thisComp.getCompID());
    }

    @Test
    public void aggregate(){
        int aggregate = thisComp.aggregate(null);
        Assert.assertEquals(computerID,aggregate);
        final int test = 5;
        Assert.assertEquals((int) (computerID+test), (int) (thisComp.aggregate(test)));

    }


    @Test
    public void deepClone(){
        Computer compClone = thisComp.deepClone();
        Assert.assertEquals(thisComp.getCompID(), compClone.getCompID());
        Assert.assertNotEquals(thisComp, compClone);
    }




}
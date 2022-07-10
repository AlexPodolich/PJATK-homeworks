package com.company;

import java.util.Date;

public class DateThread implements Runnable{
    public static int currentDate = 0;
    public static boolean isGameGoing = true;

    @Override
    public void run() {
        while (isGameGoing){
            try{
                Thread.sleep(300);
                currentDate += 1;
                System.out.println("Current day: " + currentDate);
            }catch (InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }


    }

    public int getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(int currentDate) {
        this.currentDate = currentDate;
    }
}

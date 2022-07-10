package com.company;

import  java.util.Date;
import java.util.List;

public class DateThread implements Runnable{
    public static Date currentDate = new Date();

    @Override
    public void run() {
        while (true){
            try{
                long l =  currentDate.getTime();
                currentDate.setTime(l + 86400000);
                System.out.println(currentDate);
                Thread.sleep(5000);
            }catch (InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }


    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}

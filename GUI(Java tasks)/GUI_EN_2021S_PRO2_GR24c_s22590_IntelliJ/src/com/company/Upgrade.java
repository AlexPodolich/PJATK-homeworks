package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Upgrade implements Comparable<Upgrade>{
    private String name;
    private String description;
    private int price;
    private boolean status;
    private JButton btnBuy;

    public Upgrade(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        status = false;
        btnBuy = new JButton();
        btnBuy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               setStatus(true);
            }
        });
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public JButton getButton()
    {
        return btnBuy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int compareTo(Upgrade upgrade){
        if(this.getPrice() < upgrade.getPrice()){
            return -1;
        }else if(this.getPrice() > upgrade.getPrice()){
            return 1;
        }else{
            return 0;
        }
    }
}

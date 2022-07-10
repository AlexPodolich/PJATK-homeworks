package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TransportAnimation implements Runnable{

    JLabel background;
    int startX;
    int startY;
    int targetX;
    int targetY;
    String transport;


    public TransportAnimation(JLabel background, int startX, int startY, int targetX, int targetY, String transport) {
        this.background = background;
        this.startX = startX;
        this.startY = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.transport = transport;
    }

    public void run() {
        ImageIcon plane = new ImageIcon("resources/"+transport+".png");
        Image planeImage = plane.getImage(); // transform it
        Image newPlane = planeImage.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        plane = new ImageIcon(newPlane);  // transform it back
        JLabel jLabel = new JLabel(plane);
        jLabel.setBounds(startX, startY,25,25);
        background.add(jLabel);
        long startTime = System.currentTimeMillis();
        float progress = 0;
        Random rnd = new Random();
        float timeTravel = 0;
        switch (transport) {
            case "ship" -> timeTravel =(rnd.nextInt((5000 - 4000) + 1) + 4000);
            case "car" -> timeTravel = (rnd.nextInt((3500 - 2000) + 1) + 2000);
            case "plane" -> timeTravel = (rnd.nextInt((3500 - 1500) + 1) + 1500);
        }
        do{
            try {
                Thread.sleep(40);
                int x = jLabel.getX();
                int y = jLabel.getY();
                long duration = System.currentTimeMillis() - startTime;

                progress = (float) duration / timeTravel;

                if (progress > 1f) {
                    progress = 1f;
                    jLabel.setVisible(false);
                }
                x = startX + (int)Math.round((targetX - startX) * progress);
                y = startY + (int)Math.round((targetY - startY) * progress);
                jLabel.setLocation(x, y);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (progress < 1f);
        jLabel.setVisible(false);
    }
}

package com.company;

import javax.swing.*;
import java.awt.*;

public class MyLine extends JLabel {
    int x1;
    int y1;
    Color color;

    public MyLine(int x1, int y1,Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        g.drawLine(0, 0, x1, y1);
        g.dispose();
    }
}

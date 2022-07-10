package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChoseDiffWindow extends JFrame {

    JLabel text;
    JButton btnEasyDiff;
    JButton btnNormalDiff;
    JButton btnHardDiff;
    ChoseDiffWindow(){
        this.setLayout(null);
        this.setSize(400, 150);
        this.setVisible(true);
        this.setTitle("Plague Inc - Difficulty");

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                new Menu();
                e.getWindow().dispose();
            }
        });

        text = new JLabel("Choose difficulty for game: ");
        text.setBounds(15,0, 400, 50);
        text.setFont(new Font("MV Boli", Font.PLAIN, 28));
        text.setForeground(Color.BLACK);

        btnEasyDiff = new JButton("Easy");
        btnEasyDiff.setBounds(15,50, 100, 30);
        btnEasyDiff.setBackground(new Color(0,255,0));
        btnEasyDiff.setFont(new Font(null, Font.BOLD, 18));
        btnEasyDiff.setForeground(Color.BLACK);
        btnEasyDiff.addActionListener(e -> {
            this.dispose();
            new Game("Easy");
        });

        btnNormalDiff= new JButton("Normal");
        btnNormalDiff.setBounds(135,50, 100, 30);
        btnNormalDiff.setBackground(new Color(255, 255, 0));
        btnNormalDiff.setFont(new Font(null, Font.BOLD, 18));
        btnNormalDiff.setForeground(Color.BLACK);
        btnNormalDiff.addActionListener(e -> {
            this.dispose();
            new Game("Normal");
        });

        btnHardDiff= new JButton("Hard");
        btnHardDiff.setBounds(255,50, 100, 30);
        btnHardDiff.setBackground(new Color(255, 0, 0));
        btnHardDiff.setFont(new Font(null, Font.BOLD, 18));
        btnHardDiff.setForeground(Color.BLACK);
        btnHardDiff.addActionListener(e -> {
            this.dispose();
            new Game("Hard");
        });

        this.add(text);
        this.add(btnEasyDiff);
        this.add(btnNormalDiff);
        this.add(btnHardDiff);

        ImageIcon icon = new ImageIcon("resources/logo.png");
        this.setIconImage(icon.getImage());
    }
}

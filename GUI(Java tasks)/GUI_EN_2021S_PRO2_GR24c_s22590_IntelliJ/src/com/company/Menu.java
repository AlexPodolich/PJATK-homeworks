package com.company;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame{

    JButton startGameBtn;
    JButton bestScoreBtn;
    JButton exitBtn;
    JPanel btnPanel;
    Menu(){
        this.setTitle("Plague Inc - Menu");
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(320,350);
        this.setResizable(false);
        this.setBackground(new Color(123, 50, 250));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ImageIcon image1 = new ImageIcon("resources/Image1.jpg");
        Image image = image1.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        image1 = new ImageIcon(newimg);  // transform it back

        JLabel text1 = new JLabel("Plague Inc Game");
        text1.setIcon(image1);
        text1.setHorizontalTextPosition(JLabel.CENTER);
        text1.setVerticalTextPosition(JLabel.TOP);
        text1.setVerticalAlignment(JLabel.TOP);
        text1.setHorizontalAlignment(JLabel.CENTER);
        text1.setForeground(Color.BLACK);
        text1.setFont(new Font("MV Boli", Font.PLAIN, 36));
        text1.setBounds(0,0,300, 200);
        this.add(text1);

        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(3,1, 10, 10));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setBounds(75,200, 165, 100);
        this.add(btnPanel);


        startGameBtn = new JButton("Start Game");
        startGameBtn.setBounds(0, 0, 165, 25);
        startGameBtn.addActionListener(e -> {
            this.dispose();
            new ChoseDiffWindow();
        });
        startGameBtn.setHorizontalTextPosition(JButton.CENTER);
        startGameBtn.setFont(new Font("Comic Sans", Font.BOLD, 20));
        startGameBtn.setForeground(Color.BLACK);
        startGameBtn.setBackground(Color.GREEN);
        startGameBtn.setSize(165, 25);
        startGameBtn.setFocusable(false);

        btnPanel.add(startGameBtn);

        bestScoreBtn = new JButton("Best Score");
        bestScoreBtn.setBounds(0, 35, 165, 25);
        bestScoreBtn.addActionListener(e -> {
            this.dispose();
            new BestScore();
        });
        bestScoreBtn.setHorizontalTextPosition(JButton.CENTER);
        bestScoreBtn.setFont(new Font("Comic Sans", Font.BOLD, 20));
        bestScoreBtn.setForeground(Color.BLACK);
        bestScoreBtn.setBackground(Color.PINK);
        bestScoreBtn.setFocusable(false);

        btnPanel.add(bestScoreBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(0, 70, 165, 25);
        exitBtn.addActionListener(e -> {
            this.dispose();
            System.exit(0);
        });
        exitBtn.setHorizontalTextPosition(JButton.CENTER);
        exitBtn.setFont(new Font("Comic Sans", Font.BOLD, 20));
        exitBtn.setForeground(Color.BLACK);
        exitBtn.setBackground(Color.RED);
        exitBtn.setFocusable(false);

        btnPanel.add(exitBtn);


        ImageIcon icon = new ImageIcon("resources/logo.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(255, 255, 255));
    }

}

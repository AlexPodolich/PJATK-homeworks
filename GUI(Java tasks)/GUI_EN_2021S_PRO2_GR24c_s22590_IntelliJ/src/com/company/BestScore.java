package com.company;

import jdk.jshell.PersistentSnippet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class BestScore extends JFrame {

    JButton btnBackToMenu;
    JPanel btnPanel;
    JPanel listOfBestPanel;
    JList<Person> bestList;
    DefaultListModel<Person> dlm = new DefaultListModel<>();
    BestScore(){
        this.setTitle("Plague Inc - Best Score");
        this.setLayout(null);
        this.setSize(350,250);
        this.setResizable(false);
        this.setBackground(new Color(123, 50, 250));

        btnPanel = new JPanel();
        btnPanel.setBounds(0,0, 350, 50);

        listOfBestPanel = new JPanel();
        listOfBestPanel.setBounds(0,50, 350, 250);

        this.add(btnPanel);
        this.add(listOfBestPanel);

        JList<Person> list = new JList();
        ArrayList<Person> peopleList = loadFromFile();
        ListModel peopleModelList = new ListModel(peopleList);
        list.setModel(peopleModelList);
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane1 = new JScrollPane(list);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer)list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        peopleModelList.sort();
        listOfBestPanel.add(scrollPane1);


        btnBackToMenu = new JButton("Back to Menu");
        btnBackToMenu.setFocusable(false);
        btnBackToMenu.setBackground(new Color(136, 174, 210));
        btnBackToMenu.setForeground(new Color(255, 255, 255, 255));
        btnBackToMenu.setBackground(new Color(123, 50, 250));
        btnBackToMenu.addActionListener(e -> {
            this.dispose();
            new Menu();
        });

        btnPanel.add(btnBackToMenu);

        this.setVisible(true);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                new Menu();
                e.getWindow().dispose();
            }
        });

        ImageIcon icon = new ImageIcon("resources/logo.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(255, 255, 255));


    }

    public ArrayList<Person> loadFromFile(){
        ArrayList<Person> people = new ArrayList<>();
        try {
            File file = new File("BestScore.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                String[] splitedLine = line.split(" ");
                String name = splitedLine[0];
                int score = Integer.parseInt(splitedLine[1]);
                people.add(new Person(name, score));
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return people;
    }
}

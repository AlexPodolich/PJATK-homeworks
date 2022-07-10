package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JFrame{
    private String difficulty;
    boolean stillPlaying = true;
    Player player = Player.init();
    World world = InitWorld.init(player);
    ArrayList<Thread> threadList = new ArrayList<>();
    String currentCountry;
    long percentInfected;
    long percentCured;
    long peopleInWorld;
    long casesInWorld;
    long infectedInWorld;
    long curedInWorld;
    Action menuAction;
    int clickedButton;

    Image plane;
    Timer timer;
    long startTime;
    int startX = 0;
    int targetX;
    int startY = 0;
    int targetY;

    JButton france;
    JButton russia;
    JButton spain;
    JButton italy;
    JButton turkey;
    JButton greece;
    JButton poland;
    JButton czech;
    JButton ukraine;
    JButton norway;
    JButton sweden;
    JButton finland;
    JButton estonia;
    JButton latvia;
    JButton bielorussia;
    JButton romania;
    JButton bulgaria;
    JButton hungary;
    JButton moldavia;
    JButton lithuania;
    JButton belgium;
    JButton portugal;
    JButton ireland;
    JButton iceland;
    JButton unitedKingdom;
    JButton denmark;
    JButton germany;
    JButton albania;
    JButton switzerland;

    JLabel background;
    ImageIcon img;

    JPanel upperPanel;
    JPanel mainPanel;
    JPanel rightPanel;

    ImageIcon icon;

    JButton btnMenu;
    JLabel labelDayOfEpidemic;
    JLabel labelTotalCases;
    JLabel labelInfected;
    JLabel labelVaccineProgress;
    JLabel labelCureProgress;
    JLabel labelNumOfCoins;
    JLabel labelNumOfPoints;

    JButton btnInfectionInfo;
    JButton btnTransportInfo;
    JButton btnStore;

    JLayeredPane layeredPane;
    JPanel btnPanel;
    JPanel infectionPanel;
    JPanel virusSpreadPanel;
    JPanel storePanel;

    JLabel countryNameWithFlag;
    JLabel countryPopulation;
    JLabel countryVirusCases;
    JLabel countryPercentInfected;
    JLabel countryImage;
    JButton percentOfInfected;
    JButton countryStatus;

    JLabel labelUpgrades;
    JLabel labelPlayerCoins = new JLabel(String.valueOf(player.getMoney()));;
    JList listUpgrades;
    UpgradeListModel listModel;

    JLabel labelVirusSpread;
    JList listVirusHistory;

    ImageIcon countryPng;
    Image imgcountry;
    Image newcountry;

    ImageIcon flagPng;
    Image imgFlag;
    Image newFlag;
    Virus virus;
    ArrayList<Country> countries = world.getAllCountries();

    Game(String difficulty){
        currentCountry = "Poland";
        virus = new Virus(difficulty);
        for(Country country : countries){
            peopleInWorld += country.getCountryPopulation();
        }

        this.difficulty = difficulty;
        Vaccine.vaccineDifficulty = difficulty;
        Thread dateThread = new Thread(new DateThread());
        dateThread.start();
        DateThread.isGameGoing = true;
        Vaccine.isGameGoing = true;
       this.setLayout(null);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                askWindow();
            }
        });

       upperPanel = new JPanel();
       upperPanel.setBackground(new Color(8, 38, 68));
       upperPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 60, 10));
       upperPanel.setBounds(0,0, 840, 70);
       mainPanel = new JPanel();
       mainPanel.setBackground(new Color(8, 38, 68));
       mainPanel.setBounds(0,70, 540, 640);
       rightPanel = new JPanel();
       rightPanel.setBackground(new Color(38, 65, 238));
       rightPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
       rightPanel.setBounds(240,70, 200, 140);
       this.add(upperPanel);
       this.add(mainPanel);
       this.add(rightPanel);


       btnPanel = new JPanel();
       btnPanel.setBackground(new Color(136, 174, 210));
       btnPanel.setBounds(0,0,290,40);

       infectionPanel = new JPanel();
       infectionPanel.setBackground(new Color(136, 174, 210));
       infectionPanel.setBounds(0,40,300,600);
       infectionPanel.setLayout(new BoxLayout(infectionPanel, BoxLayout.Y_AXIS));

       virusSpreadPanel = new JPanel();
       virusSpreadPanel.setBackground(new Color(136, 174, 210));
       virusSpreadPanel.setBounds(0,40,300,600);
        virusSpreadPanel.setLayout(new BoxLayout(virusSpreadPanel, BoxLayout.Y_AXIS));

       storePanel = new JPanel();
       storePanel.setBackground(new Color(136, 174, 210));
       storePanel.setBounds(0,40,300,565);
       storePanel.setLayout(new BoxLayout(storePanel, BoxLayout.Y_AXIS));

       layeredPane = new JLayeredPane();
       layeredPane.setBounds(540,70,300,640);

       layeredPane.add(infectionPanel);
       layeredPane.add(virusSpreadPanel);
       layeredPane.add(storePanel);

       btnInfectionInfo = new JButton("Infections");
       btnInfectionInfo.setBounds(0,0, 85, 20);
       btnInfectionInfo.setForeground(new Color(255, 255, 255));
       btnInfectionInfo.setBackground(new Color(8, 38, 68));
       btnInfectionInfo.addActionListener(e -> {
           switchPanels(infectionPanel);
           clickedButton = 1;
           btnInfectionInfo.setBackground(new Color(0,255,0));
           btnTransportInfo.setBackground(new Color(8, 38, 68));
           btnStore.setBackground(new Color(8, 38, 68));
       });

       btnTransportInfo = new JButton("Virus spread");
       btnTransportInfo.setBounds(85,0, 85, 20);
       btnTransportInfo.setForeground(new Color(255, 255, 255));
       btnTransportInfo.setBackground(new Color(8, 38, 68));
       btnTransportInfo.addActionListener(e -> {
           openVirusSpread();
           clickedButton = 2;
           btnInfectionInfo.setBackground(new Color(8, 38, 68));
           btnTransportInfo.setBackground(new Color(0,255,0));
           btnStore.setBackground(new Color(8, 38, 68));
       });

       btnStore = new JButton("Store");
       btnStore.setBounds(110,0, 85, 20);
       btnStore.setForeground(new Color(255, 255, 255));
       btnStore.setBackground(new Color(8, 38, 68));
       btnStore.addActionListener(e -> {
           openUpgradeShop();
           clickedButton = 3;
           btnInfectionInfo.setBackground(new Color(8, 38, 68));
           btnTransportInfo.setBackground(new Color(8, 38, 68));
           btnStore.setBackground(new Color(0,255,0));
       });


       layeredPane.add(btnPanel);
       btnPanel.add(btnInfectionInfo);
       btnPanel.add(btnTransportInfo);
       btnPanel.add(btnStore);

       this.add(layeredPane);

       btnMenu = new JButton("Back to Menu");
       btnMenu.setFocusable(false);
       btnMenu.setForeground(new Color(255, 255, 255, 255));
       btnMenu.setBackground(new Color(123, 50, 250));
       btnMenu.addActionListener(e -> {
           askWindow();
       });

       labelDayOfEpidemic = new JLabel("Day of epidemics: 0");
       labelVaccineProgress = new JLabel("Vaccince progress: 0.0%");


       labelDayOfEpidemic.setForeground(new Color(255, 255, 255, 255));
       labelTotalCases = new JLabel("Total cases: 0");
       labelTotalCases.setForeground(new Color(255, 255, 255, 255));
       labelInfected = new JLabel("0% of the world infected");
       labelInfected.setForeground(new Color(255, 255, 255, 255));
       labelVaccineProgress.setForeground(new Color(255, 255, 255, 255));
       labelCureProgress = new JLabel("Cure progress: 0%");
       labelCureProgress.setForeground(new Color(255, 255, 255, 255));
       labelNumOfCoins = new JLabel("Coins: " + player.getMoney());
       labelNumOfCoins.setForeground(new Color(255, 231, 0, 255));

       ImageIcon coin = new ImageIcon("resources/coin.png");
       Image image = coin.getImage(); // transform it
       Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
       coin = new ImageIcon(newimg);  // transform it back

       labelNumOfCoins.setIcon(coin);

       labelNumOfPoints = new JLabel("Points: " + player.getPoints());
       labelNumOfPoints.setForeground(new Color(255, 255, 255, 255));
       ImageIcon point = new ImageIcon("resources/point.png");
       Image image1 = point.getImage(); // transform it
       Image newimg1 = image1.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
       point = new ImageIcon(newimg1);  // transform it back
       labelNumOfPoints.setIcon(point);
       upperPanel.add(btnMenu);
       upperPanel.add(labelDayOfEpidemic);
       upperPanel.add(labelTotalCases);
       upperPanel.add(labelInfected);
       upperPanel.add(labelVaccineProgress);
       upperPanel.add(labelCureProgress);
       upperPanel.add(labelNumOfCoins);
       upperPanel.add(labelNumOfPoints);

       this.setTitle("Plague Inc Game - " + difficulty + " difficulty");
       this.pack();
       this.setSize(840,710);
       this.setResizable(false);
       this.setVisible(true);
       this.setLocationRelativeTo(null);

       img = new ImageIcon("resources/map.jpg");
       background = new JLabel();
       background.setIcon(img);
       background.setSize(540,640);

       menuAction = new menuAction();
       background.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.SHIFT_MASK | KeyEvent.CTRL_MASK), "menuAction");
       background.getActionMap().put("menuAction", menuAction);

       mainPanel.add(background);

        MyLine icelandToUK = new MyLine(50, 125, Color.BLUE);
        icelandToUK.setBounds(110,55, 120, 262);
        background.add(icelandToUK);

        MyLine icelandToNorway = new MyLine(135, 100, Color.BLUE);
        icelandToNorway.setBounds(125,55, 135, 262);
        background.add(icelandToNorway);

        MyLine icelandToSpain = new MyLine(0, 380, Color.BLUE);
        icelandToSpain.setBounds(105,55, 135, 420);
        background.add(icelandToSpain);

        MyLine spainToIreland = new MyLine(0, 155, Color.BLUE);
        spainToIreland.setBounds(80,265, 135, 420);
        background.add(spainToIreland);

        MyLine spainToItaly = new MyLine(200, 0, Color.BLUE);
        spainToItaly.setBounds(110,505, 235, 420);
        background.add(spainToItaly);

        MyLine spainToGreece = new MyLine(300, 0, Color.BLUE);
        spainToGreece.setBounds(80,525, 335, 420);
        background.add(spainToGreece);

        MyLine turkeyToMoldova = new MyLine(0, 100, Color.BLUE);
        turkeyToMoldova.setBounds(495,385, 335, 420);
        background.add(turkeyToMoldova);

        MyLine swedenToPoland = new MyLine(0, 40, Color.BLUE);
        swedenToPoland.setBounds(330,245, 335, 420);
        background.add(swedenToPoland);

        MyLine swedenToLatvia = new MyLine(35, 45, Color.BLUE);
        swedenToLatvia.setBounds(350,190, 335, 420);
        background.add(swedenToLatvia);

        MyLine swedenToEstonia = new MyLine(40, 50, Color.BLUE);
        swedenToEstonia.setBounds(340,140, 335, 420);
        background.add(swedenToEstonia);

        MyLine norwayToDenmark = new MyLine(0, 40, Color.BLUE);
        norwayToDenmark.setBounds(263,205, 335, 420);
        background.add(norwayToDenmark);

        MyLine unitedKingdomToGermany = new MyLine(100, 40, Color.BLUE);
        unitedKingdomToGermany.setBounds(163,235, 335, 420);
        background.add(unitedKingdomToGermany);

        MyLine UKToFrance = new MyLine(0, 40, Color.BLUE);
        UKToFrance.setBounds(123,305, 335, 420);
        background.add(UKToFrance);

        MyLine UKToNorway = new MyLine(85, 0, Color.BLUE);
        UKToNorway.setBounds(163,205, 335, 420);
        background.add(UKToNorway);

        //Cars
        MyLine portugalToSpainCar = new MyLine(35, 20, Color.RED);
        portugalToSpainCar.setBounds(25,445, 335, 420);
        background.add(portugalToSpainCar);

        MyLine franceToSpainCar = new MyLine(0, 90, Color.RED);
        franceToSpainCar.setBounds(135,375, 335, 420);
        background.add(franceToSpainCar);

        MyLine franceToBelgiumCar = new MyLine(0, 30, Color.RED);
        franceToBelgiumCar.setBounds(155,330, 335, 420);
        background.add(franceToBelgiumCar);

        MyLine franceToSwitzerlandCar = new MyLine(20, 25, Color.RED);
        franceToSwitzerlandCar.setBounds(175,375, 335, 420);
        background.add(franceToSwitzerlandCar);

        MyLine franceToGermanyCar = new MyLine(40, 0, Color.RED);
        franceToGermanyCar.setBounds(200,365, 335, 420);
        background.add(franceToGermanyCar);

        MyLine franceToItalyCar = new MyLine(120, 80, Color.RED);
        franceToItalyCar.setBounds(145,375, 335, 420);
        background.add(franceToItalyCar);

        MyLine turkeyToBulgariaCar = new MyLine(0, 45, Color.RED);
        turkeyToBulgariaCar.setBounds(455,475, 335, 420);
        background.add(turkeyToBulgariaCar);

        MyLine greeceToTurkeyCar = new MyLine(0, 30, Color.RED);
        greeceToTurkeyCar.setBounds(455,530, 335, 420);
        background.add(greeceToTurkeyCar);

        MyLine albaniaToGreeceCar = new MyLine(20, 60, Color.RED);
        albaniaToGreeceCar.setBounds(390,508, 335, 420);
        background.add(albaniaToGreeceCar);

        MyLine albaniaToBulgariaCar = new MyLine(0, 20, Color.RED);
        albaniaToBulgariaCar.setBounds(408,475, 335, 420);
        background.add(albaniaToBulgariaCar);

        MyLine romaniaToBulgariaCar = new MyLine(0, 25, Color.RED);
        romaniaToBulgariaCar.setBounds(408,430, 335, 420);
        background.add(romaniaToBulgariaCar);

        MyLine romaniaToMoldovaCar = new MyLine(0, 25, Color.RED);
        romaniaToMoldovaCar.setBounds(440,390, 335, 420);
        background.add(romaniaToMoldovaCar);

        MyLine moldovaToUkraineCar = new MyLine(0, 25, Color.RED);
        moldovaToUkraineCar.setBounds(450,355, 335, 420);
        background.add(moldovaToUkraineCar);

        MyLine ukraineToBielorussiaCar = new MyLine(0, 35, Color.RED);
        ukraineToBielorussiaCar.setBounds(440,305, 335, 420);
        background.add(ukraineToBielorussiaCar);

        MyLine bielorussiaToLithuaniaCar = new MyLine(0, 20, Color.RED);
        bielorussiaToLithuaniaCar.setBounds(420,270, 335, 420);
        background.add(bielorussiaToLithuaniaCar);

        MyLine lithuaniaToLatviaCar = new MyLine(0, 15, Color.RED);
        lithuaniaToLatviaCar.setBounds(400,245, 335, 420);
        background.add(lithuaniaToLatviaCar);

        MyLine latviaToRussiaCar = new MyLine(0, 20, Color.RED);
        latviaToRussiaCar.setBounds(455,220, 335, 420);
        background.add(latviaToRussiaCar);

        MyLine russiaToFinlandCar = new MyLine(30, 60, Color.RED);
        russiaToFinlandCar.setBounds(420,150, 335, 420);
        background.add(russiaToFinlandCar);

        MyLine finlandToSwedenCar = new MyLine(10, 30, Color.RED);
        finlandToSwedenCar.setBounds(355,123, 335, 420);
        background.add(finlandToSwedenCar);

        MyLine swedenToNorwayCar = new MyLine(0, 45, Color.RED);
        swedenToNorwayCar.setBounds(305,123, 335, 420);
        background.add(swedenToNorwayCar);

        MyLine latviaToEstoniaCar = new MyLine(0, 20, Color.RED);
        latviaToEstoniaCar.setBounds(390,210, 335, 420);
        background.add(latviaToEstoniaCar);

        MyLine polandToCzechCar = new MyLine(0, 27, Color.RED);
        polandToCzechCar.setBounds(370,323, 335, 420);
        background.add(polandToCzechCar);

        MyLine czechToGermanyCar = new MyLine(0, 20, Color.RED);
        czechToGermanyCar.setBounds(275,332, 335, 420);
        background.add(czechToGermanyCar);

        MyLine germanyToDenmarkCar = new MyLine(0, 58, Color.RED);
        germanyToDenmarkCar.setBounds(275,260, 335, 420);
        background.add(germanyToDenmarkCar);

        MyLine belgiumToGermanyCar = new MyLine(20, 0, Color.RED);
        belgiumToGermanyCar.setBounds(220,325, 335, 420);
        background.add(belgiumToGermanyCar);

        MyLine germanyToPolandCar = new MyLine(20, 0, Color.RED);
        germanyToPolandCar.setBounds(310,320, 335, 420);
        background.add(germanyToPolandCar);

        MyLine polandToBielorussiaCar = new MyLine(25, 0, Color.RED);
        polandToBielorussiaCar.setBounds(405,312, 335, 420);
        background.add(polandToBielorussiaCar);

        MyLine polandToUkraineCar = new MyLine(40, 30, Color.RED);
        polandToUkraineCar.setBounds(395,320, 335, 420);
        background.add(polandToUkraineCar);

        MyLine polandToLithuaniaCar = new MyLine(0, 45, Color.RED);
        polandToLithuaniaCar.setBounds(375,265, 335, 420);
        background.add(polandToLithuaniaCar);

        MyLine estoniaToRussiaCar = new MyLine(20, 0, Color.RED);
        estoniaToRussiaCar.setBounds(425,220, 335, 420);
        background.add(estoniaToRussiaCar);

        MyLine hungaryToRomaniaCar = new MyLine(30, 25, Color.RED);
        hungaryToRomaniaCar.setBounds(380,380, 335, 420);
        background.add(hungaryToRomaniaCar);

        MyLine germanyToSwitzerlandCar = new MyLine(0, 55, Color.RED);
        germanyToSwitzerlandCar.setBounds(250,333, 335, 420);
        background.add(germanyToSwitzerlandCar);

        MyLine UKToIreland = new MyLine(10, 25, Color.RED);
        UKToIreland.setBounds(120,235, 335, 420);
        background.add(UKToIreland);


       france = new JButton("France");
       france.setBounds(135,360, 75, 20);
       france.setFocusable(false);
       france.addActionListener(e -> {
           changeInfectedCountry(france.getText());
           switchPanels(infectionPanel);
           clickedButton = 1;
           btnInfectionInfo.setBackground(new Color(0,255,0));
           btnTransportInfo.setBackground(new Color(8, 38, 68));
           btnStore.setBackground(new Color(8, 38, 68));
       });
        countries.get(15).setCountryBtn(france);

       iceland = new JButton("Iceland");
       iceland.setBounds(80,30, 75, 20);
       iceland.setFocusable(false);
        iceland.addActionListener(e -> {
            changeInfectedCountry(iceland.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(6).setCountryBtn(iceland);


       ireland = new JButton("Ireland");
       ireland.setBounds(50,235, 75, 20);
       ireland.setFocusable(false);
        ireland.addActionListener(e -> {
            changeInfectedCountry(ireland.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
       });
        countries.get(7).setCountryBtn(ireland);

       unitedKingdom = new JButton("<html>United<br>Kingdom</html>");
       unitedKingdom.setBounds(120,262, 75, 40);
       unitedKingdom.setFocusable(false);
        unitedKingdom.addActionListener(e -> {
            changeInfectedCountry("United Kingdom");
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(8).setCountryBtn(unitedKingdom);

       norway = new JButton("Norway");
       norway.setBounds(234,167, 85, 20);
       norway.setFocusable(false);
        norway.addActionListener(e -> {
            changeInfectedCountry(norway.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(5).setCountryBtn(norway);

       sweden = new JButton("Sweden");
       sweden.setBounds(302,108, 85, 20);
       sweden.setFocusable(false);
        sweden.addActionListener(e -> {
            changeInfectedCountry(sweden.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(4).setCountryBtn(sweden);

       finland = new JButton("Finland");
       finland.setBounds(360,146, 75, 20);
       finland.setFocusable(false);
        finland.addActionListener(e -> {
            changeInfectedCountry(finland.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(3).setCountryBtn(finland);

       russia = new JButton("Russia");
       russia.setBounds(445,205, 75, 20);
       russia.setFocusable(false);
        russia.addActionListener(e -> {
            changeInfectedCountry(russia.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(0).setCountryBtn(russia);

       ukraine = new JButton("Ukraine");
       ukraine.setBounds(438,342, 85, 20);
       ukraine.setFocusable(false);
        ukraine.addActionListener(e -> {
            changeInfectedCountry(ukraine.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(17).setCountryBtn(ukraine);

       spain = new JButton("Spain");
       spain.setBounds(60,460, 75, 20);
       spain.setFocusable(false);
        spain.addActionListener(e -> {
            changeInfectedCountry(spain.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(22).setCountryBtn(spain);

       portugal = new JButton("Portugal");
       portugal.setBounds(5,430, 85, 20);
       portugal.setFocusable(false);
        portugal.addActionListener(e -> {
            changeInfectedCountry(portugal.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(21).setCountryBtn(portugal);

       italy = new JButton("Italy");
       italy.setBounds(245,448, 85, 20);
       italy.setFocusable(false);
        italy.addActionListener(e -> {
            changeInfectedCountry(italy.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(23).setCountryBtn(italy);

       germany = new JButton("Germany");
       germany.setBounds(230,317, 85, 20);
       germany.setFocusable(false);
        germany.addActionListener(e -> {
            changeInfectedCountry(germany.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(13).setCountryBtn(germany);

       switzerland = new JButton("Switzerland");
       switzerland.setBounds(190,386, 105, 20);
       switzerland.setFocusable(false);
        switzerland.addActionListener(e -> {
            changeInfectedCountry(switzerland.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(20).setCountryBtn(switzerland);

       greece = new JButton("Greece");
       greece.setBounds(400,558, 85, 20);
       greece.setFocusable(false);
        greece.addActionListener(e -> {
            changeInfectedCountry(greece.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(28).setCountryBtn(greece);

       turkey = new JButton("Turkey");
       turkey.setBounds(445,517, 85, 20);
       turkey.setFocusable(false);
        turkey.addActionListener(e -> {
            changeInfectedCountry(turkey.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(27).setCountryBtn(turkey);

       poland = new JButton("Poland");
       poland.setBounds(325,307, 85, 20);
       poland.setFocusable(false);
        poland.addActionListener(e -> {
            changeInfectedCountry(poland.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(12).setCountryBtn(poland);

       czech = new JButton("Czech Republic");
       czech.setBounds(260,343, 125, 23);
       czech.setFocusable(false);
        czech.addActionListener(e -> {
            changeInfectedCountry(czech.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(16).setCountryBtn(czech);

       denmark = new JButton("Denmark");
       denmark.setBounds(232,243, 95, 20);
       denmark.setFocusable(false);
        denmark.addActionListener(e -> {
            changeInfectedCountry(denmark.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(9).setCountryBtn(denmark);

       albania = new JButton("Albania");
       albania.setBounds(333,489, 85, 23);
       albania.setFocusable(false);
        albania.addActionListener(e -> {
            changeInfectedCountry(albania.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(24).setCountryBtn(albania);

       estonia = new JButton("Estonia");
       estonia.setBounds(370,190, 80, 23);
       estonia.setFocusable(false);
        estonia.addActionListener(e -> {
            changeInfectedCountry(estonia.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(2).setCountryBtn(estonia);

       latvia = new JButton("Latvia");
       latvia.setBounds(387,229, 75, 20);
       latvia.setFocusable(false);
        latvia.addActionListener(e -> {
            changeInfectedCountry(latvia.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(1).setCountryBtn(latvia);

       bielorussia = new JButton("Bielorussia");
       bielorussia.setBounds(405,288, 105, 23);
       bielorussia.setFocusable(false);
        bielorussia.addActionListener(e -> {
            changeInfectedCountry(bielorussia.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(11).setCountryBtn(bielorussia);

       romania = new JButton("Romania");
       romania.setBounds(390,409, 85, 23);
       romania.setFocusable(false);
        romania.addActionListener(e -> {
            changeInfectedCountry(romania.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(25).setCountryBtn(romania);

       bulgaria = new JButton("Bulgaria");
       bulgaria.setBounds(400,455, 85, 20);
       bulgaria.setFocusable(false);
        bulgaria.addActionListener(e -> {
            changeInfectedCountry(bulgaria.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(26).setCountryBtn(bulgaria);

       hungary = new JButton("Hungary");
       hungary.setBounds(310,390, 85, 23);
       hungary.setFocusable(false);
        hungary.addActionListener(e -> {
            changeInfectedCountry(hungary.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(19).setCountryBtn(hungary);

       belgium = new JButton("Belgium");
       belgium.setBounds(144,316, 85, 23);
       belgium.setFocusable(false);
        belgium.addActionListener(e -> {
            changeInfectedCountry(belgium.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(14).setCountryBtn(belgium);

       lithuania = new JButton("Lithuania");
       lithuania.setBounds(358,254, 105, 20);
       lithuania.setFocusable(false);
        lithuania.addActionListener(e -> {
            changeInfectedCountry(lithuania.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(10).setCountryBtn(lithuania);

       moldavia = new JButton("Moldova");
       moldavia.setBounds(438,377, 85, 23);
       moldavia.setFocusable(false);
        moldavia.addActionListener(e -> {
            changeInfectedCountry(moldavia.getText());
            switchPanels(infectionPanel);
            clickedButton = 1;
            btnInfectionInfo.setBackground(new Color(0,255,0));
            btnTransportInfo.setBackground(new Color(8, 38, 68));
            btnStore.setBackground(new Color(8, 38, 68));
        });
        countries.get(18).setCountryBtn(moldavia);

       background.add(france);
       background.add(iceland);
       background.add(ireland);
       background.add(unitedKingdom);
       background.add(norway);
       background.add(sweden);
       background.add(finland);
       background.add(russia);
       background.add(ukraine);
       background.add(spain);
       background.add(portugal);
       background.add(italy);
       background.add(germany);
       background.add(switzerland);
       background.add(greece);
       background.add(turkey);
       background.add(poland);
       background.add(czech);
       background.add(denmark);
       background.add(albania);
       background.add(estonia);
       background.add(latvia);
       background.add(bielorussia);
       background.add(hungary);
       background.add(romania);
       background.add(bulgaria);
       background.add(moldavia);
       background.add(belgium);
       background.add(lithuania);


        Thread checkWorld = new Thread(new Runnable() {
            @Override
            public void run() {
                while (stillPlaying) {
                    try {
                        if(Vaccine.vaccineReadyPercent >= 100) {
                            Thread.sleep(200);
                            for (Country country : countries) {
                                if(country.getCuredPeople() <= country.getVirusCases()){
                                    country.setCuredPeople(country.getCuredPeople() + (long)(country.getVirusCases()*0.1));
                                    country.setInfectedPeople(country.getInfectedPeople() - country.getCuredPeople());
                                }
                                if(country.getCuredPeople() >= country.getVirusCases()){
                                    country.setCuredPeople(country.getVirusCases());
                                }
                                if(country.getInfectedPeople() < 0){
                                    country.setInfectedPeople(0);
                                }
                                if(country.getCuredPeople() > country.getCountryPopulation()){
                                    country.setCuredPeople(country.getCountryPopulation());
                                }
                            }
                        }

                        casesInWorld = 0;
                        curedInWorld = 0;
                        infectedInWorld = 0;
                        for (Country country : countries){
                            if(country.getVirusCases() > country.getCountryPopulation()){
                                country.setVirusCases(country.getCountryPopulation());
                            }
                            if(country.getCuredPeople() > country.getCountryPopulation()){
                                country.setCuredPeople(country.getCountryPopulation());
                            }
                            if(country.getInfectedPeople() > country.getCountryPopulation()){
                                country.setInfectedPeople(country.getCountryPopulation());
                            }
                            curedInWorld += country.getCuredPeople();
                            casesInWorld += country.getVirusCases();
                            infectedInWorld+= country.getInfectedPeople();
                        }

                        world.setWorldCured(curedInWorld);
                        
                        for (Country country : countries) {
                            world.dayInCountryWithVirus(virus, country, background);

                            long infectedPeople = country.getInfectedPeople();
                            long curedPeople = country.getCuredPeople();
                            long virusCases = country.getVirusCases();

                            if(casesInWorld <= peopleInWorld){
                                country.setInfectedPeople(infectedPeople);
                                country.setCuredPeople(curedPeople);
                                country.setVirusCases(virusCases);
                                labelTotalCases.setText("Total cases: " + world.getWorldCases());
                                percentInfected = (infectedInWorld * 100) / peopleInWorld;
                                percentCured = (curedInWorld * 100) / peopleInWorld;
                                labelInfected.setText(percentInfected + " % of the world infected");
                                labelCureProgress.setText("Cure progress: " + percentCured + " %");
                                player.setMoney((int)(player.getMoney() + world.getNewInfectedPeople()/10000));
                                player.setPoints((int)(player.getPoints() + country.getCuredPeople()/100000));
                            }
                            if(country.getVirusCases() > country.getCountryPopulation()){
                                country.setVirusCases(country.getCountryPopulation());
                            }
                            if(country.getCuredPeople() > country.getCountryPopulation()){
                                country.setCuredPeople(country.getCountryPopulation());
                            }
                            if(country.getInfectedPeople() > country.getCountryPopulation()){
                                country.setInfectedPeople(country.getCountryPopulation());
                            }
                            if(world.getWorldCases() <= casesInWorld){
                                world.setWorldCases(casesInWorld);
                            }
                            world.setPercentWorldInfected(percentInfected);

                            if(country.getInfectedPeople() < country.getCountryPopulation()*0.15){
                                country.getCountryBtn().setBackground(new Color(0, 255, 0));
                                country.getCountryBtn().setForeground(Color.BLACK);
                            }else if(country.getInfectedPeople() >= country.getCountryPopulation()*0.15 && country.getInfectedPeople() < country.getCountryPopulation()*0.33){
                                country.getCountryBtn().setBackground(new Color(255, 242, 0));
                                country.getCountryBtn().setForeground(Color.BLACK);
                            }else if(country.getInfectedPeople() >= country.getCountryPopulation()*0.33 && country.getInfectedPeople() < country.getCountryPopulation()*0.5){
                                country.getCountryBtn().setBackground(new Color(255, 100, 0));
                                country.getCountryBtn().setForeground(Color.BLACK);
                            }else if(country.getInfectedPeople() >= country.getCountryPopulation()*0.5 && country.getInfectedPeople() < country.getCountryPopulation()*0.95){
                                country.getCountryBtn().setBackground(new Color(255, 0, 0));
                                country.getCountryBtn().setForeground(Color.BLACK);
                            }else if(country.getInfectedPeople() >= country.getCountryPopulation()*0.95 && country.getInfectedPeople() <= country.getCountryPopulation()){
                                country.getCountryBtn().setBackground(new Color(0, 0, 0));
                                country.getCountryBtn().setForeground(Color.WHITE);
                            }
                        }
                        labelDayOfEpidemic.setText("Day of epidemics: " + DateThread.currentDate);
                        labelVaccineProgress.setText("Vaccince progress: " + (Math.ceil(Vaccine.vaccineReadyPercent * 10)) / 10 + "%");

                        if(difficulty.equals("Easy")){
                            player.setPoints(player.getPoints() + 1);
                        }else if(difficulty.equals("Normal")){
                            player.setPoints(player.getPoints() + 2);
                        }else if(difficulty.equals("Hard")){
                            player.setPoints(player.getPoints() + 3);
                        }
                        labelNumOfCoins.setText("Coins: " + player.getMoney());
                        labelPlayerCoins.setText(String.valueOf(player.getMoney()));

                        startAnimations();
                        Thread.sleep(300);
                        if(currentCountry != null && clickedButton == 1){
                            changeInfectedCountry(currentCountry);
                        }
                        if(clickedButton == 2){
                            openVirusSpread();
                        }
                        labelNumOfPoints.setText("Points: " + player.getPoints());
                        if(world.getPercentWorldInfected() == 100){
                            stillPlaying = false;
                        }
                        if (world.getWorldCured() >= world.getWorldCases())
                        {
                            stillPlaying = false;
                        }
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if(world.getPercentWorldInfected() == 100){
                    //show dialog window player lose
                    JOptionPane.showMessageDialog(null, "All people in the world are infected with the virus", "Inform Message", JOptionPane.ERROR_MESSAGE);
                    saveBestScore();
                }else if(world.getWorldCured() >= world.getWorldCases() && world.getWorldCases() != 0){
                    //show dialog window player win
                    JOptionPane.showMessageDialog(null, "All people in the world are cured from the virus", "Inform Message", JOptionPane.INFORMATION_MESSAGE);
                    saveBestScore();
                }
                DateThread.currentDate = 0;
                Vaccine.vaccineReadyPercent = 0;
                DateThread.isGameGoing = false;
                Vaccine.isGameGoing = false;
            }
        });
        checkWorld.start();

       this.setVisible(true);

       icon = new ImageIcon("resources/logo.png");
       this.setIconImage(icon.getImage());
       this.getContentPane().setBackground(new Color(255, 255, 255));
   }

   public void startAnimations() throws InterruptedException {
       for (int i = 0; i < world.getListThreads().size(); i++) {
           if (world.getListThreads().get(i) != null){
               world.getListThreads().get(i).start();
           }
       }
       for (int i = 0; i < world.getListThreads().size(); i++) {
           world.getListThreads().get(i).join();
       }
       world.getListThreads().clear();
   }


   public void switchPanels(JPanel panel){
       layeredPane.removeAll();
       layeredPane.add(btnPanel);
       layeredPane.add(panel);
       layeredPane.repaint();
       layeredPane.revalidate();
   }

   public void closeGame(){
       this.dispose();
       DateThread.currentDate = 0;
       Vaccine.vaccineReadyPercent = 0;
       DateThread.isGameGoing = false;
       Vaccine.isGameGoing = false;
       world.setWorldCured(0);
       world.setPercentWorldInfected(0);
       world.setWorldCases(0);
       world.setNewInfectedPeople(0);
       world.setWorldCureProgress(0);
       new Menu();
   }

   public void changeInfectedCountry(String selectedCountry){
       currentCountry = selectedCountry;
       for (Country country : countries) {
           if(country.getName().equals(selectedCountry)){
               infectionPanel.removeAll();
               flagPng = new ImageIcon(country.getPathFlagImg());
               imgFlag = flagPng.getImage();
               newFlag = imgFlag.getScaledInstance(200, 120,  java.awt.Image.SCALE_SMOOTH);
               flagPng = new ImageIcon(newFlag);

               String countryName = country.getName().toUpperCase();
               countryNameWithFlag = new JLabel(countryName);
               countryNameWithFlag.setFont(new Font("MV Boli", Font.BOLD, 28));
               countryNameWithFlag.setHorizontalTextPosition(JLabel.CENTER);
               countryNameWithFlag.setVerticalTextPosition(JLabel.TOP);
               countryNameWithFlag.setVerticalAlignment(JLabel.TOP);
               countryNameWithFlag.setHorizontalAlignment(JLabel.CENTER);
               countryNameWithFlag.setIcon(flagPng);

               long procent =country.getInfectedPeople()* 100 / country.getCountryPopulation();

               countryPopulation = new JLabel("Population: " + country.getCountryPopulation());

               countryVirusCases = new JLabel("Virus cases: " + country.getVirusCases());

               countryPercentInfected = new JLabel("Infected people: " + procent + "%");


               countryPng = new ImageIcon(country.getPathCountryImg());
               imgcountry = countryPng.getImage(); // transform it
               newcountry = imgcountry.getScaledInstance(280, 330,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
               countryPng = new ImageIcon(newcountry);  // transform it back
               countryImage = new JLabel();
               countryImage.setIcon(countryPng);

               percentOfInfected = new JButton(procent + " %");
               countryStatus = new JButton();
               if(procent < 15){
                   percentOfInfected.setBackground(new Color(0,255,0));
                   countryStatus.setVisible(false);
               }else if(procent >= 15 && procent < 33){
                   percentOfInfected.setBackground(new Color(255, 255, 0));
                   countryStatus.setText("WARNING!");
                   countryStatus.setVisible(true);
                   countryStatus.setBackground(new Color(255, 255, 0));
               }else if(procent >= 33 && procent < 50){
                   percentOfInfected.setBackground(new Color(255, 115, 0));
                   countryStatus.setText("WARNING!!!");
                   countryStatus.setVisible(true);
                   countryStatus.setBackground(new Color(255, 115, 0));
               }else if(procent >= 50 && procent < 95){
                   percentOfInfected.setBackground(new Color(255, 0, 0));
                   countryStatus.setText("DANGER");
                   countryStatus.setVisible(true);
                   countryStatus.setBackground(new Color(255, 0, 0));
               }else if(procent >= 95 && procent <= 100){
                   percentOfInfected.setBackground(new Color(0, 0, 0));
                   countryStatus.setText("TOO LATE");
                   countryStatus.setVisible(true);
                   countryStatus.setForeground(Color.WHITE);
                   percentOfInfected.setForeground(Color.WHITE);
                   countryStatus.setBackground(new Color(0, 0, 0));
               }

               percentOfInfected.setBounds(100,135,90,20);
               countryStatus.setBounds(85,165,120,20);

               infectionPanel.add(countryNameWithFlag);
               infectionPanel.add(countryPopulation);
               infectionPanel.add(countryVirusCases);
               infectionPanel.add(countryPercentInfected);
               infectionPanel.add(countryImage);
               countryImage.add(percentOfInfected);
               countryImage.add(countryStatus);
               infectionPanel.setVisible(true);
           }
       }
       switchPanels(infectionPanel);
   }

    public void openVirusSpread(){
        virusSpreadPanel.removeAll();
        labelVirusSpread = new JLabel("Virus Spread History");
        labelVirusSpread.setFont(new Font(null, Font.BOLD, 26));
        labelVirusSpread.setAlignmentX(Component.CENTER_ALIGNMENT);

        DefaultListModel<String> dlm = new DefaultListModel();
        for (int i = 0; i < world.getListVirusHistoryList().size(); i++) {
            dlm.add(0,world.getListVirusHistoryList().get(i));
        }


        listVirusHistory = new JList(dlm);
        listVirusHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listVirusHistory.setBackground(new Color(136, 174, 210));

        virusSpreadPanel.add(labelVirusSpread);

        JScrollPane scrollPane = new JScrollPane(listVirusHistory);
        virusSpreadPanel.add(scrollPane);

        virusSpreadPanel.setVisible(true);
        switchPanels(virusSpreadPanel);
    }

   public void openUpgradeShop(){
       storePanel.removeAll();
       labelUpgrades = new JLabel("Upgrades Shop");
       labelUpgrades.setFont(new Font(null, Font.BOLD, 26));
       labelUpgrades.setAlignmentX(Component.CENTER_ALIGNMENT);

       ImageIcon coin = new ImageIcon("resources/coin.png");
       Image image = coin.getImage(); // transform it
       Image newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
       coin = new ImageIcon(newimg);  // transform it back

       labelPlayerCoins.setFont(new Font(null, Font.ITALIC, 20));
       labelPlayerCoins.setIcon(coin);
       labelPlayerCoins.setAlignmentX(Component.CENTER_ALIGNMENT);

       ArrayList<Upgrade> upgrades = new ArrayList<>();


       for (int i = 0; i < player.getUpgrades().size(); i++) {
           upgrades.add(player.getUpgrades().get(i));
       }

       listModel = new UpgradeListModel(upgrades);
       listUpgrades = new JList(listModel);
       listUpgrades.setCellRenderer(new ItemCellRenderer());
       listUpgrades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       listUpgrades.addMouseListener(new MouseAdapter()
       {
           @Override
           public void mouseClicked(MouseEvent event)
           {
               clickButtonAt(event.getPoint());
           }
       });

       storePanel.add(labelUpgrades);
       storePanel.add(labelPlayerCoins);
       listModel.sort();
       JScrollPane scrollPane = new JScrollPane(listUpgrades);
       storePanel.add(scrollPane);

       storePanel.setVisible(true);
       switchPanels(storePanel);
   }

    private void clickButtonAt(Point point)
    {
        int index = listUpgrades.locationToIndex(point);
        Upgrade item = (Upgrade) listUpgrades.getModel().getElementAt(index);
        if(Integer.parseInt(labelPlayerCoins.getText()) >= item.getPrice() && item.isStatus() == false){
            int updMoney = Integer.parseInt(labelPlayerCoins.getText()) -  item.getPrice();
            player.setMoney(updMoney);
            labelPlayerCoins.setText(String.valueOf(updMoney));
            item.setStatus(true);
            labelNumOfCoins.setText("Coins: " + player.getMoney());
            if(item.getName().equals("startVaccine") && item.isStatus()){
                new Thread(new Vaccine(player)).start();
            }
        }
        //item.getButton().doClick();
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void saveBestScore(){
        String userName;
        int selectedOption;
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Enter your name: ");
        JTextField txt = new JTextField(10);
        panel.add(lbl);
        panel.add(txt);
        do{
            selectedOption = JOptionPane.showOptionDialog(null, panel, "Enter your name before exit", JOptionPane.NO_OPTION, 2, null, options , options[0]);
        }while(txt.getText().equals("") || txt.getText().equals(" "));

        if(selectedOption == 0)
        {
            userName = txt.getText();
            String filePath = "BestScore.txt";
            String addString = System.lineSeparator() + userName + " " + player.getPoints();
            try {
                FileWriter writer = new FileWriter(filePath, true);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(addString);
                bufferWriter.close();
            }
            catch (IOException err) {
                System.out.println(err);
            }

            closeGame();
        }
    }

    public void askWindow(){
        int selectedOption;
        String[] options = {"Yes", "No"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Do you want to save your result?");
        panel.add(lbl);
        selectedOption = JOptionPane.showOptionDialog(null, panel, "Do you want to save your result?", JOptionPane.YES_NO_OPTION, 2, null, options , options[0]);

        if(selectedOption == 0)
        {
            saveBestScore();
        }
        if(selectedOption == 1){
            closeGame();
        }
    }


    public class menuAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            askWindow();
        }

    }



}



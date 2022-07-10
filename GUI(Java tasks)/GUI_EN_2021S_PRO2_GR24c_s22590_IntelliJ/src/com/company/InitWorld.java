package com.company;

import java.util.ArrayList;

public class InitWorld {

    public static World init(Player player){
        World world = new World(player);

        Country russia = new Country("Russia", false, false, true, false, 144000000, "resources/russia.png", "resources/russiamap.png");
        russia.setNeighbourCountry(new ArrayList<>(){
            {
                add("Ukraine");
                add("Bielorussia");
                add("Latvia");
                add("Estonia");
                add("Finland");
                add("Norway");
            }
        } );
        russia.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        russia.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        world.setOneCountry(russia);
        Country latvia = new Country("Latvia", true, true, true, false, 1900000, "resources/latvia.png", "resources/latviamap.png");
        world.setOneCountry(latvia);
        latvia.setNeighbourCountry(new ArrayList<>(){
            {
                add("Estonia");
                add("Russia");
                add("Lithuania");
                add("Bielorussia");
            }
        } );
        latvia.setPortCountry(new ArrayList<>(){
            {
                add("Sweden");
            }
        });
        latvia.setAirportCountry(new ArrayList<>(){
            {
                add("Sweden");
            }
        });
        Country estonia = new Country("Estonia", true, true, false, true, 1320000, "resources/estonia.png", "resources/estoniamap.png");
        world.setOneCountry(estonia);
        estonia.setNeighbourCountry(new ArrayList<>(){
            {
                add("Russia");
                add("Latvia");
            }
        } );
        estonia.setPortCountry(new ArrayList<>(){
            {
                add("Sweden");
            }
        } );
        estonia.setAirportCountry(new ArrayList<>(){
            {
                add("Sweden");
            }
        });
        Country finland = new Country("Finland", false, false, false, true, 5520000, "resources/finland.png", "resources/finlandmap.png");
        world.setOneCountry(finland);
        finland.setNeighbourCountry(new ArrayList<>(){
            {
                add("Sweden");
            }
        } );
        finland.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        finland.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country sweden = new Country("Sweden", true, true, true, false, 10230000, "resources/sweden.png", "resources/swedenmap.png");
        world.setOneCountry(sweden);
        sweden.setNeighbourCountry(new ArrayList<>(){
            {
                add("Russia");
                add("Finland");
                add("Norway");
                add("Denmark");
            }
        } );
        sweden.setPortCountry(new ArrayList<>(){
            {
                add("Poland");
                add("Latvia");
                add("Estonia");
            }
        } );
        sweden.setAirportCountry(new ArrayList<>(){
            {
                add("Estonia");
                add("Latvia");
                add("Poland");
            }
        });
        Country norway = new Country("Norway", true, true, false, true, 532000,"resources/norway.png","resources/norwaymap.png");
        world.setOneCountry(norway);
        norway.setNeighbourCountry(new ArrayList<>(){
            {
                add("Sweden");
                add("Russia");
                add("Finland");
            }
        } );
        norway.setPortCountry(new ArrayList<>(){
            {
                add("Iceland");
                add("United Kingdom");
                add("Denmark");
            }
        } );
        norway.setAirportCountry(new ArrayList<>(){
            {
                add("Denmark");
                add("United Kingdom");
                add("Iceland");
            }
        });
        Country iceland = new Country("Iceland", true, true, true, false, 360000, "resources/iceland.png", "resources/icelandmap.png");
        world.setOneCountry(iceland);
        iceland.setNeighbourCountry(new ArrayList<>(){
            {
                add("No Neighbours");
            }
        } );
        iceland.setPortCountry(new ArrayList<>(){
            {
                add("Norway");
                add("United Kingdom");
                add("Spain");
            }
        } );
        iceland.setAirportCountry(new ArrayList<>(){
            {
                add("Norway");
                add("United Kingdom");
                add("Spain");
            }
        });
        Country ireland = new Country("Ireland", true, true, false, true, 4900000,"resources/ireland.png","resources/irelandmap.png");
        world.setOneCountry(ireland);
        ireland.setNeighbourCountry(new ArrayList<>(){
            {
                add("United Kingdom");
            }
        } );
        ireland.setPortCountry(new ArrayList<>(){
            {
                add("Spain");
            }
        } );
        ireland.setAirportCountry(new ArrayList<>(){
            {
                add("Spain");
            }
        });
        Country unitedKingdom = new Country("United Kingdom", true, true, false, false, 66650000,"resources/unitedKingdom.png","resources/unitedKingdommap.png");
        world.setOneCountry(unitedKingdom);
        unitedKingdom.setNeighbourCountry(new ArrayList<>(){
            {
                add("Ireland");
            }
        } );
        unitedKingdom.setPortCountry(new ArrayList<>(){
            {
                add("France");
                add("Germany");
                add("Norway");
                add("Iceland");
            }
        } );
        unitedKingdom.setAirportCountry(new ArrayList<>(){
            {
                add("France");
                add("Germany");
                add("Norway");
                add("Iceland");
            }
        } );
        Country denmark = new Country("Denmark", true, true, true, false, 5806000,"resources/denmark.png","resources/denmarkmap.png");
        world.setOneCountry(denmark);
        denmark.setNeighbourCountry(new ArrayList<>(){
            {
                add("Germany");
            }
        } );
        denmark.setPortCountry(new ArrayList<>(){
            {
                add("Norway");
            }
        } );
        denmark.setAirportCountry(new ArrayList<>(){
            {
                add("Norway");
            }
        } );
        Country lithuania = new Country("Lithuania", true, true, false, true, 2794000,"resources/lithuania.png","resources/lithuaniamap.png");
        world.setOneCountry(lithuania);
        lithuania.setNeighbourCountry(new ArrayList<>(){
            {
                add("Latvia");
                add("Bielorussia");
                add("Poland");
            }
        } );
        lithuania.setPortCountry(new ArrayList<>(){
            {
                add("Norway");
            }
        });
        lithuania.setAirportCountry(new ArrayList<>(){
            {
                add("Norway");
            }
        });
        Country bielorussia = new Country("Bielorussia", false, false, false, true, 9467000,"resources/bielorussia.png","resources/bielorussiamap.jpg");
        world.setOneCountry(bielorussia);
        bielorussia.setNeighbourCountry(new ArrayList<>(){
            {
                add("Russia");
                add("Latvia");
                add("Lithuania");
                add("Poland");
                add("Ukraine");
            }
        } );
        bielorussia.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        bielorussia.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country poland = new Country("Poland", true, true, true, false, 37970000, 1,"resources/poland.png","resources/polandmap.png");
        world.setOneCountry(poland);
        poland.setNeighbourCountry(new ArrayList<>(){
            {
                add("Bielorussia");
                add("Lithuania");
                add("Czech Republic");
                add("Germany");
                add("Ukraine");
            }
        } );
        poland.setPortCountry(new ArrayList<>(){
            {
                add("Sweden");
            }
        });
        poland.setAirportCountry(new ArrayList<>(){
            {
                add("Sweden");
            }
        });
        Country germany = new Country("Germany", true, true, false, true, 83020000,"resources/germany.png","resources/germanymap.png");
        world.setOneCountry(germany);
        germany.setNeighbourCountry(new ArrayList<>(){
            {
                add("Belgium");
                add("France");
                add("Switzerland");
                add("Poland");
                add("Czech Republic");
                add("Italy");
            }
        } );
        germany.setPortCountry(new ArrayList<>(){
            {
                add("United Kingdom");
            }
        });
        germany.setAirportCountry(new ArrayList<>(){
            {
                add("United Kingdom");
            }
        });
        Country belgium = new Country("Belgium", false, false, false, true, 11460000,"resources/belgium.png","resources/belgiummap.png");
        world.setOneCountry(belgium);
        belgium.setNeighbourCountry(new ArrayList<>(){
            {
                add("Germany");
                add("France");
            }
        } );
        belgium.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        belgium.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country france = new Country("France", true, true, true, false, 67060000,"resources/france.png","resources/francemap.png");
        world.setOneCountry(france);
        france.setNeighbourCountry(new ArrayList<>(){
            {
                add("Belgium");
                add("Germany");
                add("Switzerland");
                add("Spain");
                add("Italy");
            }
        } );
        france.setPortCountry(new ArrayList<>(){
            {
                add("United Kingdom");
            }
        });
        france.setAirportCountry(new ArrayList<>(){
            {
                add("United Kingdom");
            }
        });
        Country czech = new Country("Czech Republic", false, false, true, false, 10650000,"resources/czech.png","resources/czechmap.png");
        world.setOneCountry(czech);
        czech.setNeighbourCountry(new ArrayList<>(){
            {
                add("Poland");
                add("Germany");
            }
        } );
        czech.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        czech.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country ukraine = new Country("Ukraine", false, false, false, true, 44390000,"resources/ukraine.png","resources/ukrainemap.jpg");
        world.setOneCountry(ukraine);
        ukraine.setNeighbourCountry(new ArrayList<>(){
            {
                add("Poland");
                add("Bielorussia");
                add("Moldova");
                add("Russia");
                add("Romania");
                add("Hungary");
            }
        } );
        ukraine.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        ukraine.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country moldova = new Country("Moldova", true, true, false, true, 2658000,"resources/moldova.png","resources/moldovamap.png");
        world.setOneCountry(moldova);
        moldova.setNeighbourCountry(new ArrayList<>(){
            {
                add("Ukraine");
                add("Romania");
            }
        } );
        moldova.setPortCountry(new ArrayList<>(){
            {
                add("Turkey");
            }
        });
        moldova.setAirportCountry(new ArrayList<>(){
            {
                add("Turkey");
            }
        });
        Country hungary = new Country("Hungary", false, false, true, false, 9773000,"resources/hungary.png","resources/hungarymap.png");
        world.setOneCountry(hungary);
        hungary.setNeighbourCountry(new ArrayList<>(){
            {
                add("Ukraine");
                add("Romania");
            }
        } );
        hungary.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        hungary.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country switzerland = new Country("Switzerland", false, false, true, false, 8545000,"resources/switzerland.png","resources/switzerlandmap.png");
        world.setOneCountry(switzerland);
        switzerland.setNeighbourCountry(new ArrayList<>(){
            {
                add("France");
                add("Germany");
                add("Italy");
            }
        } );
        switzerland.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        switzerland.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country portugal = new Country("Portugal", false, false, false, true, 10280000,"resources/portugal.png","resources/portugalmap.png");
        world.setOneCountry(portugal);
        portugal.setNeighbourCountry(new ArrayList<>(){
            {
                add("Spain");
            }
        } );
        portugal.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        portugal.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country spain = new Country("Spain", true, true, true, false, 46940000,"resources/spain.png","resources/spainmap.png");
        world.setOneCountry(spain);
        spain.setNeighbourCountry(new ArrayList<>(){
            {
                add("Portugal");
                add("France");
            }
        } );
        spain.setPortCountry(new ArrayList<>(){
            {
                add("Ireland");
                add("Iceland");
                add("Greece");
                add("Italy");
            }
        });
        spain.setAirportCountry(new ArrayList<>(){
            {
                add("Ireland");
                add("Iceland");
                add("Greece");
                add("Italy");
            }
        });
        Country italy = new Country("Italy", true, true, false, true, 60360000,"resources/italy.png","resources/italymap.png");
        world.setOneCountry(italy);
        italy.setNeighbourCountry(new ArrayList<>(){
            {
                add("France");
                add("Switzerland");
            }
        } );
        italy.setPortCountry(new ArrayList<>(){
            {
                add("Spain");
            }
        });
        italy.setAirportCountry(new ArrayList<>(){
            {
                add("Spain");
            }
        });
        Country albania = new Country("Albania", false, false, true, false, 2862000,"resources/albania.png","resources/albaniamap.png");
        world.setOneCountry(albania);
        albania.setNeighbourCountry(new ArrayList<>(){
            {
                add("Greece");
                add("Bulgaria");
            }
        } );
        albania.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        albania.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country romania = new Country("Romania", false, false, true, false, 19410000,"resources/romania.png","resources/romaniamap.png");
        world.setOneCountry(romania);
        romania.setNeighbourCountry(new ArrayList<>(){
            {
                add("Hungary");
                add("Moldova");
                add("Ukraine");
                add("Bulgaria");
            }
        } );
        romania.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        romania.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country bulgaria = new Country("Bulgaria", false, false, false, true, 7000000,"resources/bulgaria.png","resources/bulgariamap.jpg");
        world.setOneCountry(bulgaria);
        bulgaria.setNeighbourCountry(new ArrayList<>(){
            {
                add("Romania");
                add("Turkey");
                add("Greece");
            }
        } );
        bulgaria.setPortCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        bulgaria.setAirportCountry(new ArrayList<>(){
            {
                add("Empty");
            }
        });
        Country turkey = new Country("Turkey", true, true, false, true, 82000000,"resources/turkey.png","resources/turkeymap.png");
        world.setOneCountry(turkey);
        turkey.setNeighbourCountry(new ArrayList<>(){
            {
                add("Bulgaria");
                add("Greece");
            }
        } );
        turkey.setPortCountry(new ArrayList<>(){
            {
                add("Moldova");
            }
        });
        turkey.setAirportCountry(new ArrayList<>(){
            {
                add("Moldova");
            }
        });
        Country greece = new Country("Greece", true, true, false, true, 10720000,"resources/greece.png","resources/greecemap.png");
        world.setOneCountry(greece);
        greece.setNeighbourCountry(new ArrayList<>(){
            {
                add("Turkey");
                add("Albania");
                add("Bulgaria");
            }
        } );
        greece.setPortCountry(new ArrayList<>(){
            {
                add("Spain");
            }
        });
        greece.setAirportCountry(new ArrayList<>(){
            {
                add("Spain");
            }
        });
        return world;
    }
}

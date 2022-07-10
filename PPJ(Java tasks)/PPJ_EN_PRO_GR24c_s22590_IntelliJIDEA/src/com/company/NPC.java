package com.company;

public class NPC extends Object {
    public Item[] items;
    public String npcName;

    public NPC(String objectName, int objectCoordinateX, int objectCoordinateY) {
        super(objectName, objectCoordinateX, objectCoordinateY);
    }

    public NPC(int i) {
        npcName = "trader" + i;
        objectName = "trader";
        objectCoordinateX = 1;
        objectCoordinateY = 1;
    }

    public NPC(String npcName, Item[] items) {
        this.npcName = npcName;
        this.items = items;
        objectName = "trader";
        objectCoordinateX = 1;
        objectCoordinateY = 1;
    }

    @Override
    public String checkCoordinates() {
        return npcName + " coordinates are: " + "(X: " + objectCoordinateX + ", Y: " + objectCoordinateY + ")";
    }

    public void showItemsOfTrader(NPC trader) {
        Item[] items = trader.getItems();
        System.out.println("Hello wanderer, I am a " + trader.getNpcName() + " and I want to sell you something.\nHere is all my items, that you can buy:");
        for (int i = 0, j = 1; i < items.length; i++, j++) {
            if (trader.getNpcName().equals("All-seeing Master")) {
                System.out.println(j + ". " + trader.items[i].itemName + " - " + trader.items[i].itemPrice + " coins");
            } else {
                System.out.println(j + ". " + trader.items[i].itemName + "(" + trader.items[i].itemDescription + ")" + " - " + trader.items[i].itemPrice + " coins");
            }
        }
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public String getNpcName() {
        return npcName;
    }

    public void setNpcName(String npcName) {
        this.npcName = npcName;
    }

}

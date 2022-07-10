package com.company;

public class Item {
    public String itemName;
    public int itemPrice;
    public String itemDescription;

    public Item(String itemName, int itemPrice, String itemDescription) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}

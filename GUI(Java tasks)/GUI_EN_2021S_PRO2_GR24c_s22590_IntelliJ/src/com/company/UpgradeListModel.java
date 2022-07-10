package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class UpgradeListModel extends AbstractListModel {
    ArrayList<Upgrade> upgrades;

    public UpgradeListModel(ArrayList<Upgrade> upgrades) {
        this.upgrades = upgrades;
    }

    @Override
    public int getSize() {
        return upgrades.size();
    }

    @Override
    public Object getElementAt(int index) {
        return upgrades.get(index);
    }

    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(ArrayList<Upgrade> upgrades) {
        this.upgrades = upgrades;
    }

    public void sort(){
        Collections.sort(upgrades);
        fireContentsChanged(this, 0, upgrades.size());
    }
}

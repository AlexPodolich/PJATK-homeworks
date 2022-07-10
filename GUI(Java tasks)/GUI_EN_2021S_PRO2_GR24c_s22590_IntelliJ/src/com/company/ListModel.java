package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class ListModel extends AbstractListModel {
    ArrayList<Person> people;

    public ListModel(ArrayList<Person> people){
        this.people = people;
    }

    @Override
    public int getSize() {
        return people.size();
    }

    @Override
    public Object getElementAt(int index) {
        return people.get(index);
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public void sort(){
        Collections.sort(people);
        fireContentsChanged(this, 0, people.size());
    }
}

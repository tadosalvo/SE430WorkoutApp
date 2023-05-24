package com.tadosalvo.se430project;

import java.util.ArrayList;

public class DietPlan {

    private String title;
    private int totalCalories;
    private ArrayList items;


    public DietPlan(String title) {
        this.title = title;
        this.totalCalories = 0;
        this.items = new ArrayList();
    }

    public void add(String item, String cal){
        items.add(item);
        addCal(Integer.valueOf(cal));
    }

    public void remove(String item){
        items.remove(item);
    }

    public int getTotalCal(){
        return totalCalories;
    }

    public void addCal(int cal){
        totalCalories += cal;
    }

    public ArrayList<String> getItems(){
        return items;
    }
}

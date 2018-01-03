package com.example.android.dataanalysis;

import java.util.ArrayList;

/**
 * Created by patri on 12/20/2017.
 */

public class Person {

    private String name;
    private double averageTemp;
    private double lowestTemp;
    private double highestTemp;


    public Person(String name, double averageTemp, double lowestTemp, double highestTemp){
        this.name = name;
        this.averageTemp = averageTemp;
        this.lowestTemp = lowestTemp;
        this.highestTemp = highestTemp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageTemp() {
        return averageTemp;
    }

    public void setAverageTemp(double averageTemp) {
        this.averageTemp = averageTemp;


    }

    public double getLowestTemp() {
        return lowestTemp;
    }

    public void setLowestTemp(double lowestTemp) {
        this.lowestTemp = lowestTemp;
    }

    public double getHighestTemp() {
        return highestTemp;
    }

    public void setHighestTemp(double highestTemp) {
        this.highestTemp = highestTemp;
    }
    public String toString(){
        return name + ", Average: " + averageTemp + ", Lowest: " + lowestTemp + ", highest: " +
                highestTemp;
    }
}



package com.example.needforneedy;

import java.io.Serializable;

public class Donatemodel implements Serializable {
    String Name, MOBILE_NUMBER, FULL_ADDRESS, typeOfFood, foodDrop, txtLocation;
    String Spinner;

    public Donatemodel(String name, String MOBILE_NUMBER, String FULL_ADDRESS, String typeOfFood, String foodDrop, String txtLocation, String spinner) {
        Name = name;
        this.MOBILE_NUMBER = MOBILE_NUMBER;
        this.FULL_ADDRESS = FULL_ADDRESS;
        this.typeOfFood = typeOfFood;
        this.foodDrop = foodDrop;
        this.txtLocation = txtLocation;
        Spinner = spinner;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMOBILE_NUMBER() {
        return MOBILE_NUMBER;
    }

    public void setMOBILE_NUMBER(String MOBILE_NUMBER) {
        this.MOBILE_NUMBER = MOBILE_NUMBER;
    }

    public String getFULL_ADDRESS() {
        return FULL_ADDRESS;
    }

    public void setFULL_ADDRESS(String FULL_ADDRESS) {
        this.FULL_ADDRESS = FULL_ADDRESS;
    }

    public String getTypeOfFood() {
        return typeOfFood;
    }

    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public String getFoodDrop() {
        return foodDrop;
    }

    public void setFoodDrop(String foodDrop) {
        this.foodDrop = foodDrop;
    }

    public String getTxtLocation() {
        return txtLocation;
    }

    public void setTxtLocation(String txtLocation) {
        this.txtLocation = txtLocation;
    }

    public String getSpinner() {
        return Spinner;
    }

    public void setSpinner(String spinner) {
        Spinner = spinner;
    }
}
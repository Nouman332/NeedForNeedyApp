package com.example.needforneedy;

import java.io.Serializable;

public class RequestModel implements Serializable {
    String Name,MOBILE_NUMBER,FULL_ADDRESS,foodType,spinner,txtLocation;;




    public RequestModel(String name, String MOBILE_NUMBER, String FULL_ADDRESS, String foodType, String spinner, String txtLocation) {
        Name = name;
        this.MOBILE_NUMBER = MOBILE_NUMBER;
        this.FULL_ADDRESS = FULL_ADDRESS;
        this.foodType = foodType;
        this.spinner = spinner;
        this.txtLocation = txtLocation;
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

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }


    public String getTxtLocation() {
        return txtLocation;
    }

    public void setTxtLocation(String txtLocation) {
        this.txtLocation = txtLocation;
    }
}

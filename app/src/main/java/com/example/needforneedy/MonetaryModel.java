package com.example.needforneedy;

import java.io.Serializable;

public class MonetaryModel implements Serializable {
    String etAmount, etName, etNumber;

    public MonetaryModel(String etAmount, String etName, String etNumber) {
        this.etAmount = etAmount;
        this.etName = etName;
        this.etNumber = etNumber;
    }

    public String getEtAmount() {
        return etAmount;
    }

    public void setEtAmount(String etAmount) {
        this.etAmount = etAmount;
    }

    public String getEtName() {
        return etName;
    }

    public void setEtName(String etName) {
        this.etName = etName;
    }

    public String getEtNumber() {
        return etNumber;
    }

    public void setEtNumber(String etNumber) {
        this.etNumber = etNumber;
    }
}

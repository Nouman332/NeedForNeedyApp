package com.example.needforneedy;

import java.io.Serializable;

public class NGOmodel implements Serializable {
    String NGO_name,etWebsite,email,phone,etAddress;

    public NGOmodel(String NGO_name, String etWebsite, String email, String phone, String etAddress) {
        this.NGO_name = NGO_name;
        this.etWebsite = etWebsite;
        this.email = email;
        this.phone = phone;
        this.etAddress = etAddress;
    }

    public String getNGO_name() {
        return NGO_name;
    }

    public void setNGO_name(String NGO_name) {
        this.NGO_name = NGO_name;
    }

    public String getEtWebsite() {
        return etWebsite;
    }

    public void setEtWebsite(String etWebsite) {
        this.etWebsite = etWebsite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEtAddress() {
        return etAddress;
    }

    public void setEtAddress(String etAddress) {
        this.etAddress = etAddress;
    }
}

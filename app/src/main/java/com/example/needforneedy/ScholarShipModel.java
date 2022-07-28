package com.example.needforneedy;

import java.io.Serializable;

public class ScholarShipModel implements Serializable {
    String etFullName, etFatherName,etCNIC,etPhoneNo,etEmail,etHomeAddress,etGender,etMatricMarks,etFSC;

    public ScholarShipModel(String etFullName, String etFatherName, String etCNIC, String etPhoneNo, String etEmail, String etHomeAddress, String etGender, String etMatricMarks, String etFSC) {
        this.etFullName = etFullName;
        this.etFatherName = etFatherName;
        this.etCNIC = etCNIC;
        this.etPhoneNo = etPhoneNo;
        this.etEmail = etEmail;
        this.etHomeAddress = etHomeAddress;
        this.etGender = etGender;
        this.etMatricMarks = etMatricMarks;
        this.etFSC = etFSC;
    }

    public String getEtFullName() {
        return etFullName;
    }

    public void setEtFullName(String etFullName) {
        this.etFullName = etFullName;
    }

    public String getEtFatherName() {
        return etFatherName;
    }

    public void setEtFatherName(String etFatherName) {
        this.etFatherName = etFatherName;
    }

    public String getEtCNIC() {
        return etCNIC;
    }

    public void setEtCNIC(String etCNIC) {
        this.etCNIC = etCNIC;
    }

    public String getEtPhoneNo() {
        return etPhoneNo;
    }

    public void setEtPhoneNo(String etPhoneNo) {
        this.etPhoneNo = etPhoneNo;
    }

    public String getEtEmail() {
        return etEmail;
    }

    public void setEtEmail(String etEmail) {
        this.etEmail = etEmail;
    }

    public String getEtHomeAddress() {
        return etHomeAddress;
    }

    public void setEtHomeAddress(String etHomeAddress) {
        this.etHomeAddress = etHomeAddress;
    }

    public String getEtGender() {
        return etGender;
    }

    public void setEtGender(String etGender) {
        this.etGender = etGender;
    }

    public String getEtMatricMarks() {
        return etMatricMarks;
    }

    public void setEtMatricMarks(String etMatricMarks) {
        this.etMatricMarks = etMatricMarks;
    }

    public String getEtFSC() {
        return etFSC;
    }

    public void setEtFSC(String etFSC) {
        this.etFSC = etFSC;
    }
}

package com.example.zloiy.marriage_agency.DataBase;

public class Agency {
    private String agencyName;
    private int telID;
    private int webID;
    private int emailID;
    private int streetID;

    public Agency(){}

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public int getTelID() {
        return telID;
    }

    public void setTelID(int telID) {
        this.telID = telID;
    }

    public int getWebID() {
        return webID;
    }

    public void setWebID(int webID) {
        this.webID = webID;
    }

    public int getEmailID() {
        return emailID;
    }

    public void setEmailID(int emailID) {
        this.emailID = emailID;
    }

    public int getStreetID() {
        return streetID;
    }

    public void setStreetID(int streetID) {
        this.streetID = streetID;
    }
}

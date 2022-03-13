package com.hiaryabeer.restaurantreports.model;

public class totalCashModel {
    private   String SALES;
    private  String RETURNED;
    private  String NET;

    public totalCashModel() {
    }

    public String getSALES() {
        return SALES;
    }

    public void setSALES(String SALES) {
        this.SALES = SALES;
    }

    public String getRETURNED() {
        return RETURNED;
    }

    public void setRETURNED(String RETURNED) {
        this.RETURNED = RETURNED;
    }

    public String getNET() {
        return NET;
    }

    public void setNET(String NET) {
        this.NET = NET;
    }
}

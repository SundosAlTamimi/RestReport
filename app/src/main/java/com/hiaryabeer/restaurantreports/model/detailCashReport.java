package com.hiaryabeer.restaurantreports.model;

public class detailCashReport {
    private  String CNAME;
    private  String  AMOUNT;

    public detailCashReport() {
    }

    public String getCNAME() {
        return CNAME;
    }

    public void setCNAME(String CNAME) {
        this.CNAME = CNAME;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }
}

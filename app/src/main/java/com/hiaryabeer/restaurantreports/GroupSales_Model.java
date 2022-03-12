package com.hiaryabeer.restaurantreports;

public class GroupSales_Model {

    private String kind;
    private String model;
    private String group;
    private String qty;
    private String gross;
    private String gross_percent;
    private String discount;
    private String total_After_discount;
    private String tax;
    private String net;
    private String net_percent;
    private String fromDate;
    private String toDate;
    private String service;
    private String service_tax;

    public GroupSales_Model(String kind, String model, String group, String qty, String gross,
                            String gross_percent, String discount, String total_After_discount,
                            String tax, String net, String net_percent, String fromDate, String toDate,
                            String service, String service_tax) {

        this.kind = kind;
        this.model = model;
        this.group = group;
        this.qty = qty;
        this.gross = gross;
        this.gross_percent = gross_percent;
        this.discount = discount;
        this.total_After_discount = total_After_discount;
        this.tax = tax;
        this.net = net;
        this.net_percent = net_percent;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.service = service;
        this.service_tax = service_tax;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getGross_percent() {
        return gross_percent;
    }

    public void setGross_percent(String gross_percent) {
        this.gross_percent = gross_percent;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotal_After_discount() {
        return total_After_discount;
    }

    public void setTotal_After_discount(String total_After_discount) {
        this.total_After_discount = total_After_discount;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getNet_percent() {
        return net_percent;
    }

    public void setNet_percent(String net_percent) {
        this.net_percent = net_percent;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getService_tax() {
        return service_tax;
    }

    public void setService_tax(String service_tax) {
        this.service_tax = service_tax;
    }
}

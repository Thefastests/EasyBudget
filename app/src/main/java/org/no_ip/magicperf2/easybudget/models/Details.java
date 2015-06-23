package org.no_ip.magicperf2.easybudget.models;

/**
 * Created by steve on 6/21/15.
 */
public class Details {
    private String description;
    private String date;
    private Double quantity;

    public Details(String des){
        description=des;
        date=null;
        quantity=0.00;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}

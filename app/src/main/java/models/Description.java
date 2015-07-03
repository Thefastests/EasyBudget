package models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SteveTorres on 6/25/2015.
 */
public class Description implements Serializable{
    private int id;
    private String descriptionName;
    private double price;
    private String date;

    public Description(String name){
        descriptionName=name;
        price=0.0;
        id=name.toLowerCase().hashCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriptionName() {
        return descriptionName;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionName = descriptionName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

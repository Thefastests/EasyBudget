package org.no_ip.magicperf2.easybudget.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 6/21/15.
 */
public class Category implements Serializable{
    private String catName;
    private List<Details> details;
    private double totalCat;

    public Category(String name){
        catName = name;
        details = new ArrayList<Details>();
        totalCat = 0.00;
    }

    public void addDetails (String name, String date, Double quantity){
        Details detail = new Details(name);
        detail.setDate(date);
        detail.setQuantity(quantity);
        details.add(detail);

        this.updateTotalCat();
    }

    public void removeDetails(int position){
        details.remove(position);
        this.updateTotalCat();
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }
    public void updateTotalCat(){
        totalCat=0.00;
        for(int i=0;i<details.size();i++){
            totalCat+=details.get(i).getQuantity();
        }
    }
    public Double getTotalCat(){
        return totalCat;
    }
}

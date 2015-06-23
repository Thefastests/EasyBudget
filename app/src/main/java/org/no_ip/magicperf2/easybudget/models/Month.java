package org.no_ip.magicperf2.easybudget.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 6/21/15.
 */
public class Month implements Serializable {
    private List<Category> categories;
    private String monthName;
    private Double totalMonth;

    public Month(String name){
        monthName = name;
        categories = new ArrayList<Category>();
        totalMonth = 0.00;
    }

    public void addCategory(String name){
        categories.add(new Category(name));
    }

    public void removeCategory(int position){
        categories.remove(position);
        this.updateTotalMonth();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> month) {
        this.categories = month;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public void updateTotalMonth(){
        totalMonth = 0.00;
        for(int i=0;i<categories.size();i++){
            totalMonth+=categories.get(i).getTotalCat();
        }
    }

    public Double getTotalMonth(){
        return totalMonth;
    }
}

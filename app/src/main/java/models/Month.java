package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteveTorres on 6/25/2015.
 */
public class Month implements Serializable {
    private int id;
    private String monthName;
    private double totalMonth;
    private List<Category> categories;

    public Month(String name){
        monthName=name;
        totalMonth=0.0;
        categories = new ArrayList<Category>();
        id = name.toLowerCase().hashCode();
    }
    public boolean addCategory(Category category){
        int hash = category.getId();
        for(int i=0;i<this.categories.size();i++){
            if(hash==categories.get(i).getId())
                return false;
        }
        this.categories.add(category);
        return true;
    }
    public boolean removeCategory(Category category){
        int hash = category.getId();
        for(int i=0;i<this.categories.size();i++){
            if(hash==categories.get(i).getId()){
                categories.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean modifyCategory(Category category){
        if(removeCategory(category))
            addCategory(category);
        else
            return false;
        return true;
    }
    public void updateMonthTotal(){
        totalMonth=0.0;
        for(int i=0;i<categories.size();i++){
            totalMonth+=categories.get(i).getTotalCat();
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public double getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(double totalMonth) {
        this.totalMonth = totalMonth;
    }
}

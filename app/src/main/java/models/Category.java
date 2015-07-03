package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteveTorres on 6/25/2015.
 */
public class Category implements Serializable {
    private int id;
    private String catName;
    private double totalCat;
    private List<Description> descriptions;

    public Category(String name){
        catName=name;
        totalCat=0.0;
        descriptions = new ArrayList<Description>();
        id=name.toLowerCase().hashCode();
    }
    public boolean addDescription(Description description){
        int hash = description.getId();
        for(int i=0;i<descriptions.size();i++){
            if(hash==descriptions.get(i).getId())
                return false;
        }
        descriptions.add(description);
        return true;
    }
    public boolean removeDescription(Description description){
        int hash = description.getId();
        for(int i=0;i<descriptions.size();i++){
            if(hash==descriptions.get(i).getId()){
                descriptions.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean modifyDescription(Description description){
        if(removeDescription(description))
            addDescription(description);
        else
            return false;
        return true;
    }
    public void updateTotalCat(){
        totalCat=0.0;
        for(int i=0;i<descriptions.size();i++){
            totalCat+=descriptions.get(i).getPrice();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public double getTotalCat() {
        return totalCat;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }
}

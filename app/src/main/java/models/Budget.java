package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteveTorres on 6/25/2015.
 */
public class Budget implements Serializable {
    private String budgetName;
    private double total;
    private List<Month> months;

    public Budget(){
        total=0.0;
        months = new ArrayList<Month>();
    }
    public boolean addMonth(Month month){
        for(int i =0 ; i<months.size();i++){
            if(month.getId()==months.get(i).getId())
                return false;
        }
        months.add(month);
        return true;
    }
    public boolean removeMonth(Month month){
        for(int i =0 ; i<months.size();i++){
            if(month.getId()==months.get(i).getId()){
                months.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean modifyMonth(Month month){
        if(removeMonth(month))
            addMonth(month);
        else
            return false;
        return true;
    }
    public void updateTotal(){
        total=0.0;
        for(int i =0 ; i<months.size();i++){
            total+=months.get(i).getTotalMonth();
        }
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Month> getMonths() {
        return months;
    }
}
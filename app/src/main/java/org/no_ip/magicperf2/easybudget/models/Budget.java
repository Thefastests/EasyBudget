package org.no_ip.magicperf2.easybudget.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 6/21/15.
 */
public class Budget implements Serializable {
    private List<Month> months;
    private String userName;
    private Double total;
    public Budget(String name){
        userName=name;
        months = new ArrayList<Month>();
        total = 0.00;
    }

    public void addMonth(String name){
        months.add(new Month(name));
    }

    public void removeMonth(int position){
        months.remove(position);

        this.updateTotal();
    }

    public List<Month> getMonths() {
        return months;
    }

    public void setMonths(List<Month> months) {
        this.months = months;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void updateTotal(){
        total=0.00;
        for(int i=0;i<months.size();i++){
            total+=months.get(i).getTotalMonth();
        }
    }
}

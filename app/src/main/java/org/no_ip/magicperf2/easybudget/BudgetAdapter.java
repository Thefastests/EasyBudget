package org.no_ip.magicperf2.easybudget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.no_ip.magicperf2.easybudget.models.Month;

import java.util.List;

/**
 * Created by steve on 6/21/15.
 */
public class BudgetAdapter extends ArrayAdapter<Month> {

private LayoutInflater inflater;
private List<Month> months;

public BudgetAdapter(Activity activity, List<Month> items){
        super(activity, R.layout.row_budget, items);
        months = items;
        inflater = activity.getWindow().getLayoutInflater();
        }

@Override
public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;
        if (v == null) {
        v = inflater.inflate(R.layout.row_budget, parent, false);
        }
        Month month = months.get(position);

        TextView monthName = (TextView) v.findViewById(R.id.monthName);
        monthName.setText(month.getMonthName());

        TextView monthTotal = (TextView) v.findViewById(R.id.monthTotal);
        monthTotal.setText("Total for Month: "+month.getTotalMonth().toString());

        return v;
        }
}

package org.no_ip.magicperf2.easybudget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.no_ip.magicperf2.easybudget.models.Category;
import org.no_ip.magicperf2.easybudget.models.Month;

import java.util.List;

/**
 * Created by steve on 6/22/15.
 */
public class MonthAdapter extends ArrayAdapter<Category> {

    private LayoutInflater inflater;
    private List<Category> categories;

    public MonthAdapter(Activity activity, List<Category> items){
        super(activity, R.layout.row_budget, items);
        categories = items;
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.row_month, parent, false);
        }
        Category category = categories.get(position);

        TextView monthName = (TextView) v.findViewById(R.id.catName);
        monthName.setText(category.getCatName());

        TextView monthTotal = (TextView) v.findViewById(R.id.catTotal);
        monthTotal.setText("Total for Category: "+category.getTotalCat().toString());

        return v;
    }
}

package org.no_ip.magicperf2.easybudget;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.no_ip.magicperf2.easybudget.models.Category;
import org.no_ip.magicperf2.easybudget.models.Details;

import java.util.List;

/**
 * Created by steve on 6/23/15.
 */
public class CategoryAdapter extends ArrayAdapter<Details> {

    private LayoutInflater inflater;
    private List<Details> details;

    public CategoryAdapter(Activity activity, List<Details> items){
        super(activity, R.layout.row_details, items);
        details = items;
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.row_details, parent, false);
        }
        Details detail = details.get(position);
        TextView detName = (TextView) v.findViewById(R.id.detailName);
        TextView detTotal = (TextView) v.findViewById(R.id.detailTotal);
        TextView detDate = (TextView) v.findViewById(R.id.detailDate);
        detName.setText(detail.getDescription());
        detTotal.setText("$"+String.valueOf(detail.getQuantity()));
        detDate.setText("Date: "+detail.getDate());

        return v;
    }
}

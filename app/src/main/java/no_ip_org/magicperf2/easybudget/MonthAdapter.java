package no_ip_org.magicperf2.easybudget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import models.Category;
import models.Month;

/**
 * Created by SteveTorres on 6/28/2015.
 */
public class MonthAdapter extends ArrayAdapter<Category> {
    private LayoutInflater inflater;
    private List<Category> categories;
    private AlertDialog mDialog;

    public MonthAdapter(Activity activity, Month m){
        super(activity,R.layout.row_display,m.getCategories());
        categories = m.getCategories();
        inflater=activity.getWindow().getLayoutInflater();
        //Create AlertDialog here
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to delete this?")
                .setIcon(R.drawable.trash)
                .setPositiveButton("Delete!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Use mListRowPosition for clicked list row...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object

        mDialog = builder.create();
    }

    @Override
    public View getView(final int position,View converterView,ViewGroup parent){
        View v = converterView;
        if(v==null){
            v=inflater.inflate(R.layout.row_display,parent,false);
        }
        Category category = categories.get(position);
        TextView catName = (TextView) v.findViewById(R.id.monthName);
        catName.setText(category.getCatName());

        TextView totalCat = (TextView) v.findViewById(R.id.monthTotal);
        DecimalFormat df = new DecimalFormat("#.00");
        totalCat.setText("Total: $"+String.valueOf(df.format(category.getTotalCat())));

        ImageButton delete = (ImageButton) v.findViewById(R.id.deleteMonthButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position);
            }
        });
        delete.setFocusable(false);

        ImageButton edit = (ImageButton) v.findViewById(R.id.editMonthButton);
        edit.setFocusable(false);
        notifyDataSetChanged();
        return v;
    }

    private void showDialog(int position){
        int mListRowPosition = position;
        if(mDialog != null)
            mDialog.show();
    }
}

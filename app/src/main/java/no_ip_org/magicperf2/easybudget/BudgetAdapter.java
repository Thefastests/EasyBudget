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

import models.Budget;
import models.Month;

/**
 * Created by SteveTorres on 6/25/2015.
 */
public class BudgetAdapter extends ArrayAdapter<Month>{
    private LayoutInflater inflater;
    private List<Month> months;
    private AlertDialog mDialog;

    public BudgetAdapter(Activity activity, Budget b){
        super(activity,R.layout.row_display,b.getMonths());
        months=b.getMonths();
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
        Month month = months.get(position);
        TextView monthName = (TextView) v.findViewById(R.id.monthName);
        monthName.setText(month.getMonthName());

        TextView totalMonth = (TextView) v.findViewById(R.id.monthTotal);
        DecimalFormat df = new DecimalFormat("#.00");
        totalMonth.setText("Total: $"+String.valueOf(df.format(month.getTotalMonth())));

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
        return v;
    }

    private void showDialog(int position){
        int mListRowPosition = position;
        if(mDialog != null)
            mDialog.show();
    }

}

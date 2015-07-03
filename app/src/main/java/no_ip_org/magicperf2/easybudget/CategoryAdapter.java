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
import models.Description;

/**
 * Created by SteveTorres on 6/29/2015.
 */
public class CategoryAdapter extends ArrayAdapter<Description> {
    private LayoutInflater inflater;
    private List<Description> descriptions;
    private AlertDialog mDialog;

    public CategoryAdapter(Activity activity,Category cat){
        super(activity,R.layout.row_display,cat.getDescriptions());
        inflater=activity.getWindow().getLayoutInflater();
        descriptions = cat.getDescriptions();
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
        Description description = descriptions.get(position);
        TextView desName = (TextView)v.findViewById(R.id.monthName);
        desName.setText(description.getDescriptionName());

        TextView desPrice = (TextView)v.findViewById(R.id.monthTotal);
        DecimalFormat df = new DecimalFormat("#.00");
        desPrice.setText("$"+String.valueOf(df.format(description.getPrice())));

        TextView desDate = (TextView)v.findViewById(R.id.date);
        desDate.setText(description.getDate());
        desDate.setVisibility(View.VISIBLE);

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

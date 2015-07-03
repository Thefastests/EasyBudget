package no_ip_org.magicperf2.easybudget;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import models.Budget;
import models.Description;


public class CatAddDesActivity extends ActionBarActivity {
    private Budget budget;
    private TextView desName;
    private TextView desPrice;
    private TextView desDate;
    private static final String FILE_NAME="budget.ser";
    private int monthPos;
    private int catPos;
    private CatAddDesActivity instance;
    private RadioButton income;
    private RadioButton expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_add_des);
        instance=this;
        budget = BudgetListActivity.getBudget();
        monthPos = getIntent().getIntExtra("monthPos", monthPos);
        income = (RadioButton)findViewById(R.id.incomeRadioButton);
        expense = (RadioButton)findViewById(R.id.expenseRadioButton);
        catPos = getIntent().getIntExtra("catPos",catPos);
        desName = (TextView)findViewById(R.id.desNewName);
        desPrice = (TextView)findViewById(R.id.desNewPrice);
        desDate = (TextView)findViewById(R.id.desNewDate);
        desDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                final String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                DatePickerDialog dp = new DatePickerDialog(CatAddDesActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String erg = String.valueOf(dayOfMonth);
                                erg = erg+" "+MONTH[monthOfYear];
                                erg += " " + year;
                                ((TextView) desDate).setText(erg);
                            }
                        }, y, m, d);
                dp.setTitle("EasyBudget Calendar");
                dp.setMessage("Select Your Date");
                dp.show();
            }
        });
        Button addDescription = (Button) findViewById(R.id.addNewDescription);
        addDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(desName.getText().toString().length()<1)
                    Toast.makeText(instance, "Description Name cannot be empty!", Toast.LENGTH_SHORT).show();
                else{
                    Description description = new Description(desName.getText().toString());
                    if(expense.isChecked())
                        description.setPrice((-1)*Double.parseDouble(desPrice.getText().toString()));
                    else
                        description.setPrice(Double.parseDouble(desPrice.getText().toString()));
                    description.setDate(desDate.getText().toString());
                    if(!budget.getMonths().get(monthPos).getCategories().get(catPos).addDescription(description))
                        Toast.makeText(instance,"Name already exists",Toast.LENGTH_SHORT).show();
                    else{
                        try{
                            for(int i=0;i<budget.getMonths().size();i++){
                                for(int j=0;j<budget.getMonths().get(i).getCategories().size();j++){
                                    budget.getMonths().get(i).getCategories().get(j).updateTotalCat();
                                }
                                budget.getMonths().get(i).updateMonthTotal();
                            }
                            budget.updateTotal();
                            Collections.sort(budget.getMonths().get(monthPos).getCategories().get(catPos).getDescriptions(), new Comparator<Description>() {
                                @Override
                                public int compare(Description lhs, Description rhs) {
                                    return (lhs.getDescriptionName().compareTo(rhs.getDescriptionName()));
                                }
                            });
                            FileOutputStream fos = BudgetListActivity.getInstance().openFileOutput(FILE_NAME,0);
                            ObjectOutputStream ois = new ObjectOutputStream(fos);
                            ois.writeObject(budget);
                            fos.close();
                            ois.close();
                            Log.d("EasyBudget", "Saving to file Successful");
                        }catch (Exception e){
                            Log.e("EasyBudget","Cannot write to file");
                        }
                        BudgetListActivity.setAndDisplay(budget);
                        MonthListActivity.getInstance().render(budget.getMonths().get(monthPos));
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cat_add_des, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

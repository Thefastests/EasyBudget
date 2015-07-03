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
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import models.Budget;
import models.Month;


public class BudgetAddMonthActivity extends ActionBarActivity {
    Budget budget;
    TextView monthNewName;
    private static final String FILE_NAME="budget.ser";
    BudgetAddMonthActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_add_month);
        instance =this;
        budget = (Budget)getIntent().getSerializableExtra("budget");
        monthNewName = (TextView) findViewById(R.id.monthNewName);
        monthNewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                final String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                DatePickerDialog dp = new DatePickerDialog(BudgetAddMonthActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String erg = "";
                                erg = MONTH[monthOfYear];
                                erg += " " + year;
                                ((TextView) monthNewName).setText(erg);
                            }
                        }, y, m, d);
                dp.setTitle("EasyBudget Calendar");
                dp.setMessage("Select Your Month and Year");
                dp.show();
            }
        });
        Button addMonth = (Button) findViewById(R.id.addNewMonth);
        addMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monthNewName.getText().toString().length()<3)
                    Toast.makeText(instance,"Month Name cannot be empty!",Toast.LENGTH_SHORT).show();
                else {
                    if(!budget.addMonth(new Month(monthNewName.getText().toString())))
                        Toast.makeText(instance,"Name already exists",Toast.LENGTH_SHORT).show();
                    else {
                        Collections.sort(budget.getMonths(), new Comparator<Month>() {
                            @Override
                            public int compare(Month lhs, Month rhs) {
                                return (lhs.getMonthName().compareTo(rhs.getMonthName()));
                            }
                        });
                        try {
                            FileOutputStream fos = BudgetListActivity.getInstance().openFileOutput(FILE_NAME, 0);
                            ObjectOutputStream ois = new ObjectOutputStream(fos);
                            ois.writeObject(budget);
                            fos.close();
                            ois.close();
                            Log.d("EasyBudget", "Saving to file Successful");
                        } catch (Exception e) {
                            Log.e("EasyBudget", "Cannot write to file");
                        }
                        BudgetListActivity.setAndDisplay(budget);
                        finish();
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget_add_month, menu);
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

package org.no_ip.magicperf2.easybudget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.no_ip.magicperf2.easybudget.models.Budget;
import org.no_ip.magicperf2.easybudget.models.Month;

import java.util.ArrayList;
import java.util.List;


public class MonthAddActivity extends ActionBarActivity {
    private Button addNewMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_add);
        Budget budget = (Budget) getIntent().getSerializableExtra("budget");
        if(budget == null){
            Log.e("EasyBudget","budget is null");
        }
        final List<Month> months = budget.getMonths();
        addNewMonth = (Button) findViewById(R.id.addNewMonth);
        addNewMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView monthName = (TextView) findViewById(R.id.monthNewName);
                Month month = new Month(monthName.getText().toString());
                new AsyncAddMonth(MonthAddActivity.this,month).execute(months);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_month_add, menu);
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

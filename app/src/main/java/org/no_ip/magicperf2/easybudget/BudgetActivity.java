package org.no_ip.magicperf2.easybudget;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.no_ip.magicperf2.easybudget.models.Budget;
import org.no_ip.magicperf2.easybudget.models.Month;

import java.util.ArrayList;
import java.util.List;


public class BudgetActivity extends ListActivity {
    private Button addMonthButton;
    private Budget budget;
    private static BudgetActivity instance;
    private ArrayAdapter monthItemArrayAdapter;
    private List<Month> months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        instance=this;
        budget = new Budget("User");
        months = budget.getMonths();
        addMonthButton = (Button) findViewById(R.id.addMonthButton);
        addMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BudgetActivity.this,MonthAddActivity.class);
                intent.putExtra("budget",budget);
                startActivity(intent);
                //new AsyncAddMonth(BudgetActivity.this,month).execute(months);
            }
        });
        new AsyncFetchMonth(this).execute(months);
    }

    public static BudgetActivity getInstance(){
        return instance;
    }

    public void renderMonths(List<Month> months) {
        try{
            monthItemArrayAdapter = new BudgetAdapter(this, months);
            setListAdapter(monthItemArrayAdapter);
        }catch(Exception e){
            Log.d("codelearn", "Cannot show months");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget, menu);
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Month month = (Month) getListAdapter().getItem(position);
        Intent intent = new Intent(this, MonthActivity.class);
        intent.putExtra("month",month);
        intent.putExtra("budget",budget);
        startActivity(intent);
    }
}

package no_ip_org.magicperf2.easybudget;

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

import models.Budget;
import models.Month;


public class BudgetListActivity extends ListActivity {
    private static Budget budget;
    private ArrayAdapter budgetItemArray;
    private Button addMonth;
    private static BudgetListActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_list);
        instance = this;
        budget=(Budget)getIntent().getSerializableExtra("budget");
        addMonth = (Button) findViewById(R.id.addMonthButton);
        addMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BudgetListActivity.this,BudgetAddMonthActivity.class);
                intent.putExtra("budget",budget);
                startActivity(intent);
            }
        });
        renderActivity(budget);
    }

    public void renderActivity(Budget b){
        try{
            budgetItemArray = new BudgetAdapter(this,b);
            setListAdapter(budgetItemArray);
        }catch (Exception e){
            Log.e("EasyBudget","Cannot read months");
        }
    }

    public static BudgetListActivity getInstance(){
        return instance;
    }

    public static Budget getBudget(){
        return budget;
    }

    public static void setAndDisplay(Budget b){
        budget=b;
        getInstance().renderActivity(budget);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget_list, menu);
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
    protected void onListItemClick(ListView l,View v,int position,long id){
        Month month = (Month) getListAdapter().getItem(position);
        Intent intent = new Intent(BudgetListActivity.this,MonthListActivity.class);
        intent.putExtra("monthPosition", position);
        startActivity(intent);
    }

}

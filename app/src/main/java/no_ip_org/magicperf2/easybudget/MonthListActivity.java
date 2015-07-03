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
import models.Category;
import models.Month;


public class MonthListActivity extends ListActivity {
    private Budget budget;
    private Button addCategory;
    private static MonthListActivity instance;
    private ArrayAdapter monthItemArray;
    private int monthPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_list);
        instance=this;
        budget = BudgetListActivity.getBudget();
        addCategory = (Button) findViewById(R.id.addCategoryButton);
        monthPos = (Integer)getIntent().getIntExtra("monthPosition",monthPos);
        Month month = budget.getMonths().get(monthPos);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonthListActivity.this,MonthAddCategoryActivity.class);
                intent.putExtra("monthPosition", monthPos);
                startActivity(intent);
            }
        });
        render(month);
    }

    public void render(Month month){
        try{
            monthItemArray = new MonthAdapter(this,month);
            setListAdapter(monthItemArray);
        }catch(Exception e){
            Log.e("EasyBudget", "Cannot read categories");
        }
    }

    public static MonthListActivity getInstance(){
        return instance;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_month_list, menu);
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
        Category category = (Category) getListAdapter().getItem(position);
        Intent intent = new Intent(MonthListActivity.this,CategoryListActivity.class);
        intent.putExtra("monthPos",monthPos);
        intent.putExtra("catPos",position);
        startActivity(intent);
    }
}

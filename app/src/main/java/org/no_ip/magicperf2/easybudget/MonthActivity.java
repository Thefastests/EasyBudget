package org.no_ip.magicperf2.easybudget;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.no_ip.magicperf2.easybudget.models.Budget;
import org.no_ip.magicperf2.easybudget.models.Category;
import org.no_ip.magicperf2.easybudget.models.Month;

import java.util.List;


public class MonthActivity extends ListActivity {
    private Button addCategory;
    private Month month;
    //private Budget budget;
    private int monthPosition;
    private static MonthActivity monthInstance;
    private ArrayAdapter categoryItemArrayAdapter;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        monthInstance = this;
        month = (Month) getIntent().getSerializableExtra("month");
        //budget = (Budget) getIntent().getSerializableExtra("budget");
        monthPosition = getIntent().getIntExtra("monthPosition",monthPosition);
        Log.d("codelearn","monthPosition "+monthPosition);
        //month = budget.getMonths().get(monthPosition);
        categories = month.getCategories();
        addCategory = (Button) findViewById(R.id.addCategoryButton);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonthActivity.this,CategoryAddActivity.class);
                intent.putExtra("month",month);
                //intent.putExtra("budget",budget);
                intent.putExtra("monthPos",monthPosition);
                startActivity(intent);
            }
        });
        //renderCategories(categories);
        new AsyncFetchCategory(this,monthPosition).execute(categories);
    }

    public static MonthActivity getMonthInstance(){
        return monthInstance;
    }

    public void renderCategories(List<Category> categories) {
        try{
            categoryItemArrayAdapter = new MonthAdapter(this, categories);
            setListAdapter(categoryItemArrayAdapter);
        }catch(Exception e){
            Log.d("codelearn", "Cannot show months");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_month, menu);
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
        Category category = (Category) getListAdapter().getItem(position);
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category",category);
        intent.putExtra("monthPosition",monthPosition);
        intent.putExtra("categoryPosition",position);
        //intent.putExtra("budget",budget);
        startActivity(intent);
    }
}

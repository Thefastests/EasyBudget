package no_ip_org.magicperf2.easybudget;

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

import models.Budget;
import models.Category;
import models.Description;


public class CategoryListActivity extends ListActivity {
    private Budget budget;
    private Button addDescription;
    private static CategoryListActivity instance;
    private ArrayAdapter catArrayItemAdapter;
    Category category;
    private int monthPos;
    private int catPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        instance=this;
        budget = BudgetListActivity.getBudget();
        addDescription=(Button)findViewById(R.id.addDescriptionButton);
        monthPos = (Integer)getIntent().getIntExtra("monthPos", monthPos);
        catPos = (Integer)getIntent().getIntExtra("catPos",catPos);
        category = budget.getMonths().get(monthPos).getCategories().get(catPos);
        addDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryListActivity.this,CatAddDesActivity.class);
                intent.putExtra("monthPos",monthPos);
                intent.putExtra("catPos",catPos);
                startActivity(intent);
            }
        });
        render(category);
    }

    public CategoryListActivity getInstance(){
        return instance;
    }

    public void render(Category category){
        try{
            catArrayItemAdapter = new CategoryAdapter(this,category);
            setListAdapter(catArrayItemAdapter);
        }catch(Exception e){
            Log.e("EasyBudget", "Cannot show descriptions");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_list, menu);
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
        super.onListItemClick(l, v, position, id);
        Description description = category.getDescriptions().get(position);
        Intent intent = new Intent(CategoryListActivity.this,DetailsActivity.class);
        intent.putExtra("des",description);
        startActivity(intent);
    }
}

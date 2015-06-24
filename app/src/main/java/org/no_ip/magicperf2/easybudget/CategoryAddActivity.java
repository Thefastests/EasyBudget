package org.no_ip.magicperf2.easybudget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.no_ip.magicperf2.easybudget.models.Budget;
import org.no_ip.magicperf2.easybudget.models.Category;
import org.no_ip.magicperf2.easybudget.models.Month;

import java.util.List;


public class CategoryAddActivity extends ActionBarActivity {
    private Button addNewCategory;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add);
        //Budget budget = (Budget) getIntent().getSerializableExtra("budget");
        Month month = (Month) getIntent().getSerializableExtra("month");
        pos = getIntent().getIntExtra("monthPos",pos);
        final List<Category> categoryList = month.getCategories();
        addNewCategory = (Button) findViewById(R.id.addNewCat);
        addNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView catName = (TextView)findViewById(R.id.categoryNewName);
                Category category = new Category(catName.getText().toString());
                new AsyncAddCategory(CategoryAddActivity.this,category,pos).execute(categoryList);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_add, menu);
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

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

import org.no_ip.magicperf2.easybudget.models.Category;
import org.no_ip.magicperf2.easybudget.models.Details;

import java.util.List;


public class CategoryActivity extends ListActivity {
    private static CategoryActivity categoryInstance;
    private Category category;
    private int monthPosition;
    private int categoryPostition;
    private List<Details> details;
    private Button addCategory;
    private ArrayAdapter detailItemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryInstance=this;
        category = (Category) getIntent().getSerializableExtra("category");
        monthPosition = (Integer) getIntent().getIntExtra("monthPosition", monthPosition);
        categoryPostition = (Integer) getIntent().getIntExtra("categoryPosition",categoryPostition);
        details = category.getDetails();
        addCategory = (Button) findViewById(R.id.addDescriptionButton);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, DeailsAddActivity.class);
                intent.putExtra("categories", category);
                intent.putExtra("monthPosition", monthPosition);
                intent.putExtra("categoryPosition", categoryPostition);
                startActivity(intent);
            }
        });
        //renderDetails(category.getDetails());
        new AsyncFetchDetails(this,monthPosition,categoryPostition).execute(details);
        //new AsyncFetchCategory(this,monthPosition).execute(categories);
    }

    public static CategoryActivity getCategoryInstance(){
        return categoryInstance;
    }

    public void renderDetails(List<Details> details) {
        try{
            detailItemArrayAdapter = new CategoryAdapter(this, details);
            setListAdapter(detailItemArrayAdapter);
        }catch(Exception e){
            Log.d("codelearn", "Cannot show months");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
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
        Details details = (Details) getListAdapter().getItem(position);
        Intent intent = new Intent(this, DescriptionDetailsActivity.class);
        intent.putExtra("detail",details);
        //intent.putExtra("budget",budget);
        startActivity(intent);
    }
}

package no_ip_org.magicperf2.easybudget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;

import models.Budget;
import models.Category;


public class MonthAddCategoryActivity extends ActionBarActivity {
    Budget budget;
    TextView catNewName;
    private static final String FILE_NAME="budget.ser";
    int monthPos;
    private MonthAddCategoryActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_add_category);
        instance=this;
        monthPos = getIntent().getIntExtra("monthPosition",monthPos);
        budget = BudgetListActivity.getBudget();
        catNewName = (TextView) findViewById(R.id.catNewName);
        Button addCategory = (Button) findViewById(R.id.addNewCategory);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catNewName.getText().toString().length()<1)
                    Toast.makeText(instance, "Category Name cannot be empty!", Toast.LENGTH_SHORT).show();
                else{
                    if(!budget.getMonths().get(monthPos).addCategory(new Category(catNewName.getText().toString())))
                        Toast.makeText(instance,"Name already exists",Toast.LENGTH_SHORT).show();
                    else{
                        Collections.sort(budget.getMonths().get(monthPos).getCategories(), new Comparator<Category>() {
                            @Override
                            public int compare(Category lhs, Category rhs) {
                                return (lhs.getCatName().compareTo(rhs.getCatName()));
                            }
                        });
                        try{
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
        getMenuInflater().inflate(R.menu.menu_month_add_category, menu);
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

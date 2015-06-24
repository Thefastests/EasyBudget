package org.no_ip.magicperf2.easybudget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.no_ip.magicperf2.easybudget.models.Category;
import org.no_ip.magicperf2.easybudget.models.Details;


public class DeailsAddActivity extends ActionBarActivity {
    private Button addDetail;
    private Category cat;
    int monthPos;
    int catPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deails_add);
        cat = (Category) getIntent().getSerializableExtra("categories");
        monthPos = (Integer) getIntent().getIntExtra("monthPosition", monthPos);
        catPos = (Integer) getIntent().getIntExtra("categoryPosition", catPos);
        addDetail = (Button) findViewById(R.id.addNewDet);
        addDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = (TextView) findViewById(R.id.descriptionNewName);
                TextView total = (TextView) findViewById(R.id.descriptionNewQuantity);
                TextView date = (TextView) findViewById(R.id.descriptionNewDate);
                Details detail = new Details(name.getText().toString());
                detail.setDate(date.getText().toString());
                detail.setQuantity(Double.parseDouble(total.getText().toString()));
                new AsyncAddDetails(DeailsAddActivity.this,monthPos,catPos,detail).execute(cat.getDetails());
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deails_add, menu);
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

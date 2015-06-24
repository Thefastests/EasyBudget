package org.no_ip.magicperf2.easybudget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.no_ip.magicperf2.easybudget.models.Details;
import org.w3c.dom.Text;


public class DescriptionDetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_details);
        Details details = (Details) getIntent().getSerializableExtra("detail");
        TextView tittle = (TextView) findViewById(R.id.desTitle);
        TextView price = (TextView) findViewById(R.id.desPrice);
        TextView date = (TextView) findViewById(R.id.desDate);

        tittle.setText(details.getDescription());
        price.setText(details.getQuantity().toString());
        date.setText(details.getDate());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_description_details, menu);
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

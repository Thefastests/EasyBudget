package org.no_ip.magicperf2.easybudget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
    Button _loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("codelearn_twitter", MODE_PRIVATE);
        String valueUser = prefs.getString("key1", null);
        String valuePass = prefs.getString("key2", null);
        if(valueUser!=null && valuePass!=null){
            Intent intent = new Intent(this, TweetListActivity.class);
            startActivity(intent);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _loginBtn = ( Button ) findViewById(R.id.btn_login);
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.fld_username);
                String usernameValue = username.getText().toString();
                EditText password = (EditText) findViewById(R.id.fld_pwd);
                String passwordValue = password.getText().toString();
                Log.d("Codelearn", "username caught - " + usernameValue);
                SharedPreferences prefs = getSharedPreferences("codelearn_twitter", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                //editor.putString("key1",usernameValue);
                //editor.putString("key2",passwordValue);
                //editor.apply();
                Intent intent = new Intent(MainActivity.this, TweetListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

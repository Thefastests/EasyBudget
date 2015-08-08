package no_ip_org.magicperf2.easybudget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import models.Budget;

public class MainActivity extends Activity {

    private EditText username;
    private EditText password;
    private Budget budget;
    private static final String FILE_NAME="budget.ser";
    private final String LINKLOCAL="http://easybudgetapp.com/signin.php";
    private static MainActivity instance;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupVariables();
    }

    public void authenticateLogin() {
        budget=getBudget();
        Intent intent = new Intent(this,BudgetListActivity.class);
        intent.putExtra("budget", budget);
        startActivity(intent);
        if(progress!=null)
            progress.dismiss();
    }

    private Budget getBudget(){
        Budget b=null;
        try{
            FileInputStream fis = this.openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            b=(Budget)ois.readObject();
            b.setBudgetName("admin");
            Log.d("EasyBudget","Found budget, loading");
        }catch (Exception e){
            Log.e("EasyBudget","Cannot get file, new object done");
            b=new Budget();
        }
        return b;
    }

    public static MainActivity getInstance(){
        return instance;
    }

    private void setupVariables() {
        username = (EditText) findViewById(R.id.usernameET);
        password = (EditText) findViewById(R.id.passwordET);
        budget=null;
        instance = this;
    }

    public void loginPost(View view){
        progress = ProgressDialog.show(this, "Authenticating",
                "Please wait while we connect you", true);
        String usernameField = username.getText().toString();
        String passwordField = password.getText().toString();
        TextView status=null,role=null;
        new SigninActivity(this,status,role,1,LINKLOCAL).execute(usernameField, passwordField);
    }

    public void registerUser(View view){
        Intent intent=new Intent(MainActivity.this,RegistrationActivity.class);
        intent.putExtra("link","http://easybudgetapp.com/signup.php");
        startActivity(intent);
    }

    public ProgressDialog getProgress(){
        return progress;
    }
}

package no_ip_org.magicperf2.easybudget;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by SteveTorres on 7/2/2015.
 */
public class SigninActivity  extends AsyncTask<String,Void,String> {
    private TextView statusField,roleField;
    private Context context;
    private int byGetOrPost = 0;
    private String link;

    //flag 0 means get and 1 means post.(By default it is get.)
    public SigninActivity(Context context,TextView statusField,TextView roleField,int flag,String link) {
        this.context = context;
        this.statusField = statusField;
        this.roleField = roleField;
        byGetOrPost = flag;
        this.link=link;
    }

    @Override
    protected String doInBackground(String... arg0) {

            try{
                String username = (String)arg0[0];
                String password = (String)arg0[1];

                String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                Log.d("EasyBudget","Link:"+link);
                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }
                return sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

    @Override
    protected void onPostExecute(String result){
        Log.d("EasyBudget", "result: " + result);
        if(result.equals("<!DOCTYPE html>")){
            Toast.makeText(context,"Login Successful",Toast.LENGTH_SHORT).show();
            MainActivity.getInstance().authenticateLogin();
        }
        else{
            Toast.makeText(context,"Login FAILED! "+result,Toast.LENGTH_SHORT).show();
            MainActivity.getInstance().getProgress().dismiss();
        }
        //this.statusField.setText("Login Successful");
        //this.roleField.setText(result);
    }
}

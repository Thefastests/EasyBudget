package no_ip_org.magicperf2.easybudget;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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
public class Registration extends AsyncTask<String,Void,String> {
    private Context context;

    public Registration(Context context){
        this.context=context;
    }
    @Override
    protected String doInBackground(String... arg0) {
        try{
            String username = (String)arg0[0];
            String password = (String)arg0[1];

            String link="http://192.168.0.23/registration.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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
        Log.d("EasyBudget","result: "+result);
        Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show();
        MainActivity.getInstance().authenticateLogin();
        /*if(result.equals("admin")){

        }
        else
            Toast.makeText(context,"Registration FAILED! "+result,Toast.LENGTH_SHORT).show();
        //this.statusField.setText("Login Successful");
        //this.roleField.setText(result);*/
    }
}

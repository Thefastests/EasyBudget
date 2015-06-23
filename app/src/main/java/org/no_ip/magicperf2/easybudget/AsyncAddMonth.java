package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Month;
import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 6/21/15.
 */
public class AsyncAddMonth extends AsyncTask<List<Month>,Void,List<Month>> {
    private List<Month> monthsRead;
    private static final String MONTHS_CACHE_FILE = "month_cache.ser";
    private MonthAddActivity activity;
    private Month monthToAdd;


    public AsyncAddMonth(MonthAddActivity act,Month m) {
        activity=act;
        monthToAdd=m;
    }

    @Override
    protected List<Month> doInBackground(List<Month>... params) {
        monthsRead=params[0];
        try {
            //Thread.sleep(5000);
            FileInputStream fis = activity.openFileInput(MONTHS_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            monthsRead = (List<Month>) ois.readObject();
            if(monthsRead==null) monthsRead = new ArrayList<Month>();
            //close file handles
            ois.close();
            fis.close();
        }catch (Exception e){
            //log exceptions (at least)
            Log.e("codelearn", "Error reading months", e);
        }
        monthsRead.add(monthToAdd);
        try{
            FileOutputStream fos = activity.openFileOutput(MONTHS_CACHE_FILE, 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(monthsRead);
            Log.d("codelearn", "Successfully wrote months to the file.");
            oos.close();
            fos.close();
        }catch(Exception e){
            Log.e("codelearn", "Error writing months", e);
        }
        return monthsRead;
    }

    protected void onPostExecute(List<Month> result) {
        BudgetActivity.getInstance().renderMonths(result);
    }
}

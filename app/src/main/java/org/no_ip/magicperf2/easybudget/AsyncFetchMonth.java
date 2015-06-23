package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Month;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by steve on 6/21/15.
 */
public class AsyncFetchMonth extends AsyncTask<List<Month>,Void,List<Month>> {

    private List<Month> monthsRead;
    private static final String MONTH_CACHE_FILE = "month_cache.ser";
    private BudgetActivity activity;

    public AsyncFetchMonth(BudgetActivity act){
        activity = act;
    }

    @Override
    protected List<Month> doInBackground(List<Month>... params) {
        monthsRead = params[0];
        try {
            //Thread.sleep(5000);
            FileInputStream fis = activity.openFileInput(MONTH_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            monthsRead = (List<Month>) ois.readObject();
            //close file handles
            ois.close();
            fis.close();
        }catch (Exception e){
            //log exceptions (at least)
            Log.e("codelearn", "Error reading months", e);
        }
        return monthsRead;
    }
    protected void onPostExecute(List<Month> result) {
        //pass result to main UI thread to show the final data
        activity.renderMonths(result);
    }
}

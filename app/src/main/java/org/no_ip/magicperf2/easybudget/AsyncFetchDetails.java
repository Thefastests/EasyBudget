package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Category;
import org.no_ip.magicperf2.easybudget.models.Details;
import org.no_ip.magicperf2.easybudget.models.Month;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by steve on 6/23/15.
 */
public class AsyncFetchDetails extends AsyncTask<List<Details>,Void,List<Details>> {
    private CategoryActivity activity;
    private int monthPos;
    private int catPos;
    private static final String MONTH_CACHE_FILE = "month_cache.ser";
    private List<Month> monthsRead;
    private List<Details> details;

    public AsyncFetchDetails(CategoryActivity act, int mPos, int cPos){
        activity=act;
        monthPos=mPos;
        catPos=cPos;
    }

    @Override
    protected List<Details> doInBackground(List<Details>... params) {
        details=params[0];
        try {
            //Thread.sleep(5000);
            FileInputStream fis = activity.openFileInput(MONTH_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            monthsRead = (List<Month>) ois.readObject();
            details = monthsRead.get(monthPos).getCategories().get(catPos).getDetails();
            //close file handles
            ois.close();
            fis.close();
        }catch (Exception e){
            //log exceptions (at least)
            Log.e("codelearn", "Error reading months", e);
        }
        return details;
    }
    @Override
    protected void onPostExecute(List<Details>det){
        activity.renderDetails(det);
    }
}

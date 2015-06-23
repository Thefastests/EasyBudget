package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Category;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by steve on 6/22/15.
 */
public class AsyncFetchCategory extends AsyncTask<List<Category>,Void,List<Category>> {

    private List<Category> categoriesRead;
    private static final String MONTH_CACHE_FILE = "month_cache.ser";
    private MonthActivity activity;

    public AsyncFetchCategory(MonthActivity act){
        activity = act;
    }

    @Override
    protected List<Category> doInBackground(List<Category>... params) {
        categoriesRead = params[0];
        try {
            //Thread.sleep(5000);
            FileInputStream fis = activity.openFileInput(MONTH_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            categoriesRead = (List<Category>) ois.readObject();
            //close file handles
            ois.close();
            fis.close();
        }catch (Exception e){
            //log exceptions (at least)
            Log.e("codelearn", "Error reading months", e);
        }
        return categoriesRead;
    }
    protected void onPostExecute(List<Category> result) {
        //pass result to main UI thread to show the final data
        activity.renderCategories(result);
    }
}

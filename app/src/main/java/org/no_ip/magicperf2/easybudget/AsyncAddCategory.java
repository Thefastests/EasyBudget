package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Category;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 6/22/15.
 */
public class AsyncAddCategory extends AsyncTask<List<Category>,Void,List<Category>> {
    private List<Category> categoriesRead;
    private static final String MONTHS_CACHE_FILE = "month_cache.ser";
    private CategoryAddActivity activity;
    private Category categoryToAdd;

    public AsyncAddCategory(CategoryAddActivity act,Category category){
        activity = act;
        categoryToAdd=category;
    }

    @Override
    protected List<Category> doInBackground(List<Category>... params) {
        categoriesRead = params[0];
        try {
            //Thread.sleep(5000);
            FileInputStream fis = activity.openFileInput(MONTHS_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            categoriesRead = (List<Category>) ois.readObject();
            if(categoriesRead==null) categoriesRead = new ArrayList<Category>();
            //close file handles
            ois.close();
            fis.close();
        }catch (Exception e){
            //log exceptions (at least)
            Log.e("codelearn", "Error reading months", e);
        }
        categoriesRead.add(categoryToAdd);
        try{
            FileOutputStream fos = activity.openFileOutput(MONTHS_CACHE_FILE, 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(categoriesRead);
            Log.d("codelearn", "Successfully wrote months to the file.");
            oos.close();
            fos.close();
        }catch(Exception e){
            Log.e("codelearn", "Error writing months", e);
        }
        return categoriesRead;
    }

    protected void onPostExecute(List<Category> result) {
        MonthActivity.getMonthInstance().renderCategories(result);
    }
}

package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Category;
import org.no_ip.magicperf2.easybudget.models.Month;

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
    private List<Month> monthsRead;
    private static final String MONTHS_CACHE_FILE = "month_cache.ser";
    private CategoryAddActivity activity;
    private Category categoryToAdd;
    private int monthPos;

    public AsyncAddCategory(CategoryAddActivity act,Category category, int pos){
        activity = act;
        categoryToAdd=category;
        monthPos=pos;
    }

    @Override
    protected List<Category> doInBackground(List<Category>... params) {
        categoriesRead = params[0];
        try {
            //Thread.sleep(5000);
            FileInputStream fis = activity.openFileInput(MONTHS_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            monthsRead = (List<Month>) ois.readObject();
            //categoriesRead = monthsRead.get(monthPos).getCategories();
            //close file handles
            ois.close();
            fis.close();
        }catch (Exception e){
            //log exceptions (at least)
            Log.e("codelearn", "Error reading cat", e);
        }
        monthsRead.get(monthPos).getCategories().add(categoryToAdd);
        categoriesRead=monthsRead.get(monthPos).getCategories();
        try{
            FileOutputStream fos = activity.openFileOutput(MONTHS_CACHE_FILE, 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(monthsRead);
            Log.d("codelearn", "Successfully wrote cat to the file.");
            oos.close();
            fos.close();
        }catch(Exception e){
            Log.e("codelearn", "Error writing cat", e);
        }
        return categoriesRead;
    }

    protected void onPostExecute(List<Category> result) {
        MonthActivity.getMonthInstance().renderCategories(result);
    }
}

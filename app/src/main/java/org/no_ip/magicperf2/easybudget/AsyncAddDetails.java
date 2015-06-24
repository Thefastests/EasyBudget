package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Details;
import org.no_ip.magicperf2.easybudget.models.Month;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by steve on 6/23/15.
 */
public class AsyncAddDetails extends AsyncTask<List<Details>,Void,List<Details>> {
    private DeailsAddActivity activity;
    private int monthPos;
    private int catPos;
    private static final String MONTH_CACHE_FILE = "month_cache.ser";
    private List<Month> monthsRead;
    private List<Details> details;
    private Details detailToAdd;

    public AsyncAddDetails(DeailsAddActivity act, int mPos, int cPos,Details det){
        activity=act;
        monthPos=mPos;
        catPos=cPos;
        detailToAdd=det;
    }

    @Override
    protected List<Details> doInBackground(List<Details>... params) {
        details = params[0];
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
            Log.e("codelearn", "Error reading detail", e);
        }
        monthsRead.get(monthPos).getCategories().get(catPos).getDetails().add(detailToAdd);
        details = monthsRead.get(monthPos).getCategories().get(catPos).getDetails();
        try{
            FileOutputStream fos = activity.openFileOutput(MONTH_CACHE_FILE, 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(monthsRead);
            Log.d("codelearn", "Successfully wrote detail to the file.");
            oos.close();
            fos.close();
        }catch(Exception e){
            Log.e("codelearn", "Error writing months", e);
        }
        return details;
    }

    @Override
    protected void onPostExecute(List<Details>det){
        CategoryActivity.getCategoryInstance().renderDetails(det);
    }
}

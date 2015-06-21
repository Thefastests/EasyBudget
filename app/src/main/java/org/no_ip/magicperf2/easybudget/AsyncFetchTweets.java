package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 6/15/15.
 */
public class AsyncFetchTweets extends AsyncTask<List<Tweet>,Void,List<Tweet>> {

    private List<Tweet> tweetsRead;
    private static final String TWEETS_CACHE_FILE = "tweet_cache.ser";
    private TweetListActivity activity;

    public AsyncFetchTweets(TweetListActivity act){
        activity = act;
    }

    @Override
    protected List<Tweet> doInBackground(List<Tweet>... params) {
        tweetsRead = params[0];
        try {
            //Thread.sleep(5000);
            FileInputStream fis = activity.openFileInput(TWEETS_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tweetsRead = (List<Tweet>) ois.readObject();
            //close file handles
            ois.close();
            fis.close();
        }catch (Exception e){
            //log exceptions (at least)
            Log.e("codelearn", "Error reading tweets", e);
        }
        return tweetsRead;
    }
    protected void onPostExecute(List<Tweet> result) {
        //pass result to main UI thread to show the final data
        activity.renderTweets(result);
    }
}

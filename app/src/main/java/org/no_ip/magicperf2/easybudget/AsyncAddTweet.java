package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by steve on 6/21/15.
 */
public class AsyncAddTweet extends AsyncTask<List<Tweet>,Void,List<Tweet>> {
    private List<Tweet> tweetsRead;
    private static final String TWEETS_CACHE_FILE = "tweet_cache.ser";
    private TweetAddActivity activity;
    private Tweet tweetToAdd;

    public AsyncAddTweet(TweetAddActivity act,Tweet t){
        activity = act;
        tweetToAdd=t;
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
        tweetsRead.add(tweetToAdd);
        try{
            FileOutputStream fos = activity.openFileOutput(TWEETS_CACHE_FILE, 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tweetsRead);
            Log.d("codelearn", "Successfully wrote tweets to the file.");
            oos.close();
            fos.close();
        }catch(Exception e){
            Log.e("codelearn", "Error writing tweets", e);
        }
        return tweetsRead;//null;
    }

    protected void onPostExecute(List<Tweet> result) {
        //pass result to main UI thread to show the final data
        //activity.renderTweets(result);
        TweetListActivity.getInstance().renderTweets(result);
    }
}

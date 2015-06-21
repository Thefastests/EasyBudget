package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by steve on 6/20/15.
 */
public class AsynchWriteTweets extends AsyncTask<List<Tweet>,Void,Void> {
    private List<Tweet> tweetsWrite;
    private static final String TWEETS_CACHE_FILE = "tweet_cache.ser";
    private TweetListActivity activity;

    public AsynchWriteTweets(TweetListActivity act){
        activity = act;
    }

    @Override
    protected Void doInBackground(List<Tweet>... params) {
        tweetsWrite = params[0];
        try{
            for ( int i = 0; i < 20; i++ ) {
                Tweet tweet = new Tweet();
                tweet.setTitle("A nice header for Tweet # " +i);
                tweet.setBody("Some random body text for the tweet # " +i);
                tweetsWrite.add(tweet);
            }
        }catch(Exception e){

        }
        try{
            FileOutputStream fos = activity.openFileOutput(TWEETS_CACHE_FILE, 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tweetsWrite);
            Log.d("codelearn", "Successfully wrote tweets to the file.");
            oos.close();
            fos.close();
        }catch(Exception e){
            Log.e("codelearn", "Error writing tweets", e);
        }
        return null;
    }
}

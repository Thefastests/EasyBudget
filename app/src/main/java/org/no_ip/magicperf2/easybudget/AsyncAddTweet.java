package org.no_ip.magicperf2.easybudget;

import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by steve on 6/21/15.
 */
public class AsyncAddTweet extends AsyncTask<List<Tweet>,Void,Void> {
    private List<Tweet> tweetsWrite;
    private static final String TWEETS_CACHE_FILE = "tweet_cache.ser";
    private TweetListActivity activity;

    public AsyncAddTweet(TweetListActivity act){
        activity = act;
    }

    @Override
    protected Void doInBackground(List<Tweet>... params) {
        tweetsWrite = params[0];
        Tweet tweet = new Tweet();
        tweet.setTitle("Random Tweet"+tweetsWrite.size()+1);
        tweet.setBody("Set Body");
        tweet.setDate("TOday");
        tweetsWrite.add(tweet);
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

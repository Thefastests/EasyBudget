package org.no_ip.magicperf2.easybudget;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 6/15/15.
 */
public class AsyncFetchTweets extends AsyncTask<List<Tweet>,Void,Void> {

    private List<Tweet> tweetsWrite;
    private static final String TWEETS_CACHE_FILE = "tweet_cache.ser";
    private Context context;

    public AsyncFetchTweets(Context con){
        context = con;
    }

    @Override
    protected Void doInBackground(List<Tweet>... params) {
        tweetsWrite = params[0];

        try{
            Thread.sleep(5000);
            for ( int i = 0; i < 20; i++ ) {
                Tweet tweet = new Tweet();
                tweet.setTitle("A nice header for Tweet # " +i);
                tweet.setBody("Some random body text for the tweet # " +i);
                tweetsWrite.add(tweet);
            }
        }catch(Exception e){

        }
        try{
            FileOutputStream fos = context.openFileOutput(TWEETS_CACHE_FILE, 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tweetsWrite);
            Log.d("codelearn", "Successfully wrote tweets to the file.");
            oos.close();
            fos.close();
        }catch(Exception e){
            Log.e("codelearn", "Error writing tweets", e);
        }
        return null;//tweetsWrite;
    }
}

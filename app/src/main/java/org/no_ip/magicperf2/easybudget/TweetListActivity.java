package org.no_ip.magicperf2.easybudget;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class TweetListActivity extends ListActivity {
    private ArrayAdapter tweetItemArrayAdapter;
    private List<Tweet> tweetsRead;
    private List<Tweet> tweetsWrite;
    private static final String TWEETS_CACHE_FILE = "tweet_cache.ser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);

        try {
            FileInputStream fis = openFileInput(TWEETS_CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tweetsRead = (List<Tweet>) ois.readObject();
            //close file handles
            ois.close();
            fis.close();
        }catch (Exception e){
            //log exceptions (at least)
            Log.e("codelearn", "Error reading tweets", e);
        }

        tweetsWrite = new ArrayList<Tweet>();
        new AsyncFetchTweets(this).execute(tweetsWrite);

        tweetItemArrayAdapter = new TweetAdapter(this, tweetsRead);
        setListAdapter(tweetItemArrayAdapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, TweetDetailActivity.class);
        startActivity(intent);
    }
}

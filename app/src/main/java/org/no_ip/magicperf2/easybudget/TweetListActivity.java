package org.no_ip.magicperf2.easybudget;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TweetListActivity extends ListActivity {
    private ArrayAdapter tweetItemArrayAdapter;
    private List<Tweet> tweets;
    private static final String TWEETS_CACHE_FILE = "tweet_cache.ser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);

        tweets = new ArrayList<Tweet>();
        for ( int i = 0; i < 20; i++ ) {
            Tweet tweet = new Tweet();
            tweet.setTitle("A nice header for Tweet # " +i);
            tweet.setBody("Some random body text for the tweet # " +i);
            tweets.add(tweet);
        }

        try{
            FileOutputStream fos = openFileOutput(TWEETS_CACHE_FILE, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tweets);
            Log.d("codelearn", "Successfully wrote tweets to the file.");
            oos.close();
            fos.close();
        }catch(Exception e){
            Log.e("codelearn", "Error writing tweets", e);
        }

        tweetItemArrayAdapter = new TweetAdapter(this, tweets);
        setListAdapter(tweetItemArrayAdapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, TweetDetailActivity.class);
        startActivity(intent);
    }
}

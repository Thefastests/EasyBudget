package org.no_ip.magicperf2.easybudget;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetListActivity extends ListActivity {
    private ArrayAdapter tweetItemArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);

        List<Tweet> tweets = new ArrayList<Tweet>();
        for ( int i = 0; i < 20; i++ ) {
            Tweet tweet = new Tweet();
            tweet.setTitle("A nice header for Tweet # " +i);
            tweet.setBody("Some random body text for the tweet # " +i);
            tweets.add(tweet);
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

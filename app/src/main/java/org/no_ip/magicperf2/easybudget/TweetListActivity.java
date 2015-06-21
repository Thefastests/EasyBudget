package org.no_ip.magicperf2.easybudget;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import org.no_ip.magicperf2.easybudget.models.Tweet;
import java.util.ArrayList;
import java.util.List;

public class TweetListActivity extends ListActivity {
    private ArrayAdapter tweetItemArrayAdapter;
    private List<Tweet> tweetsRead;
    private List<Tweet> tweetsWrite;
    private static final String TWEETS_CACHE_FILE = "tweet_cache.ser";
    private Button addTweet;

    public void renderTweets(List<Tweet> tweets) {
        try{
            tweetItemArrayAdapter = new TweetAdapter(this, tweets);
            setListAdapter(tweetItemArrayAdapter);
        }catch(Exception e){
            Log.d("codelearn","Cannot show tweets");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);

        tweetsWrite = new ArrayList<Tweet>();
        tweetsRead = new ArrayList<Tweet>();

        new AsynchWriteTweets(this).execute(tweetsWrite);
        final TweetListActivity tweetListActivity = this;

        addTweet = (Button) findViewById(R.id.addTweetButton);
        addTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncAddTweet(tweetListActivity).execute(tweetsWrite);
                new AsyncFetchTweets(tweetListActivity).execute(tweetsRead);
            }
        });

        new AsyncFetchTweets(this).execute(tweetsRead);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Tweet tweet = (Tweet) getListAdapter().getItem(position);
        Intent intent = new Intent(this, TweetDetailActivity.class);
        intent.putExtra("tweet",tweet);
        startActivity(intent);
    }
}

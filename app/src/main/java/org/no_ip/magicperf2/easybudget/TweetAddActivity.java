package org.no_ip.magicperf2.easybudget;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.util.ArrayList;
import java.util.List;


public class TweetAddActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_add);

        Button addTweet = (Button) findViewById(R.id.addNewTweet);
        addTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tittle = (TextView) findViewById(R.id.tweetNewTitle);
                TextView body = (TextView) findViewById(R.id.tweetNewBody);
                TextView date = (TextView) findViewById(R.id.tweetNewDate);
                Tweet tweet = new Tweet();
                tweet.setTitle(tittle.getText().toString());
                tweet.setBody(body.getText().toString());
                tweet.setDate(date.getText().toString());
                List<Tweet> tweets = new ArrayList<Tweet>();
                new AsyncAddTweet(TweetAddActivity.this,tweet).execute(tweets);
                //new TweetListActivity().renderTweets();
                finish();
                Log.d("learn",tweet.getTitle()+" "+tweet.getBody());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tweet_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

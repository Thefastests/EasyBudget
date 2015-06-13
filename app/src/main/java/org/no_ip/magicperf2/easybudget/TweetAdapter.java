package org.no_ip.magicperf2.easybudget;

/**
 * Created by steve on 6/13/15.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.no_ip.magicperf2.easybudget.models.Tweet;

import java.util.List;

public class TweetAdapter extends ArrayAdapter<Tweet>{

    private LayoutInflater inflater;
    List<Tweet> tweets;

    public TweetAdapter(Activity activity, List<Tweet> items){
        super(activity, R.layout.row_tweet, items);
        tweets=items;
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = inflater.inflate(R.layout.row_tweet, parent, false);
        View viewById = view.findViewById(R.id.tweetTitle);
        return view;
    }

}
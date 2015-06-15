package org.no_ip.magicperf2.easybudget.models;

import java.io.Serializable;

/**
 * Created by steve on 6/13/15.
 */
public class Tweet implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String body;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

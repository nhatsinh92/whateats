package com.example.sinh.whateats.events;

public class KeywordSubmitEvent {

    private String mQuery;

    public KeywordSubmitEvent(String query) {
        this.mQuery = query;
    }

    public String getmQuery() {
        return mQuery;
    }
}
package com.example.sinh.whateats.events;

public class KeywordSubmitEvent {

    private String mQuery;
    private String mLocation;

    public KeywordSubmitEvent(String query, String location) {
        this.mQuery = query;
        this.mLocation = location;
    }

    public String getmQuery() {
        return mQuery;
    }

    public String getmLocation() {
        return mLocation;
    }
}
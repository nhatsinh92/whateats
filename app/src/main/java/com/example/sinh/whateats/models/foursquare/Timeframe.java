
package com.example.sinh.whateats.models.foursquare;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timeframe {

    @SerializedName("days")
    @Expose
    private String days;
    @SerializedName("includesToday")
    @Expose
    private Boolean includesToday;
    @SerializedName("open")
    @Expose
    private List<Open> open = new ArrayList<Open>();
    @SerializedName("segments")
    @Expose
    private List<Object> segments = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The days
     */
    public String getDays() {
        return days;
    }

    /**
     * 
     * @param days
     *     The days
     */
    public void setDays(String days) {
        this.days = days;
    }

    /**
     * 
     * @return
     *     The includesToday
     */
    public Boolean getIncludesToday() {
        return includesToday;
    }

    /**
     * 
     * @param includesToday
     *     The includesToday
     */
    public void setIncludesToday(Boolean includesToday) {
        this.includesToday = includesToday;
    }

    /**
     * 
     * @return
     *     The open
     */
    public List<Open> getOpen() {
        return open;
    }

    /**
     * 
     * @param open
     *     The open
     */
    public void setOpen(List<Open> open) {
        this.open = open;
    }

    /**
     * 
     * @return
     *     The segments
     */
    public List<Object> getSegments() {
        return segments;
    }

    /**
     * 
     * @param segments
     *     The segments
     */
    public void setSegments(List<Object> segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        String openTime = getDays() + " ";
        for (Open o: open) {
             openTime += o.getRenderedTime() + "\n";
        }
        return openTime;
    }
}

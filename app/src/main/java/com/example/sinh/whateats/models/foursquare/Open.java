
package com.example.sinh.whateats.models.foursquare;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Open {

    @SerializedName("renderedTime")
    @Expose
    private String renderedTime;

    /**
     * 
     * @return
     *     The renderedTime
     */
    public String getRenderedTime() {
        return renderedTime;
    }

    /**
     * 
     * @param renderedTime
     *     The renderedTime
     */
    public void setRenderedTime(String renderedTime) {
        this.renderedTime = renderedTime;
    }

}

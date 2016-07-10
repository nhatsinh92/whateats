
package com.example.sinh.whateats.models.foursquare;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Todo {

    @SerializedName("count")
    @Expose
    private Integer count;

    /**
     * 
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

}

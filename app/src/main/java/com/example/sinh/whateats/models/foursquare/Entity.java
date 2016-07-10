
package com.example.sinh.whateats.models.foursquare;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entity {

    @SerializedName("indices")
    @Expose
    private List<Integer> indices = new ArrayList<Integer>();
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * 
     * @return
     *     The indices
     */
    public List<Integer> getIndices() {
        return indices;
    }

    /**
     * 
     * @param indices
     *     The indices
     */
    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

}

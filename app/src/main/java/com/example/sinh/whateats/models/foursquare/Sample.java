
package com.example.sinh.whateats.models.foursquare;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sample {

    @SerializedName("entities")
    @Expose
    private List<Entity> entities = new ArrayList<Entity>();
    @SerializedName("text")
    @Expose
    private String text;

    /**
     * 
     * @return
     *     The entities
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * 
     * @param entities
     *     The entities
     */
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

}

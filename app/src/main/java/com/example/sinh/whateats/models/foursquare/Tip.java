
package com.example.sinh.whateats.models.foursquare;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tip {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private Integer createdAt;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("canonicalUrl")
    @Expose
    private String canonicalUrl;
    @SerializedName("logView")
    @Expose
    private Boolean logView;
    @SerializedName("agreeCount")
    @Expose
    private Integer agreeCount;
    @SerializedName("disagreeCount")
    @Expose
    private Integer disagreeCount;
    @SerializedName("todo")
    @Expose
    private Todo todo;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public Integer getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The createdAt
     */
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
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

    /**
     * 
     * @return
     *     The canonicalUrl
     */
    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    /**
     * 
     * @param canonicalUrl
     *     The canonicalUrl
     */
    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    /**
     * 
     * @return
     *     The logView
     */
    public Boolean getLogView() {
        return logView;
    }

    /**
     * 
     * @param logView
     *     The logView
     */
    public void setLogView(Boolean logView) {
        this.logView = logView;
    }

    /**
     * 
     * @return
     *     The agreeCount
     */
    public Integer getAgreeCount() {
        return agreeCount;
    }

    /**
     * 
     * @param agreeCount
     *     The agreeCount
     */
    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    /**
     * 
     * @return
     *     The disagreeCount
     */
    public Integer getDisagreeCount() {
        return disagreeCount;
    }

    /**
     * 
     * @param disagreeCount
     *     The disagreeCount
     */
    public void setDisagreeCount(Integer disagreeCount) {
        this.disagreeCount = disagreeCount;
    }

    /**
     * 
     * @return
     *     The todo
     */
    public Todo getTodo() {
        return todo;
    }

    /**
     * 
     * @param todo
     *     The todo
     */
    public void setTodo(Todo todo) {
        this.todo = todo;
    }

}

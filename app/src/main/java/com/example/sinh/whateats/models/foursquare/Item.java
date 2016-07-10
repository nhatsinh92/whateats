
package com.example.sinh.whateats.models.foursquare;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private Integer createdAt;
    @SerializedName("source")
    @Expose
    private Source source;
    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("suffix")
    @Expose
    private String suffix;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("tip")
    @Expose
    private Tip tip;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("displayValue")
    @Expose
    private String displayValue;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("reasonName")
    @Expose
    private String reasonName;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("canonicalUrl")
    @Expose
    private String canonicalUrl;
    @SerializedName("photo")
    @Expose
    private Photo photo;
    @SerializedName("photourl")
    @Expose
    private String photourl;
    @SerializedName("likes")
    @Expose
    private Likes likes;
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
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("editable")
    @Expose
    private Boolean editable;
    @SerializedName("public")
    @Expose
    private Boolean _public;
    @SerializedName("collaborative")
    @Expose
    private Boolean collaborative;
    @SerializedName("followers")
    @Expose
    private Followers followers;
    @SerializedName("listItems")
    @Expose
    private ListItems listItems;

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
     *     The source
     */
    public Source getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(Source source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * 
     * @param prefix
     *     The prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 
     * @return
     *     The suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 
     * @param suffix
     *     The suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * 
     * @return
     *     The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The tip
     */
    public Tip getTip() {
        return tip;
    }

    /**
     * 
     * @param tip
     *     The tip
     */
    public void setTip(Tip tip) {
        this.tip = tip;
    }

    /**
     * 
     * @return
     *     The visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * 
     * @param visibility
     *     The visibility
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     *
     * @return
     *     The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     *     The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     *     The displayValue
     */
    public String getDisplayValue() {
        return displayValue;
    }

    /**
     *
     * @param displayValue
     *     The displayValue
     */
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    /**
     *
     * @return
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     *
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return
     *     The reasonName
     */
    public String getReasonName() {
        return reasonName;
    }

    /**
     *
     * @param reasonName
     *     The reasonName
     */
    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
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
     *     The photo
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     *     The photo
     */
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     *
     * @return
     *     The photourl
     */
    public String getPhotourl() {
        return photourl;
    }

    /**
     *
     * @param photourl
     *     The photourl
     */
    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    /**
     *
     * @return
     *     The likes
     */
    public Likes getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     *     The likes
     */
    public void setLikes(Likes likes) {
        this.likes = likes;
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

    /**
     *
     * @return
     *     The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     *     The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     *     The lang
     */
    public String getLang() {
        return lang;
    }

    /**
     *
     * @param lang
     *     The lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The editable
     */
    public Boolean getEditable() {
        return editable;
    }

    /**
     *
     * @param editable
     *     The editable
     */
    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    /**
     *
     * @return
     *     The _public
     */
    public Boolean getPublic() {
        return _public;
    }

    /**
     *
     * @param _public
     *     The public
     */
    public void setPublic(Boolean _public) {
        this._public = _public;
    }

    /**
     *
     * @return
     *     The collaborative
     */
    public Boolean getCollaborative() {
        return collaborative;
    }

    /**
     *
     * @param collaborative
     *     The collaborative
     */
    public void setCollaborative(Boolean collaborative) {
        this.collaborative = collaborative;
    }

    /**
     *
     * @return
     *     The followers
     */
    public Followers getFollowers() {
        return followers;
    }

    /**
     *
     * @param followers
     *     The followers
     */
    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    /**
     *
     * @return
     *     The listItems
     */
    public ListItems getListItems() {
        return listItems;
    }

    /**
     *
     * @param listItems
     *     The listItems
     */
    public void setListItems(ListItems listItems) {
        this.listItems = listItems;
    }

}

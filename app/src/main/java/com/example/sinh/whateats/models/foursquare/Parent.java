
package com.example.sinh.whateats.models.foursquare;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parent {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = new ArrayList<Category>();
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("allowMenuUrlEdit")
    @Expose
    private Boolean allowMenuUrlEdit;
    @SerializedName("venuePage")
    @Expose
    private VenuePage venuePage;
    @SerializedName("storeId")
    @Expose
    private String storeId;

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
     *     The contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * 
     * @param contact
     *     The contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * 
     * @return
     *     The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The verified
     */
    public Boolean getVerified() {
        return verified;
    }

    /**
     * 
     * @param verified
     *     The verified
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    /**
     * 
     * @return
     *     The stats
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * 
     * @param stats
     *     The stats
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The allowMenuUrlEdit
     */
    public Boolean getAllowMenuUrlEdit() {
        return allowMenuUrlEdit;
    }

    /**
     * 
     * @param allowMenuUrlEdit
     *     The allowMenuUrlEdit
     */
    public void setAllowMenuUrlEdit(Boolean allowMenuUrlEdit) {
        this.allowMenuUrlEdit = allowMenuUrlEdit;
    }

    /**
     * 
     * @return
     *     The venuePage
     */
    public VenuePage getVenuePage() {
        return venuePage;
    }

    /**
     * 
     * @param venuePage
     *     The venuePage
     */
    public void setVenuePage(VenuePage venuePage) {
        this.venuePage = venuePage;
    }

    /**
     * 
     * @return
     *     The storeId
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * 
     * @param storeId
     *     The storeId
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

}

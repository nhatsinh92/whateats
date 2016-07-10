
package com.example.sinh.whateats.models.foursquare;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Venue implements Parcelable {

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
    @SerializedName("delivery")
    @Expose
    private Delivery delivery;
    @SerializedName("allowMenuUrlEdit")
    @Expose
    private Boolean allowMenuUrlEdit;
    @SerializedName("specials")
    @Expose
    private Specials specials;
    @SerializedName("hereNow")
    @Expose
    private HereNow hereNow;
    @SerializedName("referralId")
    @Expose
    private String referralId;
    @SerializedName("hasMenu")
    @Expose
    private Boolean hasMenu;
    @SerializedName("menu")
    @Expose
    private Menu menu;
    @SerializedName("venuePage")
    @Expose
    private VenuePage venuePage;
    @SerializedName("canonicalUrl")
    @Expose
    private String canonicalUrl;
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("dislike")
    @Expose
    private Boolean dislike;
    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("ratingSignals")
    @Expose
    private Integer ratingSignals;
    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("reasons")
    @Expose
    private Reasons reasons;
    @SerializedName("createdAt")
    @Expose
    private Integer createdAt;
    @SerializedName("tips")
    @Expose
    private Tips tips;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = new ArrayList<Object>();
    @SerializedName("shortUrl")
    @Expose
    private String shortUrl;
    @SerializedName("timeZone")
    @Expose
    private String timeZone;
    @SerializedName("listed")
    @Expose
    private Listed listed;
    @SerializedName("phrases")
    @Expose
    private List<Phrase> phrases = new ArrayList<Phrase>();
    @SerializedName("popular")
    @Expose
    private Popular popular;
    @SerializedName("pageUpdates")
    @Expose
    private PageUpdates pageUpdates;
    @SerializedName("inbox")
    @Expose
    private Inbox inbox;
    @SerializedName("parent")
    @Expose
    private Parent parent;
    @SerializedName("hierarchy")
    @Expose
    private List<Hierarchy> hierarchy = new ArrayList<Hierarchy>();
    @SerializedName("venueChains")
    @Expose
    private List<Object> venueChains = new ArrayList<Object>();
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("bestPhoto")
    @Expose
    private BestPhoto bestPhoto;

    protected Venue(Parcel in) {
        id = in.readString();
        name = in.readString();
        url = in.readString();
        referralId = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<Venue> CREATOR = new Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };

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
     *     The delivery
     */
    public Delivery getDelivery() {
        return delivery;
    }

    /**
     * 
     * @param delivery
     *     The delivery
     */
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
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
     *     The specials
     */
    public Specials getSpecials() {
        return specials;
    }

    /**
     * 
     * @param specials
     *     The specials
     */
    public void setSpecials(Specials specials) {
        this.specials = specials;
    }

    /**
     * 
     * @return
     *     The hereNow
     */
    public HereNow getHereNow() {
        return hereNow;
    }

    /**
     * 
     * @param hereNow
     *     The hereNow
     */
    public void setHereNow(HereNow hereNow) {
        this.hereNow = hereNow;
    }

    /**
     * 
     * @return
     *     The referralId
     */
    public String getReferralId() {
        return referralId;
    }

    /**
     * 
     * @param referralId
     *     The referralId
     */
    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    /**
     * 
     * @return
     *     The venueChains
     */
    public List<Object> getVenueChains() {
        return venueChains;
    }

    /**
     * 
     * @param venueChains
     *     The venueChains
     */
    public void setVenueChains(List<Object> venueChains) {
        this.venueChains = venueChains;
    }

    /**
     * 
     * @return
     *     The hasMenu
     */
    public Boolean getHasMenu() {
        return hasMenu;
    }

    /**
     * 
     * @param hasMenu
     *     The hasMenu
     */
    public void setHasMenu(Boolean hasMenu) {
        this.hasMenu = hasMenu;
    }

    /**
     * 
     * @return
     *     The menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * 
     * @param menu
     *     The menu
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
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
     *     The dislike
     */
    public Boolean getDislike() {
        return dislike;
    }

    /**
     *
     * @param dislike
     *     The dislike
     */
    public void setDislike(Boolean dislike) {
        this.dislike = dislike;
    }

    /**
     *
     * @return
     *     The ok
     */
    public Boolean getOk() {
        return ok;
    }

    /**
     *
     * @param ok
     *     The ok
     */
    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    /**
     *
     * @return
     *     The rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     *     The rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     *     The ratingSignals
     */
    public Integer getRatingSignals() {
        return ratingSignals;
    }

    /**
     *
     * @param ratingSignals
     *     The ratingSignals
     */
    public void setRatingSignals(Integer ratingSignals) {
        this.ratingSignals = ratingSignals;
    }

    /**
     *
     * @return
     *     The photos
     */
    public Photos getPhotos() {
        return photos;
    }

    /**
     *
     * @param photos
     *     The photos
     */
    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    /**
     *
     * @return
     *     The reasons
     */
    public Reasons getReasons() {
        return reasons;
    }

    /**
     *
     * @param reasons
     *     The reasons
     */
    public void setReasons(Reasons reasons) {
        this.reasons = reasons;
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
     *     The tips
     */
    public Tips getTips() {
        return tips;
    }

    /**
     *
     * @param tips
     *     The tips
     */
    public void setTips(Tips tips) {
        this.tips = tips;
    }

    /**
     *
     * @return
     *     The tags
     */
    public List<Object> getTags() {
        return tags;
    }

    /**
     *
     * @param tags
     *     The tags
     */
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    /**
     *
     * @return
     *     The shortUrl
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     *
     * @param shortUrl
     *     The shortUrl
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    /**
     *
     * @return
     *     The timeZone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     *
     * @param timeZone
     *     The timeZone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     *
     * @return
     *     The listed
     */
    public Listed getListed() {
        return listed;
    }

    /**
     *
     * @param listed
     *     The listed
     */
    public void setListed(Listed listed) {
        this.listed = listed;
    }

    /**
     *
     * @return
     *     The phrases
     */
    public List<Phrase> getPhrases() {
        return phrases;
    }

    /**
     *
     * @param phrases
     *     The phrases
     */
    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }

    /**
     *
     * @return
     *     The popular
     */
    public Popular getPopular() {
        return popular;
    }

    /**
     *
     * @param popular
     *     The popular
     */
    public void setPopular(Popular popular) {
        this.popular = popular;
    }

    /**
     *
     * @return
     *     The pageUpdates
     */
    public PageUpdates getPageUpdates() {
        return pageUpdates;
    }

    /**
     *
     * @param pageUpdates
     *     The pageUpdates
     */
    public void setPageUpdates(PageUpdates pageUpdates) {
        this.pageUpdates = pageUpdates;
    }

    /**
     *
     * @return
     *     The inbox
     */
    public Inbox getInbox() {
        return inbox;
    }

    /**
     *
     * @param inbox
     *     The inbox
     */
    public void setInbox(Inbox inbox) {
        this.inbox = inbox;
    }

    /**
     *
     * @return
     *     The parent
     */
    public Parent getParent() {
        return parent;
    }

    /**
     *
     * @param parent
     *     The parent
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     *     The hierarchy
     */
    public List<Hierarchy> getHierarchy() {
        return hierarchy;
    }

    /**
     *
     * @param hierarchy
     *     The hierarchy
     */
    public void setHierarchy(List<Hierarchy> hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     *
     * @return
     *     The attributes
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    /**
     *
     * @return
     *     The bestPhoto
     */
    public BestPhoto getBestPhoto() {
        return bestPhoto;
    }

    /**
     *
     * @param bestPhoto
     *     The bestPhoto
     */
    public void setBestPhoto(BestPhoto bestPhoto) {
        this.bestPhoto = bestPhoto;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(url);
        parcel.writeString(referralId);
        parcel.writeParcelable(location, i);
    }
}

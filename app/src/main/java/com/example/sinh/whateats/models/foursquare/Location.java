
package com.example.sinh.whateats.models.foursquare;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Location implements Parcelable {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("crossStreet")
    @Expose
    private String crossStreet;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("cc")
    @Expose
    private String cc;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("formattedAddress")
    @Expose
    private List<String> formattedAddress = new ArrayList<String>();

    protected Location(Parcel in) {
        address = in.readString();
        crossStreet = in.readString();
        postalCode = in.readString();
        cc = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();
        formattedAddress = in.createStringArrayList();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    /**
     * 
     * @return
     *     The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The crossStreet
     */
    public String getCrossStreet() {
        return crossStreet;
    }

    /**
     * 
     * @param crossStreet
     *     The crossStreet
     */
    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    /**
     * 
     * @return
     *     The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * 
     * @return
     *     The lng
     */
    public Double getLng() {
        return lng;
    }

    /**
     * 
     * @param lng
     *     The lng
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * 
     * @return
     *     The distance
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     * 
     * @return
     *     The postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 
     * @param postalCode
     *     The postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 
     * @return
     *     The cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * 
     * @param cc
     *     The cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The formattedAddress
     */
    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * 
     * @param formattedAddress
     *     The formattedAddress
     */
    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeString(crossStreet);
        parcel.writeString(postalCode);
        parcel.writeString(cc);
        parcel.writeString(city);
        parcel.writeString(state);
        parcel.writeString(country);
        parcel.writeStringList(formattedAddress);
    }
}

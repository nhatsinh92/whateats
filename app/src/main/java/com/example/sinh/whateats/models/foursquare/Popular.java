
package com.example.sinh.whateats.models.foursquare;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Popular {

    @SerializedName("isOpen")
    @Expose
    private Boolean isOpen;
    @SerializedName("isLocalHoliday")
    @Expose
    private Boolean isLocalHoliday;
    @SerializedName("timeframes")
    @Expose
    private List<Timeframe> timeframes = new ArrayList<Timeframe>();

    @SerializedName("status")
    @Expose
    private String status;

    /**
     *
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The isOpen
     */
    public Boolean getIsOpen() {
        return isOpen;
    }

    /**
     * 
     * @param isOpen
     *     The isOpen
     */
    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * 
     * @return
     *     The isLocalHoliday
     */
    public Boolean getIsLocalHoliday() {
        return isLocalHoliday;
    }

    /**
     * 
     * @param isLocalHoliday
     *     The isLocalHoliday
     */
    public void setIsLocalHoliday(Boolean isLocalHoliday) {
        this.isLocalHoliday = isLocalHoliday;
    }

    /**
     * 
     * @return
     *     The timeframes
     */
    public List<Timeframe> getTimeframes() {
        return timeframes;
    }

    /**
     * 
     * @param timeframes
     *     The timeframes
     */
    public void setTimeframes(List<Timeframe> timeframes) {
        this.timeframes = timeframes;
    }

}

package com.example.sinh.whateats.network;

import com.example.sinh.whateats.models.foursquare.FoursquareResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kimhieu on 6/28/16.
 */

public interface FoursquareApi {
    @GET("venues/search")
    Call<FoursquareResponse> searchVenue(@Query("v") String ver, @Query("ll") String longLat, @Query("query") String query);
}

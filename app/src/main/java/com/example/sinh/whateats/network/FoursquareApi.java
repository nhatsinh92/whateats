package com.example.sinh.whateats.network;

import com.example.sinh.whateats.models.foursquare.FoursquareResponse;
import com.example.sinh.whateats.models.foursquare.PhotosResponse;
import com.example.sinh.whateats.models.foursquare.VenueResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kimhieu on 6/28/16.
 */

public interface FoursquareApi {
    @GET("venues/search")
    Call<FoursquareResponse> searchVenue(@Query("ll") String longLat, @Query("query") String query);

    @GET("venues/{id}/photos")
    Call<PhotosResponse> getPhotos(@Path("id") String venueId);

    @GET("venues/{id}")
    Call<VenueResponse> getVenue(@Path("id") String venueId);
}

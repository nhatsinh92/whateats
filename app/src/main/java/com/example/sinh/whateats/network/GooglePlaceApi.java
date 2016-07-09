package com.example.sinh.whateats.network;

import com.example.sinh.whateats.models.googleplace.GooglePlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sinh on 7/7/2016.
 */
public interface GooglePlaceApi {
    @GET("nearbysearch/json")
    Call<GooglePlaceResponse> searchNearbyPlace(
            @Query("location") String location,
            @Query("keyword") String keyword,
            @Query("rankby") String rankBy
    );
}

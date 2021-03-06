package com.example.submission3_movieapp_rest_api.networks.tvshow;

import com.example.submission3_movieapp_rest_api.models.tvshow.TvShowAiringTodayResponse;
import com.example.submission3_movieapp_rest_api.models.tvshow.TvShowModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvApiInterface {
    @GET("airing_today")
    Call<TvShowAiringTodayResponse> getAiringToday(
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}")
    Call<TvShowModel> getTvShow(
            @Path("tv_id") String id,
            @Query("api_key") String apiKey
    );
}

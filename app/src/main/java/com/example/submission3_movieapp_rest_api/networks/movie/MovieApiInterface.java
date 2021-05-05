package com.example.submission3_movieapp_rest_api.networks.movie;

import com.example.submission3_movieapp_rest_api.models.movie.MovieModel;
import com.example.submission3_movieapp_rest_api.models.movie.MovieNowPlayingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiInterface {
    @GET("now_playing")
    Call<MovieNowPlayingResponse> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}")
    Call<MovieModel> getMovie(
            @Path("movie_id") String id,
            @Query("api_key") String apiKey
    );
}

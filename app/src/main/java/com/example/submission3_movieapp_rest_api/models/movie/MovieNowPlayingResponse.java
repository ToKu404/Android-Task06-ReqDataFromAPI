package com.example.submission3_movieapp_rest_api.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieNowPlayingResponse {
    @SerializedName("results")
    @Expose
    private List<MovieNowPlaying> nowPlayings;

    public List<MovieNowPlaying> getNowPlayings() {
        return nowPlayings;
    }
}

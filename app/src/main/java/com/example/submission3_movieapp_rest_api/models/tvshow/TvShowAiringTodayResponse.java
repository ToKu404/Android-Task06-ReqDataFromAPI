package com.example.submission3_movieapp_rest_api.models.tvshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowAiringTodayResponse {
    @SerializedName("results")
    @Expose
    private List<TvShowAiringToday> airingTodayList;

    public List<TvShowAiringToday> getAiringTodayList() {
        return airingTodayList;
    }
}

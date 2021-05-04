package com.example.submission3_movieapp_rest_api.models.tvshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvAiringTodayResponse {
    @SerializedName("results")
    @Expose
    private List<TvAiringToday> airingTodayList;

    public List<TvAiringToday> getAiringTodayList() {
        return airingTodayList;
    }
}

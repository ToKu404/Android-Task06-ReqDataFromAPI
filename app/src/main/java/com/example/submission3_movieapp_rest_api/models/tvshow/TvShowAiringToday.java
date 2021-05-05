package com.example.submission3_movieapp_rest_api.models.tvshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvShowAiringToday {
    private String id;
    private String overview;
    private String[] releaseYear;

    @SerializedName("name")
    private String title;


    @SerializedName("first_air_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String imgUrl;

    @SerializedName("vote_count")
    private String voteCount;

    @SerializedName("backdrop_path")
    private String backdrop;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseYear() {
        releaseYear = releaseDate.split("-");
        return releaseYear[0];
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }




}

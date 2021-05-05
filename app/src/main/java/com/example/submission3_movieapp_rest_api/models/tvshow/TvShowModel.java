package com.example.submission3_movieapp_rest_api.models.tvshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvShowModel {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("poster_path")
    @Expose
    private String poster;

    @SerializedName("name")
    @Expose
    private String title;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("first_air_date")
    @Expose
    private String releaseDate;

    @SerializedName("vote_count")
    @Expose
    private String voteCount;

    @SerializedName("backdrop_path")
    @Expose
    private String backdrop;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    public String getReleaseYear() {
        String[] releaseYear = releaseDate.split("-");
        return releaseYear[0];
    }
}

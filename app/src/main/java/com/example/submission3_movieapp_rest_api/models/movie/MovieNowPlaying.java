package com.example.submission3_movieapp_rest_api.models.movie;

import com.google.gson.annotations.SerializedName;

public class MovieNowPlaying {
    private String id;
    private String title;
    private String[] relaseYear;
    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String imgUrl;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_average")
    private String voteAverage;


    public String getVoteAverage() {
        return voteAverage;
    }

    public Float getRating() {
        Float rating = ((Float.parseFloat(voteAverage))/10)*5;
        return rating;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public MovieNowPlaying() {
    }

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


    public String getReleaseDate() {
        relaseYear = releaseDate.split("-");
        return relaseYear[0];
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;

    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

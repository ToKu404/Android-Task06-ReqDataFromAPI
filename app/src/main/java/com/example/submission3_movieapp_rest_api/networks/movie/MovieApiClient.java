package com.example.submission3_movieapp_rest_api.networks.movie;

import com.example.submission3_movieapp_rest_api.networks.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiClient {
    private static Retrofit retrofit, retrofitDetail;


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Const.MOVIE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getMovieDetail(){
        if(retrofitDetail==null){
            retrofitDetail = new Retrofit.Builder().baseUrl(Const.BASE_URL_DETAIL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitDetail;
    }
}

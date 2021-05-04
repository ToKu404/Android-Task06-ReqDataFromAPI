package com.example.submission3_movieapp_rest_api.networks.tvshow;

import com.example.submission3_movieapp_rest_api.networks.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvApiClient {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(Const.TVSHOW_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}

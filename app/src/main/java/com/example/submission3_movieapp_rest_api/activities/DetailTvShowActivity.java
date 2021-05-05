package com.example.submission3_movieapp_rest_api.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.models.movie.MovieModel;
import com.example.submission3_movieapp_rest_api.models.tvshow.TvShowModel;
import com.example.submission3_movieapp_rest_api.networks.Const;
import com.example.submission3_movieapp_rest_api.networks.movie.MovieApiClient;
import com.example.submission3_movieapp_rest_api.networks.movie.MovieApiInterface;
import com.example.submission3_movieapp_rest_api.networks.tvshow.TvApiClient;
import com.example.submission3_movieapp_rest_api.networks.tvshow.TvApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTvShowActivity extends AppCompatActivity {
    private TvShowModel tvShowModel;
    ImageView ivPoster, ivBanner;
    TextView tvTitle, tvSinopsis, tvVote, tvYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#191826"));
            getSupportActionBar().setBackgroundDrawable(colorDrawable);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ivPoster = findViewById(R.id.iv_ts_poster);
        ivBanner = findViewById(R.id.iv_ts_banner);
        tvTitle = findViewById(R.id.tv_ts_title);
        tvSinopsis = findViewById(R.id.tv_ts_sinopsis);
        tvVote = findViewById(R.id.tv_ts_vote);
        tvYear = findViewById(R.id.tv_ts_year);

        loadData(getIntent().getStringExtra("ID"));


    }

    private void loadData(String id) {
        TvApiInterface tvApiInterface = TvApiClient.getTvDetail().create(TvApiInterface.class);
        Call<TvShowModel> tvShowCall = tvApiInterface.getTvShow(id, Const.API_KEY);
        tvShowCall.enqueue(new Callback<TvShowModel>() {
            @Override
            public void onResponse(Call<TvShowModel> call, Response<TvShowModel> response) {
                System.out.println("URL :: "+ response.raw().request().url());
                if (response.isSuccessful()&& response.body() != null)
                {
                    tvShowModel = response.body();
                    addValue();
                }
                else
                {
                    Toast.makeText(DetailTvShowActivity.this,"Request Error :: " + response.errorBody(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<TvShowModel> call, Throwable t) {
                Toast.makeText(DetailTvShowActivity.this,"Network Error :: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addValue(){
        tvTitle.setText(tvShowModel.getTitle());
        tvSinopsis.setText(tvShowModel.getOverview());
        tvVote.setText(tvShowModel.getVoteCount());
        tvYear.setText(tvShowModel.getReleaseYear());
        Glide.with(DetailTvShowActivity.this).load(Const.IMG_URL_300+tvShowModel.getPoster()).into(ivPoster);
        Glide.with(DetailTvShowActivity.this).load(Const.IMG_URL_300+tvShowModel.getBackdrop()).into(ivBanner);
    }
}
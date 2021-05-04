package com.example.submission3_movieapp_rest_api.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.models.tvshow.TvShowModel;
import com.example.submission3_movieapp_rest_api.networks.Const;

public class DetailTvShowActivity extends AppCompatActivity {
    private TvShowModel tvShowModel;

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

        ImageView ivPoster = findViewById(R.id.iv_ts_poster);
        ImageView ivBanner = findViewById(R.id.iv_ts_banner);
        TextView tvTitle = findViewById(R.id.tv_ts_title);
        TextView tvSinopsis = findViewById(R.id.tv_ts_sinopsis);
        TextView tvVote = findViewById(R.id.tv_ts_vote);
        TextView tvYear = findViewById(R.id.tv_ts_year);

        tvTitle.setText(getIntent().getStringExtra("TITLE"));
        tvSinopsis.setText(getIntent().getStringExtra("OVERVIEW"));
        tvVote.setText(getIntent().getStringExtra("VOTE"));
        tvYear.setText(getIntent().getStringExtra("YEAR"));
        Glide.with(DetailTvShowActivity.this).load(Const.IMG_URL_300+getIntent().getStringExtra("IMG_URL")).into(ivPoster);
        Glide.with(DetailTvShowActivity.this).load(Const.IMG_URL_300+getIntent().getStringExtra("BACKDROP")).into(ivBanner);
    }
}
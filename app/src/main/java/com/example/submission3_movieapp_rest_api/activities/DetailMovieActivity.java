package com.example.submission3_movieapp_rest_api.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.models.movie.MovieModel;
import com.example.submission3_movieapp_rest_api.networks.Const;

public class DetailMovieActivity extends AppCompatActivity {
    private MovieModel movieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#191826"));
            getSupportActionBar().setBackgroundDrawable(colorDrawable);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ImageView ivPoster = findViewById(R.id.iv_mv_poster);
        TextView tvTitle = findViewById(R.id.tv_mv_title);
        TextView tvSinopsis = findViewById(R.id.tv_mv_sinopsis);
        TextView tvVote = findViewById(R.id.tv_mv_vote);
        TextView tvYear = findViewById(R.id.tv_mv_year);
        RatingBar ratingBar = findViewById(R.id.rb_mv);


        tvTitle.setText(getIntent().getStringExtra("TITLE"));
        tvSinopsis.setText(getIntent().getStringExtra("OVERVIEW"));
        tvVote.setText(getIntent().getStringExtra("VOTE_AVERAGE"));
        tvYear.setText(getIntent().getStringExtra("YEAR"));
        ratingBar.setRating(getIntent().getFloatExtra("RATE",0));
        Glide.with(DetailMovieActivity.this).load(Const.IMG_URL_300+getIntent().getStringExtra("IMG_URL")).into(ivPoster);
    }
}
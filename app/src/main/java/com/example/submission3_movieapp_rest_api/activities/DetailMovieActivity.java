package com.example.submission3_movieapp_rest_api.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.models.movie.MovieModel;
import com.example.submission3_movieapp_rest_api.networks.Const;
import com.example.submission3_movieapp_rest_api.networks.movie.MovieApiClient;
import com.example.submission3_movieapp_rest_api.networks.movie.MovieApiInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity {
    private MovieModel movieModel;
    ImageView ivPoster;
    TextView tvTitle, tvSinopsis, tvVote, tvYear;
    RatingBar ratingBar;
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
        ivPoster = findViewById(R.id.iv_mv_poster);
        tvTitle = findViewById(R.id.tv_mv_title);
        tvSinopsis = findViewById(R.id.tv_mv_sinopsis);
        tvVote = findViewById(R.id.tv_mv_vote);
        tvYear = findViewById(R.id.tv_mv_year);
        ratingBar = findViewById(R.id.rb_mv);
        loadData(getIntent().getStringExtra("ID"));

    }

    private void loadData(String id) {
        System.out.println("ID : "+id);
        MovieApiInterface movieApiInterface = MovieApiClient.getMovieDetail().create(MovieApiInterface.class);
        Call<MovieModel> movieCall = movieApiInterface.getMovie(id, Const.API_KEY);
        movieCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                System.out.println("URL :: "+ response.raw().request().url());
                if (response.isSuccessful()&& response.body() != null)
                {
                    movieModel = response.body();
                    addValue();
                }
                else
                {
                    Toast.makeText(DetailMovieActivity.this,"Request Error :: " + response.errorBody(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toast.makeText(DetailMovieActivity.this,"Network Error :: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addValue() {
        tvTitle.setText(movieModel.getTitle());
        tvSinopsis.setText(movieModel.getOverview());
        tvVote.setText(movieModel.getVoteAverage());
        tvYear.setText(movieModel.getYear());
        ratingBar.setRating(movieModel.getRating());
        Glide.with(DetailMovieActivity.this).load(Const.IMG_URL_300+movieModel.getPoster()).into(ivPoster);
    }
}
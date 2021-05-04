package com.example.submission3_movieapp_rest_api.fragments;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.activities.DetailMovieActivity;
import com.example.submission3_movieapp_rest_api.adapters.MovieAdapter;
import com.example.submission3_movieapp_rest_api.models.movie.MovieNowPlaying;
import com.example.submission3_movieapp_rest_api.models.movie.MovieNowPlayingResponse;
import com.example.submission3_movieapp_rest_api.networks.Const;
import com.example.submission3_movieapp_rest_api.networks.movie.MovieApiClient;
import com.example.submission3_movieapp_rest_api.networks.movie.MovieApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieFragment extends Fragment implements MovieAdapter.OnItemClick {

    private static final String TAG = "MovieFragment";
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<MovieNowPlaying> listMovie;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = v.findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        loadData();
        return v;
    }

    private void loadData() {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit().create(MovieApiInterface.class);

        retrofit2.Call<MovieNowPlayingResponse> nowPlayingCall = movieApiInterface.getNowPlaying(Const.API_KEY);
        nowPlayingCall.enqueue(new Callback<MovieNowPlayingResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MovieNowPlayingResponse> call, Response<MovieNowPlayingResponse> response) {
                if (response.isSuccessful() && response.body().getNowPlayings() != null) {
                    listMovie = response.body().getNowPlayings();
                    adapter = new MovieAdapter(listMovie, MovieFragment.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieNowPlayingResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(int pos) {
        Intent detailActivity = new Intent(getActivity(), DetailMovieActivity.class);
        detailActivity.putExtra("ID", listMovie.get(pos).getId());
        detailActivity.putExtra("TITLE", listMovie.get(pos).getTitle());
        detailActivity.putExtra("IMG_URL", listMovie.get(pos).getImgUrl());
        detailActivity.putExtra("YEAR", listMovie.get(pos).getReleaseDate());
        detailActivity.putExtra("OVERVIEW", listMovie.get(pos).getOverview());
        detailActivity.putExtra("VOTE_AVERAGE", listMovie.get(pos).getVoteAverage());
        detailActivity.putExtra("RATE", listMovie.get(pos).getRating());
        startActivity(detailActivity);
    }
}
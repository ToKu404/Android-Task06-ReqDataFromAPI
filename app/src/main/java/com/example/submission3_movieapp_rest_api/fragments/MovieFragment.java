package com.example.submission3_movieapp_rest_api.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    private List<MovieNowPlaying> listMovieNowPlaying;
    private ProgressBar tvProgressBar;
    private TextView tvNoRecord;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = v.findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        tvProgressBar = v.findViewById(R.id.pb_movie);
        tvNoRecord = v.findViewById(R.id.tv_mv_empty);
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
                    listMovieNowPlaying = response.body().getNowPlayings();
                    adapter = new MovieAdapter(listMovieNowPlaying, MovieFragment.this);
                    recyclerView.setAdapter(adapter);
                    tvProgressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                    tvNoRecord.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<MovieNowPlayingResponse> call, Throwable t) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        tvProgressBar.setVisibility(View.GONE);
                        tvNoRecord.setVisibility(View.VISIBLE);
                    }
                }, 3000);
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(int pos) {
        Intent detailActivity = new Intent(getActivity(), DetailMovieActivity.class);
        detailActivity.putExtra("ID", listMovieNowPlaying.get(pos).getId());
        detailActivity.putExtra("TITLE", listMovieNowPlaying.get(pos).getTitle());
        startActivity(detailActivity);
    }
}
package com.example.submission3_movieapp_rest_api.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.fragments.HistoryFragment;
import com.example.submission3_movieapp_rest_api.fragments.MovieFragment;
import com.example.submission3_movieapp_rest_api.fragments.TvShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menu_item_tvshow);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.menu_item_tvshow:
                getSupportActionBar().setTitle(getResources().getString(R.string.menu_tvshow));
                fragment = new TvShowFragment();
                break;
            case R.id.menu_item_movie:
                getSupportActionBar().setTitle(getResources().getString(R.string.menu_movie));
                fragment = new MovieFragment();
                break;
            case R.id.menu_item_history:
                getSupportActionBar().setTitle(getResources().getString(R.string.menu_recent));
                fragment = new HistoryFragment();
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_main, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
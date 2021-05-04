package com.example.submission3_movieapp_rest_api.splash_screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.example.submission3_movieapp_rest_api.R;
import com.example.submission3_movieapp_rest_api.activities.MainActivity;

public class SplashScreen extends Activity {
    //Oncreate Method
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Atur layoutnya
        setContentView(R.layout.splash_screen);
        // Handler untuk pindah setelah delay
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }},2000);
    }
}

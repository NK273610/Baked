package com.example.shakt.baked;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.apply();
            Intent i = new Intent(this, IntroActivity.class);
            startActivity(i);


        } else {

            // link for background image : https://www.wallpaperup.com/66805/green_abstract_gaussian_blur.html

            EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                    .withFullScreen()
                    .withSplashTimeOut(3000)
                    .withBackgroundResource(R.color.colorAccent)
                    .withTargetActivity(HomeActivity.class)
                    .withLogo(R.mipmap.baked_icon);

            //add custom font
//            Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "Pacifico.ttf");
//            config.getAfterLogoTextView().setTextSize(50);

//            config.getAfterLogoTextView().setTypeface(pacificoFont);

            View easySplashScreenView = config.create();
            setContentView(easySplashScreenView);
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

        }

    }
}

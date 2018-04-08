package com.example.shakt.baked;

import android.*;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;


//the code was directly taken and used from this library https://github.com/TangoAgency/material-intro-screen
//we have changed the parameters but the methods were directly used from this library
public class IntroActivity extends MaterialIntroActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLastSlideAlphaExitTransition(true);

        getBackButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });
        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorAccent)
                        .buttonsColor(R.color.colorWhite)
                        .image(R.mipmap.baked_app_icon)
                        .title("Please have time and checkout strains")
                        .description("Would you try?")
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("Our Recommendation gives you info about best strain in market");
                    }
                }, "Work with love"));

        addSlide(new SlideFragmentBuilder() //we add slides method with given functionalities
                .backgroundColor(R.color.colorWhite)
                .buttonsColor(R.color.colorAccent)
                .title("Want more?")
                .description("Go on")
                .build());

        addSlide(new SlideFragmentBuilder() //add slide method with given functionalities
                        .backgroundColor(R.color.colorAccent)
                        .buttonsColor(R.color.colorPrimary)
                        .neededPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION})
                        .image(R.drawable.ic_next)
                        .title("We provide best Strains")
                        .description("ever")
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("Try us!");
                    }
                }, "Strains"));

        addSlide(new SlideFragmentBuilder()//add slide method with given functionalities
                .backgroundColor(R.color.colorPrimary)
                .buttonsColor(R.color.colorWhite)
                .title("That's it")
                .description("Would you join us?")
                .build());
    }

}

package com.erikmejia.onamet;

import android.os.Bundle;

import com.erikmejia.onamet.ui.SampleSlide;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(SampleSlide.newInstance(R.layout.fragment_intro_fragment_one));
        addSlide(SampleSlide.newInstance(R.layout.fragment_intro_fragment_two));
        addSlide(SampleSlide.newInstance(R.layout.fragment_intro_fragment_three));
        addSlide(SampleSlide.newInstance(R.layout.fragment_intro_fragment_four));

//        Ask for GPS permission on the second slide

        setZoomAnimation();
        showStatusBar(false);

        showSkipButton(false);
//        setProgressButtonEnabled(false);
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}

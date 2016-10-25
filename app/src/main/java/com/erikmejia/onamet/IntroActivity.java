package com.erikmejia.onamet;

import android.graphics.Typeface;
import android.os.Bundle;

import com.erikmejia.onamet.ui.IntroFragmentFour;
import com.erikmejia.onamet.ui.IntroFragmentOne;
import com.erikmejia.onamet.ui.IntroFragmentThree;
import com.erikmejia.onamet.ui.IntroFragmentTwo;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setWizardMode(true);

//        Custom fragments
        IntroFragmentOne fragmentOne = new IntroFragmentOne();
        IntroFragmentTwo fragmentTwo = new IntroFragmentTwo();
        IntroFragmentThree fragmentThree = new IntroFragmentThree();
        IntroFragmentFour fragmentFour = new IntroFragmentFour();

        addSlide(AppIntro2Fragment.newInstance("Bienvenido a Onamet",
                "/fonts/Brandon_light.otf",
                "Podras estar al tanto de toda información relacionada con el clima en República Dominicana",
                "/fonts/Brandon_reg.otf",
                R.drawable.weather_clear, getResources().getColor(R.color.colorPrimary)));

        addSlide(AppIntro2Fragment.newInstance("Pronósticos diarios",
                "fonts/Brandon_thin.otf",
                "Recibes estado del clima para los próximos 13 días",
                "fonts/Brandon_reg.otf",
                R.drawable.weather_few_clouds, getResources().getColor(R.color.colorPrimaryDark)));

        addSlide(AppIntro2Fragment.newInstance("Boletines Oficiales",
                "fonts/Brandon_light.otf",
                "Los boletines oficiales al instante, para que estés al tanto de lo último",
                "fonts/Brandon_light.otf",
                R.drawable.weather_storm, getResources().getColor(R.color.material_purple_a700)));

        addSlide(AppIntro2Fragment.newInstance("Aunque no tengas Internet",
                "fonts/Brandon_thin.otf",
                "Recibiras boletines de emergencia en mensajes de texto por si no tienes Internet, " +
                        "necesitas iniciar sesión para habilitarlo",
                "fonts/Brandon_reg.otf",
                R.drawable.weather_rain_day, getResources().getColor(R.color.colorAccent)));

//        Ask for GPS permission on the second slide

        setDepthAnimation();
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

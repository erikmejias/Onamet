package com.erikmejia.onamet.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erikmejia.onamet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragmentThree extends Fragment {


    public IntroFragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_fragment_three, container, false);
    }

}

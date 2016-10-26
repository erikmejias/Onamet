package com.erikmejia.onamet.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.SolverVariable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikmejia.onamet.R;

/**
 * Created by erik on 10/25/16.
 */

public class SampleSlide extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";
    private int layoutResId;
    private Typeface titleFont;
    private Typeface descriptionFont;

    public static SampleSlide newInstance(int layoutResId) {
        SampleSlide sampleSlide = new SampleSlide();

        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        sampleSlide.setArguments(args);

        return sampleSlide;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)) {
            layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
        }

        titleFont = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Brandon_reg.otf");
        descriptionFont = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Brandon_light.otf");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(layoutResId, container, false);
        TextView title = (TextView) rootView.findViewById(R.id.welcome_title_text);
        TextView description = (TextView) rootView.findViewById(R.id.welcome_description_text);

        rootView.getBackground().setAlpha(140);


        title.setTypeface(titleFont);
        description.setTypeface(descriptionFont);

        return rootView;
    }
}

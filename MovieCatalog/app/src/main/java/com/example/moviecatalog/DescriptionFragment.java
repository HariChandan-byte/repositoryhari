package com.example.moviecatalog;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.moviecatalog.databinding.FragmentDescriptionBinding;


public class DescriptionFragment extends Fragment {

    FragmentDescriptionBinding fragmentDescriptionBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View v = inflater.inflate(R.layout.fragment_description, container, false);

        fragmentDescriptionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_description, container, false);

        View v = fragmentDescriptionBinding.getRoot();

        Bundle bundle = this.getArguments();

        String name = bundle.getString("Name");
        int season = bundle.getInt("Season");
        final int number = bundle.getInt("Number");
        int runTime = bundle.getInt("RunTime");
        String summary = bundle.getString("Summary");
        String image = bundle.getString("ImageUrl");

        String s1 = "Season: " + season;
        String s2 = "Episode: " + number;
        String s3 = runTime + " Minutes";

        fragmentDescriptionBinding.textView5.setText(name);
        fragmentDescriptionBinding.textView6.setText(s1);
        fragmentDescriptionBinding.textView7.setText(s2);
        fragmentDescriptionBinding.textView8.setText(s3);
        fragmentDescriptionBinding.textView9.setText(summary);

        Glide.with(v.getContext()).load(image).into(fragmentDescriptionBinding.imageView3);

        fragmentDescriptionBinding.floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EpisodeFragment episodeFragment = new EpisodeFragment();

                getFragmentManager().beginTransaction().replace(R.id.mainLayout, episodeFragment).commit();

            }
        });

        return v;
    }



}
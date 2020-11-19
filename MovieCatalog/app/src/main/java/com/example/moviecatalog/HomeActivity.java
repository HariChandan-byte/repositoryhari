package com.example.moviecatalog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.moviecatalog.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    EpisodeFragment episodeFragment;

    ActivityHomeBinding activityHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);

        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        episodeFragment = new EpisodeFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.mainLayout, episodeFragment).commit();

        activityHomeBinding.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ab = new AlertDialog.Builder(HomeActivity.this);

                ab.setMessage("Do You Want To Quit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                finish();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.cancel();

                            }
                        })
                        .show();
            }
        });

    }

}
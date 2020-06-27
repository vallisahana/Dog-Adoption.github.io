package com.example.sahana.petadoption;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private  static int SPLASH_TIME_OUT =100;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(getActivity(), HomeScreenActivity.class);
                startActivity(homeIntent);
                finish();
            }

            private void finish() {
            }
        }, SPLASH_TIME_OUT);

        return inflater.inflate(R.layout.fragment_home,container,false);


    }
}

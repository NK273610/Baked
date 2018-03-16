package com.example.shakt.baked;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class home_tab_1 extends Fragment implements View.OnClickListener{



    public home_tab_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_tab_1, container, false);
        // Inflate the layout for this fragment

        return view;
    }


    @Override
    public void onClick(View view) {

    }
}

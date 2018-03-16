package com.example.shakt.baked;

import android.app.Fragment;

/**
 * Created by nikhildhirmalani on 16/03/18.
 */


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ReviewClass extends android.support.v4.app.Fragment {

    TextView text;
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle saveInstanceState){
        return inflater.inflate(R.layout.review, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        text=getView().findViewById(R.id.textView);



    }
}

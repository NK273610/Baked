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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


public class home_tab_2 extends Fragment implements View.OnClickListener {

    private Button mapButton;
    private Button gotoMap;


    public home_tab_2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_tab_2, container, false);
//         Inflate the layout for this fragment
        mapButton = (Button) view.findViewById(R.id.find);
        mapButton.setOnClickListener(this);
        gotoMap = view.findViewById(R.id.gotoMap
        );
        gotoMap.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.find:
                Intent intent  = new Intent(getActivity().getApplicationContext(), MapActivity.class);
                intent.putExtra("Redirecting to", "Map");
                startActivity(intent);
                break;
            case R.id.gotoMap:
                Intent intent2  = new Intent(getActivity().getApplicationContext(), MapActivity.class);
                intent2.putExtra("Redirecting to", "Map");
                startActivity(intent2);
                break;
        }
    }
}

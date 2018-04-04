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

    private Button bodyButton;
    private Button brainButton;

    public home_tab_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_tab_1, container, false);
        // Inflate the layout for this fragment
        bodyButton = (Button) view.findViewById(R.id.body_button);
        bodyButton.setOnClickListener(this);
        brainButton = (Button) view.findViewById(R.id.brain_button);
        brainButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.body_button:
                Intent intent3  = new Intent(getActivity().getApplicationContext(), ProductDesc.class);
                intent3.putExtra("type", "sativa");
                startActivity(intent3);
                break;
            case R.id.brain_button:
                Intent intent4  = new Intent(getActivity().getApplicationContext(), ProductDesc.class);
                intent4.putExtra("type", "indica");
                startActivity(intent4);
                break;
        }

    }

}

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

    private Button infoButton;
    private Button accountButton;

    public home_tab_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_tab_1, container, false);
        // Inflate the layout for this fragment
        infoButton = (Button) view.findViewById(R.id.info_button);
        infoButton.setOnClickListener(this);
        accountButton = (Button) view.findViewById(R.id.account_button);
        accountButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.account_button:
                Intent intent1  = new Intent(getActivity().getApplicationContext(), UserAccount.class);
                startActivity(intent1);
                break;
            case R.id.info_button:
                Intent intent2  = new Intent(getActivity().getApplicationContext(), InfoActivity.class);
                startActivity(intent2);
                break;
        }

    }

}

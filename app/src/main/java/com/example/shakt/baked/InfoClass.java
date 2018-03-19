package com.example.shakt.baked;

/**
 * Created by nikhildhirmalani on 16/03/18.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class InfoClass extends Fragment {

    Button effects;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle saveInstanceState){
        return inflater.inflate(R.layout.infotab, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        effects=getView().findViewById(R.id.Effects);

        // Recycle View
//        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//
//        ArrayList<String> dataList = new ArrayList<>();
//        for(char ch='A'; ch<='Z'; ch++) {
//            dataList.add(Character.toString(ch));
//        }
//
//        mAdapter = new MyAdapter(dataList);
//        recyclerView.setAdapter(mAdapter);

    }
}


package com.example.shakt.baked;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Shakti on 05-03-2018.
 */

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}


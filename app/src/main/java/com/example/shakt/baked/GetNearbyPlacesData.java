package com.example.shakt.baked;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.DOMImplementation;

import java.io.IOException;
import java.net.URL;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.*;

/**
 * Created by Shakti on 05-03-2018.
 */

// inspired from : https://github.com/priyankapakhale/GoogleMapsNearbyPlacesDemo

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    GoogleMap mMap;
    String url;
    private Context mContext;
    private MapActivity activity;

    private ArrayList<String> mAddress = new ArrayList<>();
    private ArrayList<String> mUrls = new ArrayList<>();

    public GetNearbyPlacesData (Context context, MapActivity activity1){
        mContext = context;
        activity = activity1;
    }

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];

        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googlePlacesData = downloadUrl.getUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String,String>> nearByPlaceList = null;
        DataParser dataParser = new DataParser();
        nearByPlaceList = dataParser.parse(s);
        showNearbyPlaces(nearByPlaceList);
        activity.setList(nearByPlaceList);
    }

    private void showNearbyPlaces(final List<HashMap<String, String>> nearbyPlaceList){

        for (int i = 0; i < nearbyPlaceList.size(); i++){
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            mAddress.add(vicinity);
            mUrls.add("https://www.mynslc.com/-/media/About/About-Landing-Pages/650x450/650x450-About-Media-Our-logo.jpg");

            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));


            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName+" "+ vicinity);

            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.baked_map_marker));

            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

        }

    }

}


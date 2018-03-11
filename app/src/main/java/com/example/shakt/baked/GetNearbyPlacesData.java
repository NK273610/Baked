package com.example.shakt.baked;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.DOMImplementation;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Shakti on 05-03-2018.
 */

// inspired from : https://github.com/priyankapakhale/GoogleMapsNearbyPlacesDemo

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    GoogleMap mMap;
    String url;

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

    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList){
        for (int i = 0; i < nearbyPlaceList.size(); i++){
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");

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


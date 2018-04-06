package com.example.shakt.baked;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.Manifest;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


// inspired from : https://github.com/priyankapakhale/GoogleMapsNearbyPlacesDemo


public class MapActivity extends AppCompatActivity
    implements OnMapReadyCallback{

    private static final String TAG = MapActivity.class.getSimpleName();
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private SupportMapFragment mapFragment;

    private LocationRequest mLocationRequest;
    private Marker mCurrLocationMarker;

    private Button nslc_button;
    int PROXIMITY_RADIUS = 800;
    double latitude;
    double longitude;
    public ArrayList<String> mAddress = new ArrayList<>();
    public ArrayList<String> mUrls = new ArrayList<>();
    private List<HashMap<String,String>> nearByPlaceList = new ArrayList<>();
    private RecyclerView recyclerView;
    private mapRecycleViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        nslc_button = findViewById(R.id.nslcButton);
        recyclerView = findViewById(R.id.recyclerViewMap);


        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private String getUrl(double latitude, double longitude, String nearbyPlace){
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyAuPA0-uOPx-aHkuqj9AxGAPnwaoEzjSmw");

        return googlePlaceUrl.toString();

    }


    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mFusedLocationProviderClient != null) {
            mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                mMap.setMyLocationEnabled(true);
                mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        } else {
                //Request Location Permission
                checkLocationPermission();
        }


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                final LatLng markerPosition = marker.getPosition();
                int selected_marker = -1;
                for (int i = 0; i < nearByPlaceList.size(); i++) {
                    LatLng latLng = new LatLng(Double.parseDouble(nearByPlaceList.get(i).get("lat")),
                                                Double.parseDouble(nearByPlaceList.get(i).get("lng")));
                    if (markerPosition.latitude == latLng.latitude && markerPosition.longitude == latLng.longitude) {
                        selected_marker = i;
                    }
                }

                CameraPosition cameraPosition = new CameraPosition.Builder().target(markerPosition).zoom(12).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(selected_marker);

                marker.showInfoWindow();


                return false;
            }
        });

    }

    public void setList(List<HashMap<String,String>> list) {
        this.nearByPlaceList = list;
        Log.d("list", nearByPlaceList.get(1).toString());
        fillrecyclerView();

    }

    LocationCallback mLocationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                Log.i("MapActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                Location mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }

                //Place current location marker
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mCurrLocationMarker = mMap.addMarker(markerOptions);

                //move map camera
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }
        }

    };


    public void onClick(View view){
        mMap.clear();
        String nslc = "NSLC";
        String url = getUrl(latitude, longitude, nslc);
        Object dataTransfer[] = new Object[2];
        dataTransfer[0] = mMap;
        dataTransfer[1] = url;

        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData(this, this);
        getNearbyPlacesData.execute(dataTransfer);

        Toast.makeText(MapActivity.this, "Nearby Stores", Toast.LENGTH_SHORT).show();
    }

    public void fillrecyclerView(){

        for (int i = 0; i < nearByPlaceList.size(); i++){
            HashMap<String, String> googlePlace = nearByPlaceList.get(i);

            String vicinity = googlePlace.get("vicinity");
            mAddress.add(vicinity);
            mUrls.add("https://www.mynslc.com/-/media/About/About-Landing-Pages/650x450/650x450-About-Media-Our-logo.jpg");

        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new mapRecycleViewAdapter(this, mAddress, mUrls);
        recyclerView.setAdapter(adapter);

    }


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
            }

        }

    }

}

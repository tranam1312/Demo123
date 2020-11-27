package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback {
    private GoogleMap map;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 10001;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(final GoogleMap mMap) {
        final String[] finalAdd = new String[0];
        map= mMap;
        final Database database = Room.databaseBuilder(getApplicationContext(), Database.class, "Database").allowMainThreadQueries().build();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);


            }
        }


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {

                    database.viTriDao().insert(new ViTri(adda(latLng), latLng.latitude, latLng.longitude));
                    mMap.addMarker(new MarkerOptions().position(latLng).title(adda(latLng)));
                    mMap.animateCamera(CameraUpdateFactory.zoomIn());
                    mMap.getUiSettings().setCompassEnabled(true);
                    mMap.getUiSettings().setZoomControlsEnabled(true);
                    mMap.getUiSettings().setZoomGesturesEnabled(true);
                    mMap.getUiSettings().setMyLocationButtonEnabled(false);
                    mMap.setTrafficEnabled(true);
                    mMap.setBuildingsEnabled(true);




                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Intent intent = new Intent(getApplication(), MainActivity5.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", marker.getTitle());
                        bundle.putDouble("kd", marker.getPosition().latitude);
                        bundle.putDouble("vt", marker.getPosition().longitude);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });
            }

            public String adda(LatLng latLng) {
                String a = null;
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    android.location.Address obj = addresses.get(0);
                    String add = obj.getAddressLine(0);
                    add = add + "\n" + obj.getCountryName();
                    add = add + "\n" + obj.getCountryCode();
                    add = add + "\n" + obj.getAdminArea();
                    add = add + "\n" + obj.getPostalCode();
                    add = add + "\n" + obj.getSubAdminArea();
                    add = add + "\n" + obj.getLocality();
                    add = add + "\n" + obj.getSubThoroughfare();
                    a= add;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return a;
            }});








        final ArrayList<ViTri> viTriList = (ArrayList<ViTri>) database.viTriDao().getAll();
        if (viTriList.size() > 0) {
            for (int i = 0; i < viTriList.size(); i++) {
                LatLng latLng1 = new LatLng(viTriList.get(i).getLatitude(), viTriList.get(i).getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng1).title(viTriList.get(i).getName()));


            }
            ;
        }
    }


    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        map.setMyLocationEnabled(true);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                enableMyLocation();
            }
            else {

            }
        }
    }
}









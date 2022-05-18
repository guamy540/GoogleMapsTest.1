package com.example.googlemapstest1;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.googlemapstest1.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemapstest1.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Button btn;
    Button btnDirections;
    MarkerOptions bellingham, issaquah;

    Polyline currentPolyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        bellingham = new MarkerOptions().position(new LatLng(48.740768, -122.488380)).title("Bellingham");
        issaquah = new MarkerOptions().position(new LatLng(47.563251, -122.021851)).title("Issaquah");

        LatLng bellingham2 = new LatLng(48.740768, -122.488380);
        LatLng issaquah2 = new LatLng(47.563251, -122.021851);
        LatLng guam = new LatLng(13.4708, 144.8181);

        //button pressed to go to premade location
        btn = findViewById(R.id.gotoGuam);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.addMarker(new MarkerOptions().position(guam).title("Marker on Barrigada,Guam"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(guam, 15));
            }
        });

        //button pressed to test polyline
        btnDirections = findViewById(R.id.directions);
        btnDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PolylineOptions rectOption = new PolylineOptions()
                        .add(bellingham2)
                        .add(issaquah2)
                        .add(guam);

                Polyline polyline = mMap.addPolyline(rectOption);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bellingham2, 5));



            }
        });



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
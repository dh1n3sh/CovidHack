package com.example.bourbon.activities.arumugam_activities;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bourbon.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

import print.Print;

public class MapsActivity extends FragmentActivity
        implements
        OnMapReadyCallback{
    private BottomSheetBehavior<View> behavior;
    private GoogleMap mMap;
    private ArrayList<HospitalDetails> results;
    private ArrayList<MarkerOptions> m;
    private int checked;
    private Button hospital;
    private Button pharmacy;
    private LinearLayout hospitaldetailslayout;
    private Print print;
    private FusedLocationProviderClient flpc;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_arumugam);

        //setting radio
        print = new Print(getApplicationContext());
        checked=0;
        hospital= findViewById(R.id.search_hospital);
        pharmacy = findViewById(R.id.search_pharmacy);
        hospitaldetailslayout = findViewById(R.id.hospital_details_linear);

        hospitaldetailslayout.setVisibility(View.GONE);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        flpc = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);

        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                if(locationResult==null) return;

                Location location = locationResult.getLastLocation();
                if (location != null) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15.0f);
                    mMap.animateCamera(cameraUpdate);
                    pharmacy.setTextColor(getApplication().getResources().getColor(R.color.black));
                    pharmacy.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));
                    hospital.setTextColor(getApplication().getResources().getColor(R.color.black));
                    hospital.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));
                    flpc.removeLocationUpdates(this);
                }
                else
                {
                    print.fprintf("Failed to get your location.");
                }
            }
        };

        try{
            View bottomSheet = findViewById(R.id.bottom_sheet);
            behavior = BottomSheetBehavior.from(bottomSheet);

            final LinearLayout inner = findViewById(R.id.linear1);

            inner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    inner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    View hidden = inner.getChildAt(inner.getChildCount() - 1);

                    behavior.setPeekHeight(hidden.getBottom());
                }
            });
        }
        catch(Exception e)
        {
            Log.d("oncreatebottomsheet",e.toString());

        }
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
    public void getPlaces(String place){
        try {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    flpc.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            mMap.clear();

                            if(location==null)
                                return;

                            results = ApiQuery.ping(location,place.toLowerCase());

                            if(results==null)
                            {
                                print.fprintf("No results found. Please try again in few moments");
                                return;
                            }

                            if(results.size()==0) {
                                print.fprintf("Please try again in few moments");
                                checked=0;
                            }
                            m=new ArrayList();

                            for(int i=0;i<results.size();i++)
                            {
                                m.add(new MarkerOptions().position(new LatLng(results.get(i).getLocationlatlng()[0],results.get(i).getLocationlatlng()[1])));
                                m.get(i).snippet(i+"");
                            }

                            if(m==null)
                                return;

                            for(int i=0;i<m.size();i++)
                                mMap.addMarker(m.get(i));

                        }
                    });

                }
            });
        }
        catch(Exception e)
        {
            Log.d("searchbutton",e.toString());
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        checkingPermissions();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getSnippet().equals("current location"))
                {
                    print.sprintf("My Location");
                    return true;
                }

                hospitaldetailslayout.setVisibility(View.VISIBLE);
                try {
                    flpc.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location l) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(l.getLatitude() + "," + l.getLongitude());
                            String location = sb.toString();

                            HospitalDetails obj = results.get(Integer.parseInt(marker.getSnippet()));
                            TextView hospitalname = findViewById(R.id.hospitalname);
                            TextView address = findViewById(R.id.address);
                            RatingBar rating = findViewById(R.id.rating);
                            TextView openinghrs = findViewById(R.id.openinghrs);
                            Button directionbutton = findViewById(R.id.directions);

                            hospitalname.setText(obj.getHospitalName());
                            address.setText(obj.getAddress());
                            try {
                                rating.setVisibility(View.VISIBLE);
                                rating.setRating(Float.parseFloat(obj.getRating()));
                            } catch (Exception e) {
                                rating.setVisibility(View.GONE);
                            }

                            if(!obj.getOpeningHours().equals("Not Available")) {
                                openinghrs.setVisibility(View.VISIBLE);
                                openinghrs.setText(obj.getOpeningHours());
                            }
                            else
                            {
                                openinghrs.setVisibility(View.GONE);
                            }
                            directionbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    String direction = "";
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("https://www.google.com/maps/dir/?api=1");
                                    sb.append("&origin=" + location);
                                    sb.append("&destination=" + obj.getLocationlatlng()[0] + "," + obj.getLocationlatlng()[1]);

                                    direction = sb.toString();

                                    Uri uri = Uri.parse(direction);

                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);

                                }
                            });
                            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    });

                }
                catch(Exception e)
                {
                    Log.d("onmarkerclick",e.toString());
                }
            return true;
            }
        });
    }
    private boolean checkingPermissions()
    {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this,
                    Manifest.permission.READ_CONTACTS)) {
                print.sprintf("Location Services required");
            } else {
                ActivityCompat.requestPermissions(MapsActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
            }
        }
        else{
            checkLocation();
            mMap.setMyLocationEnabled(true);
            flpc.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper());
        }

        if(!checkPlayServices())
        {
            print.fprintf("Please install Google Play Services");
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    checkLocation();
                    mMap.setMyLocationEnabled(true);
                    flpc.requestLocationUpdates(locationRequest,locationCallback,Looper.getMainLooper());
                } else {
                    print.fprintf("Cannot find hospitals/pharmacies without location services");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, 9000);
            } else {
                finish();
            }

            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void finderOption(View view) {
        if(view == hospital && checked!=1)
        {
            hospitaldetailslayout.setVisibility(View.GONE);
            checked=1;
            ((Button)view).setTextColor(getApplication().getResources().getColor(R.color.white));
            view.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_selected));
            pharmacy.setTextColor(getApplication().getResources().getColor(R.color.black));
            pharmacy.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));
            getPlaces("hospital");
        }
        else  if(view == pharmacy && checked!=2)
        {
            hospitaldetailslayout.setVisibility(View.GONE);
            checked=2;
            ((Button)view).setTextColor(getApplication().getResources().getColor(R.color.white));
            view.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_selected));
            hospital.setTextColor(getApplication().getResources().getColor(R.color.black));
            hospital.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));
            getPlaces("pharmacy");
        }
    }
    
    private void checkLocation()
    {
        LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled("gps")) {

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(10000 / 2);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
            builder.setAlwaysShow(true);

            Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(this)
                    .checkLocationSettings(builder.build());
            result.addOnCompleteListener(task -> {
                try {
                    LocationSettingsResponse response =
                            task.getResult(ApiException.class);
                } catch (ApiException ex) {
                    switch (ex.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                ResolvableApiException resolvableApiException =
                                        (ResolvableApiException) ex;
                                resolvableApiException
                                        .startResolutionForResult(MapsActivity.this,
                                                100);
                            } catch (IntentSender.SendIntentException e) {
                                Log.d("Locationrequest",e.toString());
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            print.fprintf("Cannot find hospitals/pharmacies without GPS");
                            break;
                        case LocationSettingsStatusCodes.SUCCESS:
                            break;
                    }
                }
            });
        }else {
            print.sprintf("GPS already enabled");
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode) {
            case LocationRequest.PRIORITY_HIGH_ACCURACY:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        print.sprintf("GPS enabled");
                        Log.i("activityresult", "onActivityResult: GPS Enabled by user");
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        print.fprintf("Cannot find hospitals/pharmacies without GPS");
                        Log.i("activityresult", "onActivityResult: User rejected GPS request");
                        break;
                    default:
                        break;
                }
                break;
        }
    }
}

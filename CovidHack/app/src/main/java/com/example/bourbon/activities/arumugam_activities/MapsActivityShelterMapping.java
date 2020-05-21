package com.example.bourbon.activities.arumugam_activities;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bourbon.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import print.Print;

public class MapsActivityShelterMapping extends FragmentActivity implements OnMapReadyCallback,
        View.OnClickListener,
        GoogleMap.OnMyLocationChangeListener{

    private GoogleMap mMap;
    private DatabaseReference databaseReference;
    private Print print;
    private Button search;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_shelter_mapping_arumugam);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_shelter_mapping);
        mapFragment.getMapAsync(this);

        print = new Print(this);

        sp = (Spinner) findViewById(R.id.distance);

        String[] options = new String[] { "5 Kilometers","10 Kilometers","50 Kilometers","100 Kilometers"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,options);
        sp.setAdapter(adapter);


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
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        checkingPermissions();

    }

    private void fetchShelters(Location location,int dis)
    {
        databaseReference=FirebaseDatabase.getInstance().getReference("/Shelters");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    HashMap<String, String> dict = (HashMap<String, String>) dataSnapshot.getValue();
                    plotMarkers(dict, location,dis);
                }
                else
                {
                    print.fprintf("Sorry. No shelters found.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                print.fprintf("Failed to connect to server");
            }
        });
    }

    private void plotMarkers(HashMap<String,String> dict,Location location,int dis)
    {
        Log.d("Arumugam","entered plot markers");
        mMap.clear();
        LatLngBounds.Builder latlngbuilder = new LatLngBounds.Builder();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(location.getLatitude(),location.getLongitude()));
        markerOptions.title("Your Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        latlngbuilder.include(new LatLng(location.getLatitude(),location.getLongitude()));
        mMap.addMarker(markerOptions);

        int cnt=0;

        double curdist=0.0;

        for(String title : dict.keySet())
        {
            String l = dict.get(title);
            String[] latlng = l.split(",");
            LatLng ll = new LatLng(Double.parseDouble(latlng[0]),Double.parseDouble(latlng[1]));
            Location temp = new Location("gps");
            temp.setLongitude(ll.longitude);
            temp.setLatitude(ll.latitude);
            curdist = location.distanceTo(temp);
            if(Math.ceil(curdist)<=(dis*1000))
            {
                cnt++;
                MarkerOptions markerOptions2 = new MarkerOptions();
                markerOptions2.position(ll);
                markerOptions2.title(title);
                latlngbuilder.include(ll);
                mMap.addMarker(markerOptions2);
            }
        }

        Log.d("Arumugam","Count : "+cnt);
        print.sprintf("got "+cnt+" shelters in the radius of "+dis);
        LatLngBounds latLngBounds = latlngbuilder.build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds,200));
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
                                        .startResolutionForResult(MapsActivityShelterMapping.this,
                                                100);
                            } catch (IntentSender.SendIntentException e) {
                                Log.d("Locationrequest",e.toString());
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            print.fprintf("Cannot locate shelters without GPS");
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
                        print.fprintf("Cannot locate shelters without GPS");
                        Log.i("activityresult", "onActivityResult: User rejected GPS request");
                        break;
                    default:
                        break;
                }
                break;
        }
    }



    private boolean checkingPermissions()
    {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivityShelterMapping.this,
                    Manifest.permission.READ_CONTACTS)) {
                print.sprintf("Location Services required");
            } else {
                ActivityCompat.requestPermissions(MapsActivityShelterMapping.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
            }
        }
        else{
            checkLocation();
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationChangeListener(this);
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
                    mMap.setOnMyLocationChangeListener(this);
                } else {
                    print.fprintf("Cannot locate shelters without location services");
                    //Toast.makeText(this,"Cannot provide the location services",Toast.LENGTH_SHORT).show();
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
    public void onClick(View v) {
            Location location = mMap.getMyLocation();

            if(location!=null)
            {
                int dis = Integer.parseInt(sp.getSelectedItem().toString().split(" ")[0]);
                fetchShelters(location,dis);
                Log.d("Arumugam","fetch called with "+location.getLatitude()+","+location.getLongitude());
            }
            else
            {
                print.fprintf("Failed to get your location. Please enable GPS if it is disabled.");
            }
    }

    @Override
    public void onMyLocationChange(Location location) {
        if(location!=null)
        {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),15.0f));
            search = (Button) findViewById(R.id.plot);
            search.setOnClickListener(this);
            mMap.setOnMyLocationChangeListener(null);
            Log.d("Arumugam","first location got button enabled.");
        }
    }
}

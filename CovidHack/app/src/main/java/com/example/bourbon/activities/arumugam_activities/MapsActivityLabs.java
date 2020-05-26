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
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bourbon.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import print.Print;

public class MapsActivityLabs extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private Print print;
    private FusedLocationProviderClient flpc;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private DatabaseReference databaseReference;
    private ArrayList<Labs> labsArrayList;
    private LinearLayout labsLinearLayout;
    private BottomSheetBehavior<View> behavior;
    private View bottomsheet;
    private Marker previousSelectedMarker;
    private Location mylocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_labs_arumugam);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        print = new Print(this);
        labsArrayList = null;

        previousSelectedMarker = null;


        labsLinearLayout = findViewById(R.id.labs_details_linear);
        labsLinearLayout.setVisibility(View.GONE);

        bottomsheet = findViewById(R.id.bottom_sheet_labs);
        behavior = BottomSheetBehavior.from(bottomsheet);

        flpc = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                if(locationResult==null) return;

                Location location = locationResult.getLastLocation();

                if(location!=null)
                {
                    mylocation=location;
                    flpc.removeLocationUpdates(this);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),13.0f));
                }
            }
        };

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

        fetchlabs();

        checkingPermissions();
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
                                        .startResolutionForResult(MapsActivityLabs.this,
                                                100);
                            } catch (IntentSender.SendIntentException e) {
                                Log.d("Locationrequest",e.toString());
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            print.fprintf("Cannot locate Labs without GPS");
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
                        print.fprintf("Cannot locate Labs without GPS");
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

            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivityLabs.this,
                    Manifest.permission.READ_CONTACTS)) {
                print.sprintf("Location Services required");
            } else {
                ActivityCompat.requestPermissions(MapsActivityLabs.this,
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
                    flpc.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper());
                } else {
                    print.fprintf("Cannot locate Labs without location services");
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

    private void fetchlabs()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseReference = FirebaseDatabase.getInstance().getReference("/labs/items");

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        ArrayList<HashMap<String,Object>> arrayList = (ArrayList<HashMap<String, Object>>) dataSnapshot.getValue();
                        Log.d("result",arrayList.toString());
                        ArrayList<Labs> result = new ArrayList<>();

                        try {
                            for (HashMap<String,Object> hashMap : arrayList)
                                result.add(new Labs(hashMap));
                            plotlabs(result);
                        }
                        catch (Exception e)
                        {
                            Log.d("jsonerror",e.toString());
                            print.fprintf("Couldnt't Plot Labs. Please try again later");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("labapi",databaseError.toString());
                    }
                });

            }
        }).start();

    }

    public void plotlabs(ArrayList<Labs> labs)
    {
        runOnUiThread(() -> {

            mMap.clear();

            if(labs==null)
            {
                print.fprintf("No Labs.");
                return;
            }

            labsArrayList = labs;

            int cnt=0;

            for(int i=0; i<labs.size(); ++i)
            {
                if(mylocation.distanceTo(labs.get(i).getLocation())>100*1000) continue;

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(labs.get(i).getLocation().getLatitude(),labs.get(i).getLocation().getLongitude()));
                markerOptions.title(labs.get(i).getName());
                markerOptions.snippet(""+i);
                mMap.addMarker(markerOptions);
                cnt++;
            }

            if(cnt>0)
                print.sprintf("plotted "+cnt+" labs inside 100 km radius");
            else
                print.fprintf("There are no labs in 100 kilometers radius.");

            mMap.setOnMarkerClickListener(this);
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        int index = Integer.parseInt(marker.getSnippet());

        if(previousSelectedMarker!=null)
        {
            previousSelectedMarker.setIcon(BitmapDescriptorFactory.defaultMarker());
        }

        previousSelectedMarker = marker;
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        fillBottomSheet(labsArrayList.get(index));

        Log.d("markertesting",labsArrayList.get(index).toString());
        return true;
    }

    private void fillBottomSheet(Labs obj){

        labsLinearLayout.setVisibility(View.VISIBLE);

        TextView name = (TextView) findViewById(R.id.labsname);
        TextView address = (TextView) findViewById(R.id.labsaddress);
        Button directions = (Button)findViewById(R.id.labsdirections);

        name.setText(obj.getName());
        address.setText(obj.getAddress());

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direction = "";
                StringBuilder sb = new StringBuilder();
                sb.append("https://www.google.com/maps/dir/?api=1");
                sb.append("&origin=" + mylocation);
                sb.append("&destination=" + obj.getLocation().getLatitude() + "," + obj.getLocation().getLongitude());

                direction = sb.toString();

                Uri uri = Uri.parse(direction);

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

}

package com.example.bourbon.activities.arumugam_activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.bourbon.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import print.Print;

public class MapsModule extends FragmentActivity implements OnMapReadyCallback {

    //HospitalFinder's global variables
    private GoogleMap mMap;
    private ArrayList<HospitalDetails> results;
    private ArrayList<MarkerOptions> m;
    private int checked;
    private Button hospital;
    private Button pharmacy;
    private LinearLayout hospitaldetailslayout;

    //Red-Zone's global variables
    private static final String TAG = "MapsActivity";
    private GeofencingClient geofencingClient;
    private GeofenceHelper geofenceHelper;


    //Labs' global variables

    private ArrayList<Labs> labsArrayList;
    private LinearLayout labsLinearLayout;
    private View bottomsheetlabs;
    private Location mylocation;

    //Shelter's global variables
    public int seekValue;
    private TextView helpMsg ;
    private Circle mCircle;
    private LatLng myloc;
    private HashMap<String,String> dict;
    private SeekBar seekbar;


    //Module's global variables
    private Print print;
    private FusedLocationProviderClient flpc;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Button hospitalButton,shelterButton,redzoneButton,labsButton;
    private DatabaseReference databaseReference;
    private BottomSheetBehavior<View> behavior;
    private Marker previousSelectedMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_module_arumugam);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initializeGlobal();

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
        mMap.setPadding(0,40,0,0);
        mMap.setMyLocationEnabled(true);

        settingOnClickListeners();

    }

    //------------------------------------ MODULE'S METHOD---------------------------------------------------


    private void initializeGlobal()
    {
        // Initializing global variables
        print = new Print(this);
        flpc = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //disableBottomSheets();
        hospitalButton = findViewById(R.id.hospital);
        shelterButton = findViewById(R.id.shelter);
        labsButton = findViewById(R.id.labs);
        redzoneButton = findViewById(R.id.redzone);

    }

    private void disableBottomSheets()
    {
        if(findViewById(R.id.bottom_sheet).getVisibility()==View.VISIBLE)
            findViewById(R.id.bottom_sheet).setVisibility(View.GONE);
        if(findViewById(R.id.bottom_sheet_labs).getVisibility()==View.VISIBLE)
            findViewById(R.id.bottom_sheet_labs).setVisibility(View.GONE);
        if(findViewById(R.id.bottom_sheet_shelter).getVisibility()==View.VISIBLE)
            findViewById(R.id.bottom_sheet_shelter).setVisibility(View.GONE);
    }

    private void settingOnClickListeners()
    {
        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableBottomSheets();
                findViewById(R.id.bottom_sheet).setVisibility(View.VISIBLE);
                findViewById(R.id.linear1).setVisibility(View.VISIBLE);
                clearButtonUI();
                selectButtonUI(hospitalButton);
                onClickHospital();
            }
        });
        shelterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableBottomSheets();
                findViewById(R.id.bottom_sheet_shelter).setVisibility(View.VISIBLE);
                clearButtonUI();
                selectButtonUI(shelterButton);
                onClickShelter();
            }
        });
        redzoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disableBottomSheets();
                clearButtonUI();
                selectButtonUI(redzoneButton);
                onClickRedzone();
            }
        });
        labsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                disableBottomSheets();
                findViewById(R.id.bottom_sheet_labs).setVisibility(View.VISIBLE);
                clearButtonUI();
                selectButtonUI(labsButton);
                onClickLabs();
            }
        });

        hospitalButton.callOnClick();
    }

    private boolean checkingPermissions()
    {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsModule.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                print.sprintf("Location Services required");
            } else {
                ActivityCompat.requestPermissions(MapsModule.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
            }
        }
        else{
            checkLocation();
            mMap.setMyLocationEnabled(true);
            flpc.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper());
        }

        // FOR RED-ZONE MODULE'S GEOFENCING SERVICES

        if (Build.VERSION.SDK_INT >= 29) {
            //We need background permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                    //We show a dialog and ask for permission
                    print.fprintf("Background Location services is required for req-zone alert.!");
                    ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 101);
                } else {
                    ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 101);
                }
            }

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
                    print.fprintf("Cannot provide service without Location services");
                }
                return;
            }

            case 101: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    print.sprintf("Red-Zones added to the map.!");
                } else {
                    print.fprintf("Background location access is neccessary for Red-zones to trigger.!");
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
                                        .startResolutionForResult(MapsModule.this,
                                                100);
                            } catch (IntentSender.SendIntentException e) {
                                Log.d("Locationrequest",e.toString());
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            print.fprintf("Cannot provide service without GPS");
                            break;
                        case LocationSettingsStatusCodes.SUCCESS:
                            break;
                    }
                }
            });
        }else {
            //print.sprintf("GPS already enabled");
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == LocationRequest.PRIORITY_HIGH_ACCURACY) {
            if (resultCode == Activity.RESULT_OK) {// All required changes were successfully made
                print.sprintf("GPS enabled");
                Log.i("activityresult", "onActivityResult: GPS Enabled by user");
            } else if (resultCode == Activity.RESULT_CANCELED) {// The user was asked to change settings, but chose not to
                print.fprintf("Cannot provide service without GPS");
                Log.i("activityresult", "onActivityResult: User rejected GPS request");
            }
        }
    }

    private void selectButtonUI(Button b)
    {
        b.setTextColor(getApplication().getResources().getColor(R.color.white));
        b.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_selected));
    }

    private void clearButtonUI()
    {
        hospitalButton.setTextColor(getApplication().getResources().getColor(R.color.black));
        hospitalButton.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));

        labsButton.setTextColor(getApplication().getResources().getColor(R.color.black));
        labsButton.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));

        shelterButton.setTextColor(getApplication().getResources().getColor(R.color.black));
        shelterButton.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));

        redzoneButton.setTextColor(getApplication().getResources().getColor(R.color.black));
        redzoneButton.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));
    }

    //--------------------------------------------END OF MODULE'S METHOD DEFINITIONS-----------------------------------------

    //--------------------------------------------HOSPITAL FINDER'S METHODS----------------------------------------------------

    private void onClickHospital()
    {

        mMap.clear();
        previousSelectedMarker=null;
        checked=0;

        hospital= findViewById(R.id.search_hospital);
        pharmacy = findViewById(R.id.search_pharmacy);
        hospitaldetailslayout = findViewById(R.id.hospital_details_linear);

        hospitaldetailslayout.setVisibility(View.GONE);

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
                    flpc.removeLocationUpdates(this);
                }
                else
                {
                    print.fprintf("Failed to get your location.");
                }
            }
        };

        checkingPermissions();

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

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getSnippet().equals("current location"))
                {
                    print.sprintf("My Location");
                    return true;
                }

                if(previousSelectedMarker!=null)
                {
                    previousSelectedMarker.setIcon(BitmapDescriptorFactory.defaultMarker());
                }

                previousSelectedMarker = marker;
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));


                hospitaldetailslayout.setVisibility(View.VISIBLE);
                try {
                    flpc.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location l) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(l.getLatitude() + "," + l.getLongitude());
                            String location = sb.toString();

                            HospitalDetails obj = results.get(Integer.parseInt(marker.getSnippet()));
                            Log.d("hossni",marker.getSnippet());
                            Log.d("hos",obj.toString());
                            TextView hospitalname = findViewById(R.id.hospitalname);
                            TextView address = findViewById(R.id.hospitaladdress);
                            RatingBar rating = findViewById(R.id.hospitlrating);
                            TextView openinghrs = findViewById(R.id.hospitalopeninghrs);
                            Button directionbutton = findViewById(R.id.hospitaldirections);

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
                                    sb.append("&origin=" + l);
                                    sb.append("&destination=" + obj.getLocationlatlng()[0] + "," + obj.getLocationlatlng()[1]);

                                    direction = sb.toString();

                                    Uri uri = Uri.parse(direction);

                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);

                                }
                            });
                            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("flpc last location",e.toString());
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
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("flpc last location",e.toString());
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

    public void finderOption(View view) {
        if(view == hospital && checked!=1)
        {
            findViewById(R.id.linear1).setVisibility(View.VISIBLE);
            hospitaldetailslayout.setVisibility(View.GONE);
            checked=1;
            previousSelectedMarker=null;
            ((Button)view).setTextColor(getApplication().getResources().getColor(R.color.white));
            view.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_selected));
            pharmacy.setTextColor(getApplication().getResources().getColor(R.color.black));
            pharmacy.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));
            getPlaces("hospital");
        }
        else  if(view == pharmacy && checked!=2)
        {
            findViewById(R.id.linear1).setVisibility(View.VISIBLE);
            hospitaldetailslayout.setVisibility(View.GONE);
            checked=2;
            previousSelectedMarker=null;
            ((Button)view).setTextColor(getApplication().getResources().getColor(R.color.white));
            view.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_selected));
            hospital.setTextColor(getApplication().getResources().getColor(R.color.black));
            hospital.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));
            getPlaces("pharmacy");
        }
    }

    //-------------------------------------------END OF HOSPITAL FINDER METHODS DEFINITIONS---------------------------------------------

    //-------------------------------------------RED ZONE'S METHODS----------------------------------------------------------------------

    private void onClickRedzone()
    {
        mMap.clear();
        previousSelectedMarker=null;
        mMap.setOnMarkerClickListener(null);
        geofencingClient = LocationServices.getGeofencingClient(this);
        geofenceHelper = new GeofenceHelper(this);

        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                if(locationResult==null) return;

                Location location = locationResult.getLastLocation();

                if(location!=null)
                {
                    addingGeofences();
                    flpc.removeLocationUpdates(locationCallback);
                }
                else
                {
                    print.fprintf("Failed to get your location.");
                }
            }
        };

        checkingPermissions();
    }

    private void PlotAndAddGeofence(LatLng latLng,float radius,String title) {
        addMarker(latLng,title);
        addCircle(latLng, radius);
        addGeofence(latLng, radius,title);
    }

    private void addGeofence(LatLng latLng, float radius,String name) {

        Geofence geofence = geofenceHelper.getGeofence(name, latLng, radius, Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_DWELL | Geofence.GEOFENCE_TRANSITION_EXIT);
        GeofencingRequest geofencingRequest = geofenceHelper.getGeofencingRequest(geofence);
        PendingIntent pendingIntent = geofenceHelper.getPendingIntent();

        geofencingClient.addGeofences(geofencingRequest, pendingIntent)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: Geofence Added...");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String errorMessage = geofenceHelper.getErrorString(e);
                        Log.d(TAG, "onFailure: " + errorMessage);
                    }
                });
    }

    private void addMarker(LatLng latLng,String title) {
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(title);
        mMap.addMarker(markerOptions);
    }

    private void addCircle(LatLng latLng, float radius) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.argb(255, 255, 0,0));
        circleOptions.fillColor(Color.argb(64, 255, 0,0));
        circleOptions.strokeWidth(4);
        mMap.addCircle(circleOptions);
    }

    private void addingGeofences()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("/Red-Zones/Kerala");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String,String> data = (HashMap<String,String>)dataSnapshot.getValue();
                LatLngBounds.Builder latLngBoundsBuilder = new LatLngBounds.Builder();
                for( String key : data.keySet())
                {
                    String value = data.get(key);
                    String[] values = value.split(",");
                    LatLng latlng = new LatLng(Double.parseDouble(values[0]),Double.parseDouble(values[1]));
                    float radius = Float.parseFloat(values[2])*1000;
                    PlotAndAddGeofence(latlng,radius,key);
                    latLngBoundsBuilder.include(latlng);
                }

                LatLngBounds latLngBounds = latLngBoundsBuilder.build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLngBounds,100);
                mMap.animateCamera(cameraUpdate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                print.fprintf("Failed to Connect to server.!");
            }
        });
    }

    //----------------------------------------END OF RED-ZONE'S METHOD DEFINITIONS-------------------------------------------------

    //----------------------------------------LABS' METHODS------------------------------------------------------------------------

    private void onClickLabs()
    {
        mMap.clear();
        previousSelectedMarker=null;
        mMap.setOnMarkerClickListener(null);
        labsArrayList = null;

        previousSelectedMarker = null;


        labsLinearLayout = findViewById(R.id.labs_details_linear);
        labsLinearLayout.setVisibility(View.GONE);

        bottomsheetlabs = findViewById(R.id.bottom_sheet_labs);
        behavior = BottomSheetBehavior.from(bottomsheetlabs);

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
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),12.0f));
                    fetchlabs();
                }
            }
        };

        checkingPermissions();
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

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
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
            });
        });
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
                sb.append("&origin=" + mylocation.getLatitude()+","+mylocation.getLongitude());
                sb.append("&destination=" + obj.getLocation().getLatitude() + "," + obj.getLocation().getLongitude());

                direction = sb.toString();

                Uri uri = Uri.parse(direction);

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    //-------------------------------------------END OF LABS' METHODS DEFINITIONS--------------------------------------------------

    //-------------------------------------------SHELTER MAPPING'S METHODS---------------------------------------------------------

    private void onClickShelter()
    {
        mMap.clear();
        previousSelectedMarker=null;

        dict = null;
        mMap.setOnMarkerClickListener(null);

        fetchShelters();

        int step = 1;
        int max = 200;

        seekValue = 50;int min = 0;

        View bottomSheet = findViewById(R.id.bottom_sheet_shelter);
        behavior = BottomSheetBehavior.from(bottomSheet);

        helpMsg = findViewById(R.id.shelter_help);

        locationCallback = new LocationCallback(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                if(locationResult==null) return;

                Location location = locationResult.getLastLocation();
                myloc = new LatLng(location.getLatitude(),location.getLongitude());
                seekValue=getNearest(location);
                Log.d("nearest",""+seekValue);
                seekbar.setProgress(seekValue);
                seekbar.setMax((max-seekValue)/step);
                plotMarkers(location,seekValue);
                addCircleShelter(new LatLng(location.getLatitude(),location.getLongitude()),seekValue*1000);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),location.getLongitude()),getZoomLevel(mCircle)),400,null);

                Log.d("Arumugam","first location got button enabled.");
                flpc.removeLocationUpdates(this);
            }
        };

        try {
            seekbar = findViewById(R.id.seekBar);

            seekbar.setOnSeekBarChangeListener(
                    new SeekBar.OnSeekBarChangeListener() {

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            flpc.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        int dis = seekValue;
                                        myloc = new LatLng(location.getLatitude(), location.getLongitude());
                                        plotMarkers(location, dis);
                                        Log.d("Arumugam", "fetch called with " + location.getLatitude() + "," + location.getLongitude());
                                    } else {
                                        print.fprintf("Failed to get your location. Please enable GPS if it is disabled.");
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("flpc last location", e.toString());
                                }
                            });

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress,
                                                      boolean fromUser) {

                            seekValue = min + (progress * step);
                            helpMsg.setText("Selected distance : " + seekValue + " km");
                            if (mCircle == null)
                                addCircleShelter(myloc, seekValue * 1000);
                            else
                                mCircle.setRadius(seekValue * 1000);
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myloc, getZoomLevel(mCircle)), 400, null);

                        }
                    }
            );
        }catch (Exception e){
            Log.d("shelter",e.toString());
        }

        checkingPermissions();
    }

    private int getNearest(Location mylocation)
    {
        Double res = Double.MAX_VALUE;

        try {
            for (String key : dict.keySet()) {
                Double lat = Double.parseDouble(dict.get(key).split(",")[0]);
                Double lng = Double.parseDouble(dict.get(key).split(",")[1]);
                Location temp = new Location("gps");
                temp.setLatitude(lat);
                temp.setLongitude(lng);
                res = Math.min(res, mylocation.distanceTo(temp));
            }


            res = res / 1000;
        }
        catch(Exception e)
        {
            Log.d("getnearest",e.toString());
            return 5;
        }
        return (int) Math.ceil(res);

    }

    private void fetchShelters()
    {
        databaseReference=FirebaseDatabase.getInstance().getReference("/Shelters");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dict = (HashMap<String, String>) dataSnapshot.getValue();
                    //plotMarkers(dict, location,dis);

                }
                else
                {
                    dict=null;
                    //print.fprintf("Sorry. No shelters found.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                print.fprintf("Failed to connect to server");
                dict=null;
            }
        });
    }

    private void plotMarkers(Location location,int dis)
    {
        Log.d("Arumugam","entered plot markers");
        mMap.clear();

        int cnt=0;

        double curdist=0.0;

        if(dict==null)
        {
            print.fprintf("Shelters not found");
            return;
        }

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
                mMap.addMarker(markerOptions2);
            }
        }
        addCircleShelter(myloc,seekValue*1000);
        Log.d("Arumugam","Count : "+cnt);
        print.sprintf("got "+cnt+" shelters in the radius of "+dis);
    }

    private void addCircleShelter(LatLng latLng, float radius) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.argb(255, 0, 191,165));
        circleOptions.fillColor(Color.argb(64, 100, 255,218));
        circleOptions.strokeWidth(4);
        mCircle = mMap.addCircle(circleOptions);
    }

    public float getZoomLevel(Circle circle) {
        float zoomLevel = 15;
        if (circle != null){
            double radius = circle.getRadius();
            double scale = radius / 500;
            zoomLevel =(float) (zoomLevel - Math.log(scale) / Math.log(2));
        }
        return zoomLevel;
    }

}

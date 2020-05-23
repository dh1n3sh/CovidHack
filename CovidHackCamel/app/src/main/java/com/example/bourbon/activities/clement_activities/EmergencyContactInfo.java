package com.example.bourbon.activities.clement_activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bourbon.R;
import com.example.bourbon.activities.arumugam_activities.MapsActivity;
import com.example.bourbon.activities.clement_activities.adapter.ProductRecyclerViewAdapter;
import com.example.bourbon.activities.clement_activities.model.ProductDetails;
import com.example.bourbon.databinding.RvHarishEmergencyContactNumBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import print.Print;

import static com.google.android.gms.location.LocationSettingsRequest.*;

public class EmergencyContactInfo extends Activity {
    private FusedLocationProviderClient flpc;
    private RvHarishEmergencyContactNumBinding binding;
    private ArrayList<ProductDetails> products =new ArrayList<>();
    private ProductRecyclerViewAdapter adapter;
    private DatabaseReference mDatabase;


    void configRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(EmergencyContactInfo.this));
        adapter=new ProductRecyclerViewAdapter(products);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(EmergencyContactInfo.this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(EmergencyContactInfo.this, R.layout.rv_harish_emergency_contact_num);
        configRecyclerView();

        flpc = LocationServices.getFusedLocationProviderClient(this);
        checkingPermissions();

    }

    void fetchProdFromFirebase(){
        //clement complete this function
        //this.products=prod fetched from firebase;
//        Toast.makeText(this,"fetchProd",Toast.LENGTH_SHORT).show();
        flpc.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                try {
                    if (location != null) {
//                        Toast.makeText(getApplicationContext(), location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(EmergencyContactInfo.this, "Succccesss", Toast.LENGTH_SHORT).show();
                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                        Toast.makeText(EmergencyContactInfo.this, location.getLatitude() + "", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(EmergencyContactInfo.this, addresses.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("District",addresses.toString());
                        if (addresses.size() > 0 && (addresses.get(0).getSubAdminArea() !=null)) {
//                            Toast.makeText(getApplicationContext(), addresses.get(0).getSubAdminArea() + "", Toast.LENGTH_SHORT).show();
                            String district = addresses.get(0).getSubAdminArea();
//                            Toast.makeText(EmergencyContactInfo.this,district, Toast.LENGTH_SHORT).show();
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            mDatabase.child("toll-free").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    if (snapshot.hasChild(district)) {
                                        // run some code

                                        String emergency = snapshot.child(district).child("emergency").getValue().toString();
                                        String landline = snapshot.child(district).child("landline").getValue().toString();
                                        ProductDetails pd=new ProductDetails(district,emergency,landline);
                                        products.add(pd);
                                        adapter.notifyDataSetChanged();
                                        Print p = new Print(EmergencyContactInfo.this);
                                        p.sprintf("Click on a number to make a call");

                                    } else {
                                        Toast.makeText(EmergencyContactInfo.this, "Contact Info Not Found.", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(EmergencyContactInfo.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
//                            Toast.makeText(EmergencyContactInfo.this, "Please Try Again later to get the contact info", Toast.LENGTH_SHORT).show();
                            NoLoc();
                        }
                    } else {
//                        Toast.makeText(getApplicationContext(), "Loc- null", Toast.LENGTH_SHORT).show();
                        NoLoc();
                    }
                } catch (Exception e) {
                    Log.d("loc", e.toString());
                    Toast.makeText(EmergencyContactInfo.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    void NoLoc(){
        FirebaseDatabase.getInstance().getReference().child("toll-free")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String city = snapshot.getKey().toString();
                            String emergency = snapshot.child("emergency").getValue().toString();
                            String landline = snapshot.child("landline").getValue().toString();
                            ProductDetails pd=new ProductDetails(city,emergency,landline);
                            products.add(pd);
                            adapter.notifyDataSetChanged();

                        }
                        Print p = new Print(EmergencyContactInfo.this);
                        p.sprintf("Click on a number to make a call");
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    private void checkingPermissions()
    {

        if(checkPlayServices()) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_FINE_LOCATION}, 100);

            } else {
                    checkLocation();
//                    fetchProdFromFirebase();
            }
        }
        else
        {
            print.Print p = new Print(getApplicationContext());
            p.sprintf("Google Play Services required.!");
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100 && grantResults.length>0 )
        {
            boolean flag=true;

            for(int i : grantResults)
            {
                if(i!=PackageManager.PERMISSION_GRANTED)
                {
                    flag=false;
                    break;
                }
            }

            if(flag) {
                checkLocation();
//                fetchProdFromFirebase();
            }
        }
    }

    private void checkLocation()
    {
        LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled("gps")) {
//                print.Print p = new Print(getApplicationContext());
//                p.sprintf("GPS required.!");
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
//        }

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(10000 / 2);

            Builder builder = new Builder().addLocationRequest(locationRequest);
            builder.setAlwaysShow(true);

//        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(flpc, builder.build());
            Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(this)
                    .checkLocationSettings(builder.build());
            result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
                @Override
                public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
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
                                            .startResolutionForResult(EmergencyContactInfo.this,
                                                    100);
                                } catch (IntentSender.SendIntentException e) {

                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    Print p = new Print(EmergencyContactInfo.this);
                                    p.fprintf("Cannot Fetch Your District Emergency Contact Info");
                                break;
                            case LocationSettingsStatusCodes.SUCCESS:
                                fetchProdFromFirebase();
                                break;
                        }
                    }
                }
            });
        }else {
            fetchProdFromFirebase();
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkingPermissions();
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



}
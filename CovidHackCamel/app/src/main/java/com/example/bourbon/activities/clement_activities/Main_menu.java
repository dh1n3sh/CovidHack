//package com.example.bourbon.activities.clement_activities;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.ContextCompat;
//
//import com.example.bourbon.R;
//import com.example.bourbon.activities.arumugam_activities.MapsActivity;
//import com.example.bourbon.activities.arumugam_activities.MapsActivityGeofencing;
//import com.example.bourbon.activities.harish_activities.PersonLocAct;
//import com.example.bourbon.activities.harish_activities.recycler_view_acts.CovidStatusInfo;
//import com.example.bourbon.activities.harish_activities.recycler_view_acts.CustomerOrderInfo;
//import com.example.bourbon.activities.harish_activities.recycler_view_acts.InfectedPeopleInfo;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.Map;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import frame_transition.Transition;
//import print.Print;
//
//public class Main_menu extends AppCompatActivity {
//
//    @BindView(R.id.textView)
//    TextView textView;
//    private Print p;
//    private Transition transition;
//    DatabaseReference databaseReference;
//    FirebaseUser auth;
//    SharedPreferences sharedPreferences;
//    private FusedLocationProviderClient fusedLocationProviderClient;
//
//    public SharedPreferences getSharedPreferences() {
//        return sharedPreferences;
//    }
//
//    public void setSharedPreferences(SharedPreferences sharedPreferences) {
//        this.sharedPreferences = sharedPreferences;
//    }
//
//    //harish
//    private LocationManager locationManager;
//    private LocationListener locationListener;
//    private FusedLocationProviderClient mfusedLocationProviderClient;
//
//    void init() {
//        transition = new Transition(this);
//        p = new Print(this);
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        init();
//
//        sharedPreferences = getSharedPreferences("default",Context.MODE_PRIVATE);
//
//        auth = FirebaseAuth.getInstance().getCurrentUser();
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                try {
//                    textView.setText("Welcome  " + snapshot.child("users").child(auth.getUid()).child("Name").getValue()
//                            .toString());
//                } catch (Exception e) {
//                    Toast.makeText(Main_menu.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(Main_menu.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
////        LocationManager lm = (LocationManager)getSystemService(LOCATION_SERVICE);
////        if(!lm.isProviderEnabled("gps"))
////        {
////            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
////            startActivityForResult(intent,1);
////        }else{
////            p.sprintf("Please turn on your Location for some functionalities to work");
////        }
//
//
//        getLocation();
//
////        getHomeLocation();
//
//    }
//
//    private void getHomeLocation() {
//        try {
//            Address address = new Geocoder(this).getFromLocationName("47/18 Krishnapuram Street" +
//                    "Choolaimedu Chennai", 5).get(0);
////            SharedCode(address.toString());
//            p.sprintf("Home Lat: " + address.getLatitude() + " Long: " + address.getLongitude());
//
//            Address addr2 = new Geocoder(this).getFromLocation(13.060661, 80.228231, 5).get(0);
////            SharedCode(addr2.toString());
//            p.sprintf("Your Loc Name: " + addr2.getAddressLine(0) + "\nLocality" + addr2.getLocality());
//
//        } catch (Exception e) {
//            p.fprintf("Failed to get Home Lat Long\nError: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    void getLocation() {
//        try {
//
//            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//            Geocoder geocoder = new Geocoder(this);
//            locationListener = new LocationListener() {
//                @Override
//                public void onLocationChanged(Location location) {
//
//                    try {
//                        Address addr2 = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5).get(0);
//                        //add this below loc info to shared pref
//                        //addr2.getAddressLine(0)
//                        SharedCode(addr2.toString());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    Log.d("OnNotFused Loc ", "Lat: " + location.getLatitude() + "\nLong: " + location.getLongitude());
//                }
//
//                @Override
//                public void onStatusChanged(String provider, int status, Bundle extras) {
//
//                }
//
//                @Override
//                public void onProviderEnabled(String provider) {
//
//                }
//
//                @Override
//                public void onProviderDisabled(String provider) {
//
//                }
//            };
//
//            if (Build.VERSION.SDK_INT < 23) {
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                        0, 0, locationListener);
//            } else if (Build.VERSION.SDK_INT >= 23) {
//                if (ContextCompat.checkSelfPermission(this,
//                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{
//                            Manifest.permission.ACCESS_FINE_LOCATION
//                    }, 1000);
//                } else {
//                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                                0, 0, locationListener);
//
//                        getLastLocation();
//                        //p.sprintf("Lat = "+location.getLatitude());
//
//                    }
//                }
//            }
//
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    void SharedCode(String address) {
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(Calendar.getInstance().getTime().toString(), address);
//        editor.apply();
//        getShared();
//    }
//
//    public void getShared() {
//        Map<String, ?> keys = sharedPreferences.getAll();
//
//        for (Map.Entry<String, ?> entry : keys.entrySet()) {
////            Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
//            //Toast.makeText(this, entry.getValue().toString(), Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//
//    @OnClick({R.id.hospital, R.id.fund, R.id.lab, R.id.hotspot, R.id.course, R.id.toll, R.id.volunteer, R.id.donation, R.id.store, R.id.pass, R.id.logout, R.id.checkout})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.hospital:
//                Intent intent6 = new Intent(Main_menu.this, MapsActivity.class);
//                startActivity(intent6);
//                break;
//            case R.id.fund:
//                Intent intent = new Intent(Main_menu.this, Upi_payments.class);
//                startActivity(intent);
//                break;
//            case R.id.lab:
//                Intent intent7 = new Intent(Main_menu.this, CovidStatusInfo.class);
//                startActivity(intent7);
//                break;
//            case R.id.hotspot:
//                Intent intent10 = new Intent(Main_menu.this, MapsActivityGeofencing.class);
//                startActivity(intent10);
//                break;
//            case R.id.course:
//                Intent intent1 = new Intent(Main_menu.this, YoutubeList.class);
//                startActivity(intent1);
//                break;
//            case R.id.toll:
//                Intent intent2 = new Intent(Main_menu.this, EmergencyContactInfo.class);
//                startActivity(intent2);
//                break;
//            case R.id.volunteer:
//                Intent intent3 = new Intent(Main_menu.this, Volunteer_Registration.class);
//                startActivity(intent3);
//                break;
//            case R.id.donation:
//                Intent intent4 = new Intent(Main_menu.this, Donation.class);
//                startActivity(intent4);
//                break;
//            case R.id.store:
//                Intent intent8 = new Intent(this, Store_Menu.class);
//                startActivity(intent8);
//                break;
//            case R.id.pass:
//                Intent intent9 = new Intent(Main_menu.this, E_Pass.class);
//                startActivity(intent9);
//                //transition.goTo(InfectedPeopleInfo.class);
//                break;
//            case R.id.logout:
//                FirebaseAuth.getInstance().signOut();
//                Intent intent11 = new Intent(Main_menu.this, Startact.class);
//                //transition.goTo(PersonLocAct.class);
//                intent11.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                startActivity(intent11);
//                break;
//            case R.id.checkout:
//                Intent intent15 = new Intent(this, CustomerOrderInfo.class);
//                startActivity(intent15);
//                break;
//
//
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == 1000) {
//            if (grantResults.length > 0) {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                                0, 0, locationListener);
//
//                        getLastLocation();
//                    }
//                }
//            }
//        }
//    }
//
//    void getLastLocation() {
//        fusedLocationProviderClient.getLastLocation().addOnSuccessListener((location) -> {
//            if (location != null) {
//
//                Geocoder geocoder = new Geocoder(this);
//                try {
//                    Address addr2 = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5).get(0);
//                    //add this below loc info to shared pref
//                    SharedCode(addr2.getAddressLine(0).toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.d("Fused Loc ", "Lat: " + location.getLatitude() + "\nLong: " + location.getLongitude());
//
//            }
//        }).addOnFailureListener((e) -> {
//            p.fprintf("Failed to retrieve Locaiton\nError: " + e.getMessage());
//        });
//
//
//    }
//
//
//    @OnClick(R.id.infected)
//    public void onInfectedClicked() {
//
//        transition.goTo(InfectedPeopleInfo.class);
//
//    }
//
//    @OnClick(R.id.mylocation)
//    public void onMylocationClicked() {
//        transition.goTo(PersonLocAct.class);
//    }
//}

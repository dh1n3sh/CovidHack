package com.example.bourbon.activities.arumugam_activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bourbon.R;

import java.net.URI;

public class HospitalView extends AppCompatActivity {

    private HospitalDetails obj;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_view_arumugam);
        location = getIntent().getStringExtra("loc");
        obj = (HospitalDetails)getIntent().getSerializableExtra("obj");
        TextView hospitalname =findViewById(R.id.hospitalname);
        TextView address = findViewById(R.id.address);
        RatingBar rating = findViewById(R.id.rating);
        TextView openinghrs = findViewById(R.id.openinghrs);
        Button directionbutton = findViewById(R.id.directions);

        hospitalname.setText(obj.getHospitalName());
        address.setText(obj.getAddress());
        rating.setRating(Float.parseFloat(obj.getRating()));
        openinghrs.setText(obj.getOpeningHours());

        directionbutton.setOnClickListener(v -> {

            String direction;
            StringBuilder sb= new StringBuilder();
            sb.append("https://www.google.com/maps/dir/?api=1");
            sb.append("&origin=").append(location);
            sb.append("&destination="+obj.getLocationlatlng()[0]+","+obj.getLocationlatlng()[1]);

            direction=sb.toString();

            Uri uri = Uri.parse(direction);

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);

        });

    }
}

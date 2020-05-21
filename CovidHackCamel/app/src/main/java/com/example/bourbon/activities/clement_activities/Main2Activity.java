package com.example.bourbon.activities.clement_activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.example.bourbon.activities.clement_activities.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    FirebaseAuth mauth ;
    EditText name;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser();
        name = findViewById(R.id.name);

        TextView tv = findViewById(R.id.set);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        User newuser = new User();
        newuser.setName("Clement");
        newuser.setAddress("Thuraipakkam");

//        mDatabase.child("users").child(user.getUid()).setValue(newuser);

        Intent i = new Intent(Main2Activity.this,YoutubeList.class);
        startActivity(i);
    }
}

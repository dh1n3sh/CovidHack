package com.example.bourbon.activities.clement_activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Space;
import android.widget.Toast;

import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.Dashboard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class Splash_Screen extends AppCompatActivity {
    FirebaseAuth mauth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                FirebaseMessaging.getInstance().subscribeToTopic("general")
                        .addOnCompleteListener(task -> {
                            String msg = "Successful" ;
                            if (!task.isSuccessful()) {
                                msg = "Failed";
                            }
//                        Toast.makeText(Splash_Screen.this,msg, Toast.LENGTH_SHORT).show();
                        });


                mauth = FirebaseAuth.getInstance();
                FirebaseUser user = mauth.getCurrentUser();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                if(user!=null){

//            Intent intent = new Intent(Startact.this, Main_menu.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//            startActivity(intent);
                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            try {
                                if (snapshot.child("users").hasChild(user.getUid())) {
                                    // run some code
//                                    Toast.makeText(Splash_Screen.this,"Success1", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Splash_Screen.this, Dashboard.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
//                                    Toast.makeText(Splash_Screen.this,"Success1", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Splash_Screen.this, User_Registration.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            }catch (Exception e){
                                Toast.makeText(Splash_Screen.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(Splash_Screen.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
//                    Toast.makeText(Splash_Screen.this,"Success1", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Splash_Screen.this, Startact.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            }
        }, 500);


    }
}

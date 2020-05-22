package com.example.bourbon.activities.arumugam_activities;

import android.content.Context;
import android.util.Log;


import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;

// Doesnt work anymore.. as it is difficult to link a static class with valueeventlistener.


public class LabApiQuery {

    private static DatabaseReference databaseReference;
    private static ArrayList<LatLng> result;

    public static void ping()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("/labs/items");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                result = null;

                try {

                    Log.d("datasnapshot",dataSnapshot.getValue().toString());
                    ArrayList<HashMap<String,Double>>jsonArray = (ArrayList<HashMap<String, Double>>) dataSnapshot.getValue();
                    result = new ArrayList<LatLng>();

                    for (int i = 0; i < jsonArray.size(); i++) {
                        HashMap<String,Double> jsonObject= jsonArray.get(i);
                        result.add(new LatLng(jsonObject.get("lat"),jsonObject.get("lng")));
                    }

                    Log.d("result",result.toString());
                }
                catch(Exception e)
                {
                    result=null;
                    Log.d("jsonerror",e.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("labapi",databaseError.toString());
            }
        });

    }
}

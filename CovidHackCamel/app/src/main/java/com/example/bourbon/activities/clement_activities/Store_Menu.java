package com.example.bourbon.activities.clement_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.recycler_view_acts.OrderInfo;
import com.example.bourbon.activities.harish_activities.recycler_view_acts.ShopInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Store_Menu extends AppCompatActivity {

    DatabaseReference mDatabase;
    FirebaseAuth mauth;
    @BindView(R.id.storeorder)
    Button storeorder;
    @BindView(R.id.imageView7)
    ImageView imageView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store__menu);
        ButterKnife.bind(this);

        storeorder.setVisibility(View.GONE);
        imageView7.setVisibility(View.GONE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mauth = FirebaseAuth.getInstance();
        displayButton();
//        showToken();
    }

    @OnClick({R.id.storeregister, R.id.nearbystore, R.id.storeorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.storeregister:
                Intent intent = new Intent(this, Store_Registration.class);
                startActivity(intent);
                break;
            case R.id.nearbystore:
                Intent intent1 = new Intent(this, ShopInfo.class);
                startActivity(intent1);
                break;
            case R.id.storeorder:
                Intent intent2 = new Intent(this, OrderInfo.class);
                startActivity(intent2);
                break;
        }
    }

    void displayButton() {

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapSHot : dataSnapshot.child("Stores").getChildren()){
                    if(postSnapSHot.getKey().equals(mauth.getCurrentUser().getUid())){
                        storeorder.setVisibility(View.VISIBLE);
                        imageView7.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    void showToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("default",MODE_PRIVATE);
        String token = sharedPreferences.getString("Token","Nooot");
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
    }

}

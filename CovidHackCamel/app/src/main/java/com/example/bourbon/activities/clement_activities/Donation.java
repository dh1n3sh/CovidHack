package com.example.bourbon.activities.clement_activities;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import print.Print;

public class Donation extends AppCompatActivity {

    @BindView(R.id.food)
    CheckBox food;
    @BindView(R.id.clothing)
    CheckBox clothing;
    @BindView(R.id.others)
    CheckBox others;
    String donate = "";
    @BindView(R.id.extra)
    EditText extra;
    FirebaseUser user;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arun_donation_page);
        ButterKnife.bind(this);
        user = FirebaseAuth.getInstance().getCurrentUser() ;
        database = FirebaseDatabase.getInstance().getReference() ;

    }

    @OnClick(R.id.submit)
    public void onViewClicked() {
        if(!food.isChecked() && !clothing.isChecked() && !others.isChecked()){
            food.setError("Select atleast One to Donate");
        }else if(others.isChecked() && (extra.getText().toString().length() ==0) ){
            extra.setError("Enter atleast one to Donate");
        }else {
            submit();
        }
    }

    void submit(){
        if (food.isChecked()) {
            donate += "Food, ";
        }

        if (clothing.isChecked()) {
            donate += "Cloths ,";
        }

        if (others.isChecked()) {
            donate += extra.getText().toString();
        }

        database.child("Donations").child(user.getUid()).setValue(donate);

        Print p = new Print(this);
        p.sprintf("Details Updated...You will be Contacted Soon");
        finish();
    }
}

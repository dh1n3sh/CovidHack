package com.example.bourbon.activities.clement_activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import print.Print;

public class Volunteer_Registration extends AppCompatActivity {

    @BindView(R.id.work)
    Spinner work;
    @BindView(R.id.skills)
    EditText skills;
    FirebaseUser user;
    DatabaseReference database;
    Print p ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vol_main);
        ButterKnife.bind(this);
        user = FirebaseAuth.getInstance().getCurrentUser() ;
        database = FirebaseDatabase.getInstance().getReference() ;
        p = new Print(Volunteer_Registration.this);
    }

    @OnClick(R.id.submit)
    public void onViewClicked() {
        database.child("Volunteers-Registers").child(user.getUid()).child("Work").setValue(work.getSelectedItem().toString());
        database.child("Volunteers-Registers").child(user.getUid()).child("Skills").setValue(skills.getText().toString());

        p.sprintf("Your Details are Successfully Added.");
        this.finish();
    }
}

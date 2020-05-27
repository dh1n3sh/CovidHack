package com.example.bourbon.activities.clement_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.Dashboard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Startact extends AppCompatActivity {


    FirebaseAuth mauth;
    private static final int RC_SIGN_IN = 123;
    @BindView(R.id.mobilenum)
    EditText mobilenum;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_main);
        ButterKnife.bind(this);



        startActivity(new Intent(this,Dashboard.class));
            mobilenum.setCursorVisible(false);

//        mobile = findViewById(R.id.mobi);
//        submit = findViewById(R.id.sub);

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Startact.this, Verifi.class);
//                i.putExtra("Mobile",mobile.getText().toString());
//                startActivity(i);
//            }
//        });

    }

    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero, R.id.next,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                mobilenum.setText(mobilenum.getText()+"1");

                break;
            case R.id.two:
                mobilenum.setText(mobilenum.getText()+"2");
                break;
            case R.id.three:
                mobilenum.setText(mobilenum.getText()+"3");
                break;
            case R.id.four:
                mobilenum.setText(mobilenum.getText()+"4");
                break;
            case R.id.five:
                mobilenum.setText(mobilenum.getText()+"5");
                break;
            case R.id.six:
                mobilenum.setText(mobilenum.getText()+"6");
                break;
            case R.id.seven:
                mobilenum.setText(mobilenum.getText()+"7");
                break;
            case R.id.eight:
                mobilenum.setText(mobilenum.getText()+"8");
                break;
            case R.id.nine:
                mobilenum.setText(mobilenum.getText()+"9");
                break;
            case R.id.zero:
                mobilenum.setText(mobilenum.getText()+"0");
                break;
            case R.id.next:
                Intent i = new Intent(Startact.this, Verifi.class);
                i.putExtra("Mobile",mobilenum.getText().toString());
                startActivity(i);
                break;
            case R.id.back:
            mobilenum.setText(removeLastChar(mobilenum.getText().toString()));
            break;
        }
    }

    public String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }
}

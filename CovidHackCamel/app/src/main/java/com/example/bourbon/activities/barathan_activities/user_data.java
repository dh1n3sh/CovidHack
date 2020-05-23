package com.example.bourbon.activities.barathan_activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class user_data extends AppCompatActivity {

    @BindView(R.id.first_name)
    EditText user_firstName;

    @BindView(R.id.address)
    EditText user_address;
    @BindView(R.id.dob)
    EditText user_dob;
    @BindView(R.id.gender)
    Spinner user_gender;
    @BindView(R.id.send)
    Button user_send;
    DatePickerDialog dpdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        ButterKnife.bind(this);

        user_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                dpdialog = new DatePickerDialog(user_data.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String Date = day+"/"+month+"/"+year;
                        user_dob.setText("     "+Date);
                    }
                },year,month,day);
                dpdialog.show();
            }
        });

    }



    @OnClick(R.id.send)
    public void onSendClicked() {
    }
}

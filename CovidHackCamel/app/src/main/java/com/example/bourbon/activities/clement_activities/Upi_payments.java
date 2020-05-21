package com.example.bourbon.activities.clement_activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Upi_payments extends AppCompatActivity {


    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.amount)
    EditText amount;
    @BindView(R.id.notes)
    EditText notes;
    @BindView(R.id.send)
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upi_arun_mainpage);
        ButterKnife.bind(this);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length() == 0){
                    name.setError("Name is Required");

                }else if(amount.getText().toString().length() == 0){
                    amount.setError("Payment Amount is Required");
                }else if(notes.getText().toString().length() == 0){
                    notes.setError("Notes is Required");
                }else{
                    payusingUpi(name.getText().toString(), "pmcares@sbi", amount.getText().toString(),notes.getText().toString());

                }
            }
        });

    }

    private void payusingUpi(String name, String upiid, String money,String notes) {




        Uri uri =
                new Uri.Builder()
                        .scheme("upi")
                        .authority("pay")
                        .appendQueryParameter("pa", upiid)
                        .appendQueryParameter("pn", name)
//                        .appendQueryParameter("mc", "your-merchant-code")
//                        .appendQueryParameter("tr", "your-transaction-ref-id")
                        .appendQueryParameter("tn", notes)
                        .appendQueryParameter("am", money)
                        .appendQueryParameter("cu", "INR")
//                        .appendQueryParameter("url", "your-transaction-url")
                        .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);

        Intent choser = Intent.createChooser(intent, "Pay With");

        if (choser.resolveActivity(getPackageManager()) != null) {
            startActivity(choser);
        } else {
            Toast.makeText(this, "No UPI app found", Toast.LENGTH_SHORT).show();
        }


    }


}

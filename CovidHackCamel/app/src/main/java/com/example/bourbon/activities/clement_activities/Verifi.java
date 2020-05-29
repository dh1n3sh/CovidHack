package com.example.bourbon.activities.clement_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.Dashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import print.Print;

public class Verifi extends AppCompatActivity {

    String no;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private String mVerificationId;
    EditText otp;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_main);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        Bundle b = getIntent().getExtras();
        String no = b.getString("Mobile");
        sendVerificationCode(no);
        otp = findViewById(R.id.otp);
        otp.setCursorVisible(false);

    }

    @OnClick(R.id.next)
    public void onViewClicked() {

        String code = otp.getText().toString().trim();
        if (code.isEmpty() || code.length() < 6) {
            otp.setError("Enter valid code");
            otp.requestFocus();
            return;
        }

        //verifying the code entered manually
        verifyVerificationCode(code);
    }


    private void sendVerificationCode(String no) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + no,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                otp.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Verifi.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Verifi.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
                            mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
                            user = mAuth.getCurrentUser();
                            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    if (snapshot.hasChild(user.getUid())) {
                                        // run some code
                                        Intent intent = new Intent(Verifi.this, Dashboard.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        Intent intent = new Intent(Verifi.this, User_Registration.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(Verifi.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";
                            Print p = new Print(Verifi.this);
                            p.fprintf(task.getException().getMessage());

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }


                        }
                    }
                });
    }


    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.back, R.id.zero})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                otp.setText(otp.getText()+"1");
                break;
            case R.id.two:
                otp.setText(otp.getText()+"2");
                break;
            case R.id.three:
                otp.setText(otp.getText()+"3");
                break;
            case R.id.four:
                otp.setText(otp.getText()+"4");
                break;
            case R.id.five:
                otp.setText(otp.getText()+"5");
                break;
            case R.id.six:
                otp.setText(otp.getText()+"6");
                break;
            case R.id.seven:
                otp.setText(otp.getText()+"7");
                break;
            case R.id.eight:
                otp.setText(otp.getText()+"8");
                break;
            case R.id.nine:
                otp.setText(otp.getText()+"9");
                break;
            case R.id.back:
                otp.setText(new Startact().removeLastChar(otp.getText().toString()));
                break;
            case R.id.zero:
                otp.setText(otp.getText()+"0");
                break;
        }
    }
}

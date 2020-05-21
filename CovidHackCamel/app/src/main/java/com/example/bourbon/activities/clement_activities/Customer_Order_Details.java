package com.example.bourbon.activities.clement_activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.Dashboard;
import com.example.bourbon.activities.harish_activities.model.CustomerOrder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import print.Print;

public class Customer_Order_Details extends AppCompatActivity {

    @BindView(R.id.dop)
    TextView dop;
    @BindView(R.id.orderid)
    TextView orderid;
    @BindView(R.id.custname)
    TextView custname;
    CustomerOrder order;
    AlertDialog.Builder builder;
    String shopId;
    @BindView(R.id.rejectorder)
    Button rejectorder;
    @BindView(R.id.customer)
    TextView customer;
    private String[] groceries;
    DatabaseReference databaseReference;
    ArrayList<String> groceryList;
    String uri;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__details);
        ButterKnife.bind(this);
        rejectorder.setText("Cancel Order");
        order = (CustomerOrder) getIntent().getSerializableExtra("MyClass");
        dop.setText(order.getDOP());
//        custname.setText(order.getCustName());
        customer.setText("Shop Name");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(count == 0 && dataSnapshot.child("Carts").hasChild(order.getDOP())) {
                    shopId = dataSnapshot.child("Carts").child(order.getDOP()).child("shopId").getValue().toString();
                    custname.setText(dataSnapshot.child("Stores").child(shopId).child("Name").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @OnClick({R.id.viewcart, R.id.rejectorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.viewcart:
                if (order.getManuel().equals("False")) {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!(Customer_Order_Details.this).isFinishing() && (count == 0)) {
//                    Toast.makeText(Order_Details.this,dataSnapshot.child("Carts").child(order.getDOP()).getValue().toString(), Toast.LENGTH_SHORT).show();
                                groceryList = new ArrayList<>();
                                for (DataSnapshot postSnapShot : dataSnapshot.child("Carts").child(order.getDOP()).child("Items").getChildren()) {

                                    groceryList.add(postSnapShot.getValue().toString());
                                }
                                groceries = groceryList.toArray(new String[groceryList.size()]);
                                builder = new AlertDialog.Builder(Customer_Order_Details.this);
                                builder.setTitle("Cart");
                                builder.setItems(groceries, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }

                                });
                                AlertDialog dialog = builder.create();


                                //show dialog
                                dialog.show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(Customer_Order_Details.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!(Customer_Order_Details.this).isFinishing() && (count == 0)) {
                                uri = dataSnapshot.child("Carts").child(order.getDOP()).child("imageUri").getValue().toString();
                                ImageView imageView = new ImageView(Customer_Order_Details.this);
//                          Glide.with(Order_Details.this).load(uri).into(imageView);
                                Picasso.get().load(uri).into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        builder = new AlertDialog.Builder(Customer_Order_Details.this);
                                        builder.setTitle("Cart");
                                        builder.setView(imageView);

                                        AlertDialog dialog = builder.create();

                                        //show dialog
                                        dialog.show();


                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Toast.makeText(Customer_Order_Details.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                });
//                    Toast.makeText(Order_Details.this, "Image Downloaded", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(Customer_Order_Details.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.rejectorder:
                databaseReference.child("Carts").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                            if (postSnapShot.getKey().equals(order.getDOP())) {
                                count = 1;
                                postSnapShot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        count = 1;
                                        Print p = new Print(Customer_Order_Details.this);
                                        p.sprintf("Order Cancelled Successfully");
                                        MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext(),"Order Cancelled","A Customer has cancelled an order",shopId);
                                        myNotificationManager.Notify();
                                        Intent intent = new Intent(Customer_Order_Details.this, Dashboard.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Customer_Order_Details.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}

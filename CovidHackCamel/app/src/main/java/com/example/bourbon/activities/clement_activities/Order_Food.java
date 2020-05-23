package com.example.bourbon.activities.clement_activities;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bourbon.R;
import com.example.bourbon.activities.clement_activities.model.ListUpload;
import com.example.bourbon.activities.harish_activities.Dashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import print.Print;

public class Order_Food extends AppCompatActivity {

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAA7Ohurdc:APA91bGATg-02XfKY8klsUr9hAaYIeGLvY8CYQHfLEykFk3b21cS7LpPN_SPyXYHVJcZ1qp9XT9NWepsAIjYMdt9NrjtlKJu0vmfr-uH0KIzymDWfO0y3EYWqnahtUTcmiPNuXfx9uxf";
    final private String contentType = "application/json";

    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.item_name)
    EditText itemName;
    @BindView(R.id.item_quantity)
    EditText itemQuantity;
    @BindView(R.id.manuel)
    Button manuel;
    private Uri imageUri;
    boolean imageSelected = false;

    ListView groceryList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> groceries = new ArrayList<String>();
    private String[] groceries1;
    private StorageReference storageReference;
    private DatabaseReference mdatabase;
    AlertDialog.Builder builder;
    Print p = new Print(this);
    String shopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_purchase);
        ButterKnife.bind(this);
        storageReference = FirebaseStorage.getInstance().getReference("Carts");
        mdatabase = FirebaseDatabase.getInstance().getReference();
        View view = LayoutInflater.from(getApplication()).inflate(R.layout.listing_view, null);
        groceryList = view.findViewById(R.id.grocery_list);
        builder = new AlertDialog.Builder(Order_Food.this);
        builder.setTitle("Cart");
        Bundle b = getIntent().getExtras();
        String name = b.getString("Name");
        shopId = b.getString("ShopId");
        storeName.setText(name);
        adapter = new ArrayAdapter<String>(this, R.layout.gro_list, R.id.textViewgroc, groceries);
        groceryList.setAdapter(adapter);
    }


    @OnClick({R.id.add_item, R.id.submit_list, R.id.cart, R.id.manuel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_item:
                groceries.add(itemName.getText().toString() + ":  " + itemQuantity.getText().toString());
                adapter.notifyDataSetChanged();
                groceries1 = groceries.toArray(new String[groceries.size()]);
                p.sprintf("Item Added To Cart");

                break;
            case R.id.submit_list:
                if (imageSelected) {
                    uploadFile();

                } else {
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    Date date = new Date();
                    System.out.println(dateFormat.format(date));
                    ListUpload listUpload = new ListUpload(FirebaseAuth.getInstance().getCurrentUser().getUid(), shopId, "Null", "False");
                    mdatabase.child("Carts").child(dateFormat.format(date)).setValue(listUpload);
                    mdatabase.child("Carts").child(dateFormat.format(date)).child("Items").setValue(Arrays.asList(groceries1));
                    Notify();
                    p.sprintf("Order Successfully Placed");
                    Intent intent = new Intent(Order_Food.this, Dashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

                break;
            case R.id.cart:
                builder.setItems(groceries1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        groceries.remove(which);
                        groceries1 = groceries.toArray(new String[groceries.size()]);
                        p.sprintf("Item Deleted From Cart");
                    }

                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.manuel:
                FileChooser();
                break;
        }
    }

    public void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageSelected = true;
            manuel.setText("File Chosen");

        }
    }

    public void uploadFile() {
        if (imageUri != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + " . " + getFileExtension(imageUri));
            fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
//                            Toast.makeText(Order_Food.this, "Upload 22Successful", Toast.LENGTH_SHORT).show();
                            try {
                                ListUpload listUpload = new ListUpload(FirebaseAuth.getInstance().getCurrentUser().getUid(), shopId, uri.toString(), "True");
//                            Toast.makeText(Order_Food.this, "Upload 22Successful", Toast.LENGTH_SHORT).show();

                                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                Date date = new Date();
                                System.out.println(dateFormat.format(date));
//                                Toast.makeText(Order_Food.this, "Upload 33Successful", Toast.LENGTH_SHORT).show();

                                mdatabase.child("Carts").child(dateFormat.format(date)).setValue(listUpload);
//                                Toast.makeText(Order_Food.this, "Upload 44Successful", Toast.LENGTH_SHORT).show();

                                p.sprintf("Order Successfully Placed");
                                Notify();
                                Intent intent = new Intent(Order_Food.this, Dashboard.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                return;
                            }catch (Exception e){
                                Toast.makeText(Order_Food.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Order_Food.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                }
            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                }
            });
        } else {
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }

    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    void Notify(){
        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        try {
            notifcationBody.put("title", "New Order");
            notifcationBody.put("message", "You have got a New Order to Deliver");
            notifcationBody.put("shopId",shopId);
            notification.put("to", "/topics/stores");
            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Log.e("Notification Error", "onCreate: " + e.getMessage() );
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("My Notification", "onResponse: " + response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Order_Food.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i("Notification Error", "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

}

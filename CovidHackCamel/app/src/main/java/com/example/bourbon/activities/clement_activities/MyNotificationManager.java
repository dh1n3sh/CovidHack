package com.example.bourbon.activities.clement_activities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MyNotificationManager {
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAA7Ohurdc:APA91bGATg-02XfKY8klsUr9hAaYIeGLvY8CYQHfLEykFk3b21cS7LpPN_SPyXYHVJcZ1qp9XT9NWepsAIjYMdt9NrjtlKJu0vmfr-uH0KIzymDWfO0y3EYWqnahtUTcmiPNuXfx9uxf";
    final private String contentType = "application/json";
    private Context context;
    String title,message,shopId;
    public MyNotificationManager(Context context,String title,String message,String shopId){

        this.context = context;
        this.message = message ;
        this.title = title ;
        this.shopId = shopId ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    void Notify(){
        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        try {
            notifcationBody.put("title", title);
            notifcationBody.put("message", message);
            notifcationBody.put("shopId",shopId);
            Log.e("Notify ShopId",shopId);
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
                        Toast.makeText(context, "Request error", Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }
}

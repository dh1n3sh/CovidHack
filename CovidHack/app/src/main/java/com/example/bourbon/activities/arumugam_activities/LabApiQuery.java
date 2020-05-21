package com.example.bourbon.activities.arumugam_activities;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LabApiQuery {

    private static String url = "https://covid.icmr.org.in/index.php/testing-facilities?option=com_hotspots&view=jsonv4&task=gethotspots&hs-language=en-GB&page=1&per_page=1000&total_pages=1&total_entries=572&cat=&level=2&ne=64.702203%2C180&sw=-77.037793%2C-180&c=-19.13832%2C28.387855&fs=0&offset=0&format=raw";
    public static void ping(Context context)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.d("apiping","ping method called.");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("apiping",response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("apiping",error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}

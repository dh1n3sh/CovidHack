package com.example.bourbon.activities.arumugam_activities;

import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Labs {

    private String name;
    private String address;
    private Location location;

    public Labs(String name, String address, Location location) {
        this.name = name;
        this.address = address;
        this.location = location;
    }

    public Labs(HashMap<String,Object> hashMap)
    {
            this.name = (String)hashMap.get("title");
            this.address = (String)hashMap.get("address");
            this.location = new Location("gps");
            this.location.setLongitude((Double)hashMap.get("lng"));
            this.location.setLatitude((Double)hashMap.get("lat"));

    }

    @NonNull
    @Override
    public String toString() {
        String res = "Name : "+this.getName()+"\nAddress : "+this.getAddress()+"\nLocation : "+this.getLocation().toString();
        return res;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Location getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
